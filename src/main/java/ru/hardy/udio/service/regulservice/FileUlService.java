package ru.hardy.udio.service.regulservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.domain.regul.VidRegUl;
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
            if (documentUL.getPersonIP() != null)
                documentUL.getPersonIP().setRegNFoms(getRegNTfoms(VidRegUl.IP, documentUL.getPersonIP().getInnFl()));
            if (documentUL.getPersonUL() !=null)
                documentUL.getPersonUL().setRegNFoms(getRegNTfoms(VidRegUl.UL, documentUL.getPersonUL().getInn()));
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


    public String getRegNTfoms(VidRegUl vidRegUl, String inn){
        try {
            String strQuery;
            switch (vidRegUl){
                case IP -> strQuery = "select reg_n_foms from regul.person_ip where inn_fl = '" + inn + "' group by reg_n_foms";
                case UL -> strQuery = "select reg_n_foms from regul.person_ul where inn = '" + inn + "' group by reg_n_foms";
                case null, default -> strQuery = "select reg_n_foms from regul.person_ul";
            }
            ResultSet resultSet = new DBJDBCConfig()
                    .getUDIO()
                    .executeQuery(strQuery);
            if (resultSet.next()){
               return resultSet.getString(1);
            }  else {
                resultSet = new DBJDBCConfig()
                        .getUDIO()
                        .executeQuery("select last_number from regul.reg_n_foms");
                resultSet.next();
                int lastNumber = resultSet.getInt(1);

                if (getByRegNFomsUL(lustNumberToRegNFoms(lastNumber))){
                    updateLustNumber(lastNumber);
                    return lustNumberToRegNFoms(lastNumber);
                }else {
                    updateLustNumber(lastNumber);
                    return getRegNTfoms(vidRegUl, inn);
                }
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
