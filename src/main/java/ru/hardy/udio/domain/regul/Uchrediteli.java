package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.39 */
@Getter
@Setter
@Table(schema = "regul", name = "uchrediteli")
public class Uchrediteli {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uchrediteli_id")
    private ZapretRaspDoliami zapretRaspDoliami;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrULRos uchrULRos;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrULIn uchrULIn;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrFL uchrFL;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrPIF uchrPIF;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrRFSubMO uchrRFSubMO;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchPIF uchPIF;
    @MappedCollection(idColumn = "uchrediteli_id")
    private UchrDogInvTov uchrDogInvTov;

}
