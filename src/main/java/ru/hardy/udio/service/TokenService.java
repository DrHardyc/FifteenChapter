package ru.hardy.udio.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.Token;
import ru.hardy.udio.repo.TokenRepo;
import ru.hardy.udio.security.SecurityUtils;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;


    public boolean checkToken(String token){
        return tokenRepo.findToken(getCryptToken(token)) != null;
    }

    private String getKeyWithLpu(String lpu){
        return tokenRepo.findByLpu(lpu).getKey();
    }

    public int getCodeMOWithToken(String token) {
        if (tokenRepo.findToken(token) != null) {
            return tokenRepo.findToken(token).getLpu();
        } else return 0;
    }

    public String getCryptToken(String token){
        SecurityUtils securityUtils = new SecurityUtils();
        return securityUtils.encodeString(token);
    }

    public String getUnCryptToken(String cryptToken){
        SecurityUtils securityUtils = new SecurityUtils();
        return securityUtils.decodeString(cryptToken);
    }

    public void addNewToken(String token, int codeMO){
        tokenRepo.save(new Token(token, codeMO));
    };
}
