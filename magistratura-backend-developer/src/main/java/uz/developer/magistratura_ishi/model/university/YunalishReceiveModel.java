package uz.developer.magistratura_ishi.model.university;

import lombok.Data;

@Data
public class YunalishReceiveModel {
    private String identifier;
    private String name;
    private String fakultet;

    public YunalishReceiveModel(String name, String fakultet) {
        this.name = name;
        this.fakultet = fakultet;
    }
}
