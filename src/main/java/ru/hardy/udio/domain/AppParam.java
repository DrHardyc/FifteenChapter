package ru.hardy.udio.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(schema = "udio_sec")
public class AppParam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String value;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
