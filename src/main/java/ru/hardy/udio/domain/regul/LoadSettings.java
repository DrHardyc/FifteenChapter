package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "user_dir_file")
public class LoadSettings {
    @Id
    private Long id;

    private String pathIn;
    private String pathOut;
    private Long userId; //Сделано так потому что User реализован посредством JPA а не SD jdbc
}
