package com.nhnacademy.poker;

import java.util.ArrayList;

public class CardList {
    private String[] numList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private ArrayList<Card> cardList = new ArrayList();

    public CardList() {
        setCard();
    }

    private void setCard() {
        for (int spade = 0; spade < 13; spade++) {
            cardList.add(new Card(numList[spade], Suit.SPD));
        }

        for (int diamond = 0; diamond < 13; diamond++) {
            cardList.add(new Card(numList[diamond], Suit.DIA));
        }

        for (int heart = 0; heart < 13; heart++) {
            cardList.add(new Card(numList[heart], Suit.HRT));
        }

        for (int clob = 0; clob < 13; clob++) {
            cardList.add(new Card(numList[clob], Suit.CLO));
        }
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }
}
