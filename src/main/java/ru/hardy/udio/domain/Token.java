package ru.hardy.udio.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "udio_sec")
public class Token{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "token_seq")
    @SequenceGenerator(name = "token_seq", allocationSize = 1)
    private Long id;

    private String key;
    private String lpu;
}
