package ru.hardy.udio.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.Token;
import ru.hardy.udio.repo.TokenRepo;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    private final Base64 base64 = new Base64();

    public Token genToken(String lpu){
        if (checkLpu(lpu)){ // false - если не находим
            return tokenRepo.findByLpu(lpu);
        }
        String generatedString = UUID.randomUUID().toString();
        Token token = new Token();
        token.setLpu(lpu);
        token.setKey(generatedString);
        return tokenRepo.save(token);
    }

    private boolean checkLpu(String lpu){
        return tokenRepo.findByLpu(lpu) != null;
    }

    public boolean checkHashKey(String lpu, String keyLpu){
        if (keyLpu.isEmpty()){
            return false;
        }
        return getKeyWithLpu(lpu).equals(new String(base64.decode((keyLpu.getBytes()))));
    }

    private String getKeyWithLpu(String lpu){
        return tokenRepo.findByLpu(lpu).getKey();
    }

    public String getHashWithKey(String key) {
        return new String(base64.encode(key.getBytes()));
    }
}
