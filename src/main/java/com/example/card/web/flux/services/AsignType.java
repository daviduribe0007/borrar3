package com.example.card.web.flux.services;

import com.example.card.web.flux.valueobjects.TypeCard;

public class AsignType {

    public static TypeCard generateType(String code) {
        if(code ==null || code == ""){
            throw new IllegalArgumentException(" The code cant be null or empty");
        }
        switch (code) {
            case "03":
                return TypeCard.MasterCard;
            case "06":
                return TypeCard.VISA;
            case "12":
                return TypeCard.PRIME;
            default:
                throw new IllegalArgumentException(" The code " + code + " are not valid");
        }
    }
}
