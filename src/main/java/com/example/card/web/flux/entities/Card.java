package com.example.card.web.flux.entities;

import com.example.card.web.flux.valueobjects.TypeCard;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document
public class Card {
    @NotNull
    @NotBlank(message = "code can't be empty")
    private String code;
    @NotNull
    @NotBlank(message = "title can't be empty")
    private String title;
    @NotNull
    @NotBlank(message = "date can't be empty")
    private LocalDate date;
    @NotNull
    @Id
    @NotBlank(message = "number can't be empty")
    private String number;
    private TypeCard type;


    public Card(String code, String title, LocalDate date, String number) {
        this.code = code;
        this.title = title;
        this.date = date;
        this.number = number;
    }

    public Card() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
