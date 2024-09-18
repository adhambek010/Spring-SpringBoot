package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

@Data
public class MaqolaDTO {
    private String name;
    private String author;
    private String username;

    private String file;
    private String maqolaId;

    public MaqolaDTO(String name, String author, String username) {
        this.name = name;
        this.author = author;
        this.username = username;
    }
}

