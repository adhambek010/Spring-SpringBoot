package uz.developer.magistratura_ishi.model.university;

import lombok.Data;

@Data
public class FakultetReceiveModel {
    private String identifier;
    private String name;
    private String dekan;

    public FakultetReceiveModel(String name, String dekan) {
        this.name = name;
        this.dekan = dekan;
    }
}
