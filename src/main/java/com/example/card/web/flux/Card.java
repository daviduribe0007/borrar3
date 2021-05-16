package com.example.card.web.flux;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Document
public class Card {

    @NotNull
    @NotBlank(message = "title can't be empty")
    private String title;
    @NotNull
    @NotBlank(message = "date can't be empty")
    private LocalDateTime date;
    @NotNull
    @NotBlank(message = "number can't be empty")
    private String number;
    @NotNull
    @NotBlank(message = "TypeCard can't be empty")
    private TypeCard type;
    @Id
    @NotNull
    @NotBlank(message = "code can't be empty")
    private String code;

    public Card() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypeCard getType() {
        return type;
    }

    public void setType(TypeCard type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
