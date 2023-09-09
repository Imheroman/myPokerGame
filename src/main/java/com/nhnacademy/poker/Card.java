package com.nhnacademy.poker;

public class Card {
    private final String number;
    private final Suit suit;

    public Card(String num, Suit suit) {
        this.number = num;
        this.suit = suit;
    }

//    public String getNumber() {
//        return number;
//    }
//
//    public Suit getSuit() {
//        return suit;
//    }

    @Override
    public String toString() {
        return suit.getSuit() + number;
    }
}
