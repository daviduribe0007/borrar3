package com.example.card.web.flux.services;

import com.example.card.web.flux.valueobjects.TypeCard;

public class AsignType {

    public static TypeCard generateType(String code) {
        if (verifyCode(code)) {
            throw new IllegalArgumentException(" The code cant be null or empty");
        }

        return getTypeCard(code);
    }

    private static boolean verifyCode(String code) {
        return code == null || code == "";
    }

    private static TypeCard getTypeCard(String code) {
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
