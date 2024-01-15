package ru.hardy.udio.domain.regul.importfromfms;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlatelMapper implements RowMapper<Platel> {

    public Platel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Platel platel = new Platel();
        platel.setNamef(rs.getString("namef"));
        platel.setNamesc(rs.getString("namesc"));
        platel.setInn(rs.getString("inn"));
        platel.setOgrn(rs.getString("ogrn"));
        platel.setKpp(rs.getString("kpp"));
        platel.setRegorg(rs.getString("regorg"));
        platel.setStatus(rs.getString("status"));
        platel.setVidreg(rs.getString("vidreg"));
        platel.setDtstart(rs.getString("dtstart"));
        platel.setDtend(rs.getString("regnpf"));
        platel.setPf(rs.getString("pf"));
        platel.setRegnfoms(rs.getString("regnfoms"));
        platel.setFoms(rs.getString("foms"));
        platel.setFilen(rs.getString("filen"));
        platel.setDtpris(rs.getString("dtpris"));
        platel.setPrin(rs.getString("prin"));
        platel.setKuser(rs.getString("kuser"));
        platel.setChis(rs.getString("chis"));
        platel.setVpdt(rs.getString("vpdt"));
        platel.setVpkto(rs.getString("vpkto"));
        platel.setNum(rs.getString("num"));
        platel.setBik(rs.getString("bik"));
        platel.setTip(rs.getString("tip"));
        platel.setRegnum(rs.getString("regnum"));
        platel.setDtreg(rs.getString("dtreg"));
        platel.setDtzap(rs.getString("dtzap"));
        platel.setOkato(rs.getString("okato"));
        platel.setIndeks(rs.getString("indeks"));
        platel.setRegion(rs.getString("region"));
        platel.setRaion(rs.getString("raion"));
        platel.setGorod(rs.getString("gorod"));
        platel.setNaspunkt(rs.getString("naspunkt"));
        platel.setStreet(rs.getString("street"));
        platel.setDom(rs.getString("dom"));
        platel.setKorp(rs.getString("korp"));
        platel.setKvart(rs.getString("kvart"));
        platel.setKodgorod(rs.getString("kodgorod"));
        platel.setTel(rs.getString("tel"));

        return platel;
    }
}
