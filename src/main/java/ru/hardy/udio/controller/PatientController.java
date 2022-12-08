package ru.hardy.udio.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.udio.domain.Patient;

import java.util.List;

@RestController
public class PatientController {

    @PostMapping("/sendpatient")
    public void Get(@RequestBody List<Patient> patientList){
        for(Patient patient : patientList){
            System.out.println(patient.getFam() + " | " + patient.getIm());
        }
    }
}
