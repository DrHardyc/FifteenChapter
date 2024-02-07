package ru.hardy.udio.service.regulservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.domain.regul.LoadSettings;
import ru.hardy.udio.repo.regulrepo.LoadSettingsRepo;

import java.util.List;

@Service
public class LoadSettingsService {

    @Autowired
    private LoadSettingsRepo loadSettingsRepo;

    public void add(LoadSettings loadSettings){
        loadSettingsRepo.save(loadSettings);
    }

    public void update(LoadSettings loadSettings, Long id){
        loadSettingsRepo.update(loadSettings.getPathIn(), loadSettings.getPathOut(), id);
    }

    public LoadSettings getWithUser(User user){
        List<LoadSettings> loadSettingsList = loadSettingsRepo.findLoadSettingsByUserId(user.getId());

        if (!loadSettingsList.isEmpty()){
            return loadSettingsList.getFirst();
        }
        return null;
    }
}
