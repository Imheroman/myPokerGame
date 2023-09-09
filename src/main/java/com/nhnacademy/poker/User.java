package com.nhnacademy.poker;

import java.util.ArrayList;

public class User {
    private int turnNumber;
    private ArrayList<Card> userCards;
    private String rank;

    public User() {

    }

    public void addCard(Card card) {
        this.userCards.add(card);
    }

    public void printUserCards() {
        StringBuilder sb = new StringBuilder();

        sb.append("User").append(getTurnNumber()).append(": ");
        for (int index = 0; index < userCards.size(); index++) {
            sb.append("<").append(userCards.get(index)).append("> ");
        }
        System.out.println(sb.toString());
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public ArrayList<Card> getUserCards() {
        return userCards;
    }

    public void setCard(ArrayList<Card> cards) {
        this.userCards = cards;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
