package ru.hardy.udio.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(schema = "udio_util")
public class AppParam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "app_param_seq")
    @SequenceGenerator(name = "app_param_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String value;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
