package ru.hardy.udio.service;

import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.F003;
import ru.hardy.udio.domain.struct.handbook.HandBookValues;
import ru.hardy.udio.service.handbookservice.HandBookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class F003Service{
    private List<F003> getF003s(BufferedReader in) throws IOException {
        List<List<HandBookValues>> listHandBooksValues = new HandBookService().getHandBook(in).getDirValues();
        ArrayList<F003> listF003 = new ArrayList<>();
        for (List<HandBookValues> handBooksValues : listHandBooksValues){
            F003 f003 = new F003();
            for (HandBookValues handBookValues : handBooksValues){
                switch (handBookValues.getColumn()) {
                    case "tf_okato" -> f003.setTf_okato(handBookValues.getValue());
                    case "mcod" -> f003.setMcod(handBookValues.getValue());
                    case "nam_mop" -> f003.setNam_mop(handBookValues.getValue());
                    case "nam_mok" -> f003.setNam_mok(handBookValues.getValue());
                    case "inn" -> f003.setInn(handBookValues.getValue());
                    case "Ogrn" -> f003.setOgrn(handBookValues.getValue());
                    case "KPP" -> f003.setKpp(handBookValues.getValue());
                    case "index_j" -> f003.setIndex_j(handBookValues.getValue());
                    case "addr_j" -> f003.setAddr_j(handBookValues.getValue());
                    case "okopf" -> f003.setOkopf(handBookValues.getValue());
                    case "vedpri" -> f003.setVedpri(handBookValues.getValue());
                    case "org" -> f003.setOrg(handBookValues.getValue());
                    case "fam_ruk" -> f003.setFam_ruk(handBookValues.getValue());
                    case "im_ruk" -> f003.setIm_ruk(handBookValues.getValue());
                    case "ot_ruk" -> f003.setOt_ruk(handBookValues.getValue());
                    case "phone1" -> f003.setPhone1(handBookValues.getValue());
                    case "phone2" -> f003.setPhone2(handBookValues.getValue());
                    case "fax1" -> f003.setFax1(handBookValues.getValue());
                    case "fax2" -> f003.setFax2(handBookValues.getValue());
                    case "e_mail1" -> f003.setE_mail1(handBookValues.getValue());
                    case "e_mail2" -> f003.setE_mail2(handBookValues.getValue());
                    case "www" -> f003.setWww(handBookValues.getValue());
                    case "d_edit" -> f003.setD_edit(handBookValues.getValue());
                    case "d_begin" -> f003.setD_begin(handBookValues.getValue());
                    case "d_end" -> f003.setD_end(handBookValues.getValue());
                }
            }
            listF003.add(f003);
        }
        return listF003;
    }
    public boolean Check(String par) throws IOException {
        if (par.isEmpty()) return false;
        UtilService su = new UtilService();
        F003Service f003Service = new F003Service();
        List<F003> f003s = f003Service.getF003s(su.getHBBufferedReader("http://nsi.ffoms.ru/nsi-int/api/data?identifier=F003&filters=mcod%7C" + par));
        for (F003 f003 : f003s){
            if (f003.getMcod().equals(par) && f003.getD_end().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public F003 getWithCode(int code){
        F003Service f003Service = new F003Service();
        UtilService su = new UtilService();
        List<F003> f003s;
        try {
            f003s = f003Service.getF003s(su.getHBBufferedReader("http://nsi.ffoms.ru/nsi-int/api/data?identifier=F003&filters=mcod%7C" + code));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return f003s.get(0);
    }
}
