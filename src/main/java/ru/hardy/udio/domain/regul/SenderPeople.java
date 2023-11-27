package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Table(schema = "regul", name = "sender_people")
public class SenderPeople {
    @Id
    private Long id;

    private String position;
    private String phone;
    private String email;
//    @MappedCollection(idColumn = "senderpeople_id")
//    private SNPSenderPeople snpSenderPeople;
}
