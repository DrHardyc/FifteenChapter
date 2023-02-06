package ru.hardy.udio.domain.struct.handbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Handbook{
    private String result;
    private String resultText;
    private String resultCode;
    private String total;
    private String isHierarchical;
    @JsonProperty("list")
    private List<List<HandBookValues>> dirValues;
}
