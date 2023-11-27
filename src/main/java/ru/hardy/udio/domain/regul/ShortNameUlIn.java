package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "short_name_ul_in")
public class ShortNameUlIn {
    @Id
    private Long id;

    private String nameShort;
}
