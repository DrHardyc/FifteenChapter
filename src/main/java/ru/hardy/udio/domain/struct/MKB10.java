package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
public class MKB10 {
    private Long id;

    private String ds_code;
    private String mkb_name;
    private Date date_beg;
    private Date date_end;
}
