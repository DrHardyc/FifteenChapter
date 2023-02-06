package ru.hardy.udio.service.SRZ;


import com.linuxense.javadbf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataFilePatient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.List;

@Service
public class DBFSearchService {

    @Autowired
    private Environment environment;

    public void setDataDBF(List<DataFilePatient> dataDBF, String genStrDirFile){
        System.out.println(environment.getProperty("dbffile.path"));
        String genStrDirInputFile = environment.getProperty("dbffile.path") + "testdbf" + genStrDirFile + ".dbf";

        DBFField[] fields = new DBFField[5];

        fields[0] = new DBFField();
        fields[0].setName("fam");
        fields[0].setType(DBFDataType.CHARACTER);
        fields[0].setLength(50);

        fields[1] = new DBFField();
        fields[1].setName("im");
        fields[1].setType(DBFDataType.CHARACTER);
        fields[1].setLength(50);

        fields[2] = new DBFField();
        fields[2].setName("ot");
        fields[2].setType(DBFDataType.CHARACTER);
        fields[2].setLength(50);

        fields[3] = new DBFField();
        fields[3].setName("dr");
        fields[3].setType(DBFDataType.DATE);
        fields[3].setLength(8);

        fields[4] = new DBFField();
        fields[4].setName("ENP");
        fields[4].setType(DBFDataType.CHARACTER);
        fields[4].setLength(16);

        DBFWriter writer;
        try {
            writer = new DBFWriter(new FileOutputStream(genStrDirInputFile), Charset.forName("cp866"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        writer.setFields(fields);

        for (DataFilePatient dataFilePatient : dataDBF){
            Object[] rowData = new Object[5];
            rowData[0] = dataFilePatient.getFam();
            rowData[1] = dataFilePatient.getIm();
            rowData[2] = dataFilePatient.getOt();
            rowData[3] = dataFilePatient.getDr();
            rowData[4] = dataFilePatient.getEnp();
            writer.addRecord(rowData);
        }

        writer.close();
    }

    public List<DataFilePatient> getDateDBF(String genStrDirFile, List<DataFilePatient> dataFilePatientList) throws FileNotFoundException {
        String dirFile = environment.getProperty("dbffile.path") + "out_testdbf" + genStrDirFile + ".dbf";
        DBFReader reader = null;
        FileInputStream fileInputStream = new FileInputStream(dirFile);
        try {
            reader = new DBFReader(fileInputStream);

            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                for (DataFilePatient dataFilePatient : dataFilePatientList){
                    if (dataFilePatient.getEnp().equals(row.getString("enp"))){
                        dataFilePatient.setEerp(row.getString("eerp"));
                        break;
                    }
                }
            }
        } catch (DBFException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
        }
        return dataFilePatientList;
    }
}
