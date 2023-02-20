package ru.hardy.udio.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "udio_sec")
public class Token{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;
    private String lpu;
}
