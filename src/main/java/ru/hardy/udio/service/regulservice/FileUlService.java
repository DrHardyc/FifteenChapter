package ru.hardy.udio.service.regulservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.repo.regulrepo.FileUlRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class FileUlService {

    @Autowired
    private FileUlRepo fileUlRepo;


    @Transactional
    public void add(FileUL fileUl){
        fileUl.getDocumentUL().forEach(documentUL -> {
            if (documentUL.getPersonIP() != null) documentUL.getPersonIP().setRegNFoms(getRegNTfoms());
            if (documentUL.getPersonUL() !=null) documentUL.getPersonUL().setRegNFoms(getRegNTfoms());
            fileUl.setQuantityDoc(fileUl.getQuantityDoc().trim());
        });
        fileUlRepo.save(fileUl);
    }

    public void addFromFMS(FileUL fileUl){
        fileUlRepo.save(fileUl);
    }


    public void addAll(List<FileUL> fileULList){
        fileUlRepo.saveAll(fileULList);
    }

    public boolean getByIdFile(String fileName) {
        try {
            ResultSet resultSet = new DBJDBCConfig()
                    .getUDIO()
                    .executeQuery("select count(*) from regul.file_ul f where f.id_file = '" + fileName + "'");
            resultSet.next();
            return resultSet.getInt(1) == 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getRegNTfoms(){
        try {
            ResultSet resultSet = new DBJDBCConfig()
                    .getUDIO()
                    .executeQuery("select last_number from regul.reg_n_foms");
            resultSet.next();
            int lastNumber = resultSet.getInt(1);

            if (getByRegNFomsUL(lustNumberToRegNFoms(lastNumber))){
                updateLustNumber(lastNumber);
                return lustNumberToRegNFoms(lastNumber);
            }else {
                updateLustNumber(lastNumber);
                return getRegNTfoms();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String lustNumberToRegNFoms(int lastNumber){
        return "15000" + "0".repeat(Math.max(0, 10 - String.valueOf(lastNumber).length())) + lastNumber;
    };

    private boolean getByRegNFomsUL(String regNFoms){
        try {
            ResultSet resultSet = new DBJDBCConfig()
                    .getUDIO()
                    .executeQuery("select count(*) from regul.person_ul where reg_n_foms = '" + regNFoms + "'");
            resultSet.next();
            return resultSet.getInt(1) == 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateLustNumber(int lastNumber){
        lastNumber = lastNumber + 1;
        try {
            new DBJDBCConfig()
                    .getUDIO()
                    .execute("update regul.reg_n_foms set last_number = " + lastNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
