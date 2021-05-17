package com.example.card.web.flux;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Document
public class Card {

   @NotNull
   @NotBlank(message = "code can't be empty")
    @Id
    private String code;
    @NotNull
    @NotBlank(message = "title can't be empty")
    private String title;
    @NotNull
    @NotBlank(message = "date can't be empty")
    private LocalDate date;
    @NotNull
    @NotBlank(message = "number can't be empty")
    private String number;
    @NotNull
    @NotBlank(message = "TypeCard can't be empty")
    private TypeCard type;


    public Card() {
    }

    public Card(String code, String title, LocalDate date, String number, TypeCard type) {
        this.code = code;
        this.title = title;
        this.date = date;
        this.number = number;
        this.type = type;
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
