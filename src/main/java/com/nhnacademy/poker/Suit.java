package com.nhnacademy.poker;

public enum Suit {
    CLO("♣"),
    HRT("♥"),
    DIA("♦"),
    SPD("♠");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

//    @Override
//    public String toString() {
//        return suit;
//    }
}


