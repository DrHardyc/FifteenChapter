package ru.hardy.udio.service.handbookservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hardy.udio.domain.struct.handbook.Handbook;

import java.io.BufferedReader;
import java.io.IOException;

public class HandBookService{
    public Handbook getHandBook(BufferedReader in) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(in, Handbook.class);
    }
}
