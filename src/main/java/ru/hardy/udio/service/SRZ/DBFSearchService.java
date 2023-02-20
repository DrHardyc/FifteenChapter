package ru.hardy.udio.service.SRZ;


import com.linuxense.javadbf.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataFilePatient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class DBFSearchService {

    @Autowired
    private Environment environment;

    private void setDataDBF(List<DataFilePatient> dataDBF, String dirFileIn){

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
        fields[4].setName("enp");
        fields[4].setType(DBFDataType.CHARACTER);
        fields[4].setLength(16);

        DBFWriter writer;
        try {
            writer = new DBFWriter(new FileOutputStream(dirFileIn), Charset.forName("cp866"));
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

    public List<DataFilePatient> getDataFromDBF(List<DataFilePatient> dataFilePatientList) throws IOException, InterruptedException {
        String genStrDirFile = RandomStringUtils.random(10, true, true);
        String dirFileIn = environment.getProperty("dbffile.pathin") + "TESTDBF" + genStrDirFile.toUpperCase() + ".dbf";
        String dirFileOut = environment.getProperty("dbffile.pathout") + "out_TESTDBF" + genStrDirFile.toUpperCase() + ".dbf";
        setDataDBF(dataFilePatientList, dirFileIn);
        int sec = 0;
        while (!checkDBFFile(dirFileOut)){
            if (sec > 9000000) {
                Thread.sleep(1000);
                System.out.println("Oshibka ojidaniy ot SRZ");
                break;
            }
            sec++;
        }
        DBFReader reader = null;
        FileInputStream fileInputStream = new FileInputStream(dirFileOut);
        try {
            reader = new DBFReader(fileInputStream, Charset.forName("cp866"));

            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                for (DataFilePatient dataFilePatient : dataFilePatientList){
                    if (Objects.equals(dataFilePatient.getEnp(), row.getString("enp"))){
                        dataFilePatient.setIdsrz(row.getLong("pid"));
                        break;
                    }
                }
            }
        } catch (DBFException e) {
            e.printStackTrace();
        } finally {
            DBFUtils.close(reader);
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    Files.deleteIfExists(Paths.get(dirFileOut));
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        return dataFilePatientList;
    }

    private boolean checkDBFFile(String dirFileOut){
        try {
            return Files.exists(Paths.get(dirFileOut));
        } catch (Exception e){
            return false;
        }
    }
}
