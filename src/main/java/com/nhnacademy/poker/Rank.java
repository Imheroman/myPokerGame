package com.nhnacademy.poker;

import java.util.ArrayList;

public class Rank {
    private final String[] rank =
            {"Straight Flush", "Four Card", "Full House", "Flush", "Straight", "Triple", "Two Pair", "One Par", "Top"};
    private String[] cardIndex = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    private int[] cardIndexCount = new int[13];
    private int rankIndex = 0;

    public Rank() {

    }

    private void countCardIndex(User user) {
        ArrayList<Card> cards = user.getUserCards();

        for (Card card : cards) {
            String num = card.getNumber();

            for (int index = 0; index < cardIndex.length; index++) {
                if (cardIndex[index].equals(num)) {
                    cardIndexCount[index]++;
                    break;
                }
            }
        }
    }

    public int getRank(User user) {
        countCardIndex(user);
        int[] numberGroup = this.getCardIndexCount();
        int bigNumber = 0;
        int pairCheck = 0;

        for (int index = 0; index < numberGroup.length; index++) {
            int cardVolume = numberGroup[index];

            if (bigNumber < cardVolume) {
                bigNumber = cardVolume;
            }

            if (cardVolume == 2) {
                pairCheck++;
            }
        }

//        "Straight Flush", "Four Card", "Full House", "Flush", "Straight", "Triple", "Two Pair", "One Par", "Top"

        if (bigNumber == 4) {
            this.setRankIndex(1);
        } else if (bigNumber == 3) {
            this.setRankIndex(5);
        } else if (bigNumber == 2 && pairCheck >= 2) {
            this.setRankIndex(6);
        } else if (bigNumber == 2 && pairCheck == 1) {
            this.setRankIndex(7);
        } else if (bigNumber == 1) {
            this.setRankIndex(8);
        }

        return this.getRankIndex();
    }

    public int getRankIndex() {
        return this.rankIndex;
    }

    public void setRankIndex(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public int[] getCardIndexCount() {
        return cardIndexCount;
    }
}
