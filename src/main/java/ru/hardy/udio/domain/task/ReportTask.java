package ru.hardy.udio.domain.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_util")
public class ReportTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String file_name;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String result;

    private String username;

    private Date dateBeg;
    private Date dateEdit;

    private String period;

    public ReportTask(String name, String file_name, TaskStatus taskStatus, String result, String username, String periods){
        this.name = name;
        this.file_name = file_name;
        this.status = taskStatus;
        this.result = result;
        this.username = username;
        this.period = periods;
        this.dateBeg = Date.from(Instant.now());
        //this.dateEdit = Date.from(Instant.now());
    }

    public ReportTask() {

    }

    public LocalDateTime getLocalDateTimeDateBeg() {
        if (this.dateBeg != null) {
            return this.dateBeg.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return null;
    }

    public LocalDateTime getLocalDateTimeDateEdit() {
        if (this.dateEdit != null) {
            return this.dateEdit.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return null;
    }

    public String getDateBegString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getDateBeg() != null) return dateFormat.format(this.getDateBeg());
        return null;
    }

    public String getDateEditString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getDateEdit() != null) return dateFormat.format(this.getDateEdit());
        return null;
    }

    public String getStatusRUCaption(){
         switch (this.getStatus()) {
             case error -> {return "ошибка";}
             case progress -> {return "в процессе";}
             case success -> {return "завершена";}
             default -> {return "";}
         }
    }
}
