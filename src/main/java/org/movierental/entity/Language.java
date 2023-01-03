package org.movierental.entity;

import lombok.Data;

@Data
public class Language {

    private Long languageId;
    private String name;

    public Language(Long languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }
}
