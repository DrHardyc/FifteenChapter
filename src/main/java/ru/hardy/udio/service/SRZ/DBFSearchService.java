package ru.hardy.udio.service.SRZ;


import com.linuxense.javadbf.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.ResponseAnswerUdio;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.repo.DataFilePatientRepo;
import ru.hardy.udio.repo.DataFileRepo;
import ru.hardy.udio.repo.PeopleRepo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DBFSearchService {

    @Autowired
    private DataFileRepo dataFileRepo;

    @Autowired
    private DataFilePatientRepo dataFilePatientRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    private void setDataDBF(List<DataFilePatient> dataDBF, String dirFileIn){

        DBFField[] fields = new DBFField[6];

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

        fields[5] = new DBFField();
        fields[5].setName("dout");
        fields[5].setType(DBFDataType.DATE);
        fields[5].setLength(8);

        DBFWriter writer;
        try {
            System.out.println("Создан файл :" + dirFileIn);
            writer = new DBFWriter(new FileOutputStream(dirFileIn), Charset.forName("cp866"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        writer.setFields(fields);


        List<DataFilePatient> newDataFilePatientList = createDataFileWithDBFFile(dataDBF);
        for (DataFilePatient dataFilePatient : newDataFilePatientList){
            if (dataFilePatient.getEnp() != null && !dataFilePatient.getEnp().isEmpty() && searchFromUdioWithSrzId(dataFilePatient.getEnp())) {
                Object[] rowData = new Object[6];
                rowData[0] = dataFilePatient.getFam();
                rowData[1] = dataFilePatient.getIm();
                rowData[2] = dataFilePatient.getOt();
                rowData[3] = dataFilePatient.getDr();
                rowData[4] = dataFilePatient.getEnp();
                rowData[5] = dataFilePatient.getDate_2();
                writer.addRecord(rowData);
            } else {
                Object[] rowData = new Object[6];
                rowData[0] = dataFilePatient.getFam();
                rowData[1] = dataFilePatient.getIm();
                rowData[2] = dataFilePatient.getOt();
                rowData[3] = dataFilePatient.getDr();
                rowData[4] = "";
                rowData[5] = null;
                writer.addRecord(rowData);
            }
        }
        writer.close();
    }

    public DataFile getDataFromDBF(DataFile dataFile)  {
        String genStrDirFile = RandomStringUtils.random(10, true, true);
        String dirFileIn = "dbf\\TESTDBF" + genStrDirFile.toUpperCase() + ".dbf";
        String dirFileOut = "dbf\\out_TESTDBF" + genStrDirFile.toUpperCase() + ".dbf";
        setDataDBF(dataFile.getDataFilePatient(), dirFileIn);
        int sec = 0;
        while (!checkDBFFile(dirFileOut)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (sec > 86400) {
                dataFile.setResultCode(ResponseAnswerUdio.PROCESSING_ERR);
                dataFile.setResultDesc("Ошибка ожидания от СРЗ");
                dataFileRepo.save(dataFile);
                break;
            }
            sec++;
        }
        System.out.println("Получен файл :" + dirFileOut);
        DBFReader reader = null;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(dirFileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            reader = new DBFReader(fileInputStream, Charset.forName("cp866"));

            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()){
                    if (Objects.equals(dataFilePatient.getEnp(), row.getString("enp"))){
                        dataFilePatient.setIdsrz(row.getLong("pid"));
                        if (row.getString("lpu") == null || row.getString("lpu").isEmpty()){
                            dataFilePatient.setMo_attach(0);
                        } else dataFilePatient.setMo_attach(Integer.valueOf(row.getString("lpu")));
                        dataFilePatientRepo.save(dataFilePatient);
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
        return dataFile;
    }

    private boolean checkDBFFile(String dirFileOut){
        try {
            return Files.exists(Paths.get(dirFileOut));
        } catch (Exception e){
            return false;
        }
    }

    private List<DataFilePatient> createDataFileWithDBFFile(List<DataFilePatient> dataFilePatientList){
        List<DataFilePatient> newDataFilePatientList = new ArrayList<>();
        newDataFilePatientList.add(dataFilePatientList.get(0));
        boolean flagAdd;
        for (DataFilePatient dataFilePatient : dataFilePatientList){
            flagAdd = true;
            for (DataFilePatient newFilePatient : newDataFilePatientList){
                if (newFilePatient.getFIO().equals(dataFilePatient.getFIO()) &&
                        newFilePatient.getEnp().equals(dataFilePatient.getEnp())){
                    flagAdd = false;
                    break;
                }
            }
            if (flagAdd){
                newDataFilePatientList.add(dataFilePatient);
            }
        }

        return newDataFilePatientList;

    }

    private boolean searchFromUdioWithSrzId(String enp){
        return peopleRepo.findPeopleByEnp(enp) != null;
    }
}
