package com.nhnacademy.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Table {
    private ArrayList<Card> cards;
    private ArrayList<User> users;
    private int size = 51;
    private int turnNumber = 1;
    private final String setGameComment =
            "=====================NHN 오락실에 오신 걸 환영합니다. 7포커 게임을 시작합니다.=====================";
    private final String[] rankList =
            {"Straight Flush", "Four Card", "Full House", "Flush", "Straight", "Triple", "Two Pair", "One Par", "Top"};

    public Table() {

    }

    public void startGame() {
        this.cards = new CardList().getCardList();
        Collections.shuffle(cards);
        System.out.println(setGameComment);
    }

    private Card oneMoreCard() {
        if (size < 0) {
            throw new IllegalArgumentException("카드의 수가 부족합니다.");
        } else {
            Card card = cards.get(this.getSize());
            cards.remove(this.getSize());
            this.size--;

            return card;
        }
    }

    private void participationUserInGame(User user) {
        int userTurnNumber = this.nextTurnNumber();
        user.setTurnNumber(userTurnNumber);
        System.out.println("User" + userTurnNumber + "(이)가 게임에 참가하였습니다. 카드를 분배하겠습니다.");

        user.setCard(this.firstUserCardSetting());
        System.out.println("User" + userTurnNumber + " 카드가 분배됐습니다. 카드를 확인해 주세요.");
    }

    private ArrayList<Card> firstUserCardSetting() {
        ArrayList<Card> userCardList = new ArrayList<>(3);

        if (this.getSize() < 2) {
            throw new IllegalArgumentException("카드의 수가 부족합니다.");
        } else {
            for (int index = 0; index < 3; index++) {
                Card card = cards.get(size);
                userCardList.add(card);
                cards.remove(size);
                size--;
            }
        }

        return userCardList;
    }

    private ArrayList<User> usersSetting(User... userGroup) {
        ArrayList<User> userSet = new ArrayList<>();

        for (User user : userGroup) {
            this.participationUserInGame(user);
            user.printUserCards();
            userSet.add(user);
        }
        System.out.println("================================================");

        return userSet;
    }

    public void game(User... userGroup) {
        this.users = usersSetting(userGroup);
        int firstCardVolume = 3;
        String winner = "";

        while (this.users.size() > 1 && firstCardVolume < 7) {
            this.checkToBeOrDie();
            if (this.users.size() <= 1) {
                System.out.println("================================================");
                break;
            }

            for (User user : users) {
                user.addCard(this.oneMoreCard());
                user.printUserCards();
            }
            System.out.println("================================================");
            firstCardVolume++;
        }

        winner = whoIsWinner();
        System.out.println(winner);
    }

    private String whoIsWinner() {
        ArrayList<User> userGroup = this.getUsers();
        StringBuilder winner = new StringBuilder();
        int userTurnNumber = 0;
        int sameUserIndex = 0;
        int highest = 100;
        int countSameRank = 0;

        if (userGroup.size() == 1) {
            userTurnNumber = userGroup.get(0).getTurnNumber();
            winner.append("Winner is User").append(userTurnNumber).append(" because of only suvived ");

            return winner.toString();
        } else {
            for (int index = 0; index < userGroup.size(); index++) {
                User user = userGroup.get(index);
                int rankIndex = new Rank().getRank(user);

                if (highest > rankIndex) {
                    highest = rankIndex;
                    userTurnNumber = index + 1;
                    countSameRank = 0;
                } else if (highest == rankIndex) {
                    sameUserIndex = index + 1;
                    countSameRank++;
                }
            }
        }

        if (countSameRank == 0) {
            winner.append("Winner is User").append(userTurnNumber).append(" with ").append(rankList[highest]);
        } else {
            System.out.println(countSameRank);
            winner.append("User").append(userTurnNumber).append(" and ").append("User").append(sameUserIndex)
                    .append(" are drawed with ").append(rankList[highest]);
        }

        return winner.toString();
    }

    private void checkToBeOrDie() {
        Scanner sc = new Scanner(System.in);
        for (int index = 0; index < this.users.size(); index++) {
            User user = this.users.get(index);
            String userName = "User" + user.getTurnNumber();

            System.out.print(userName + " 게임을 계속 진행하겠습니까? (Y/N): ");
            String toBeOrDie = sc.nextLine();

            if (toBeOrDie.equals("Y")) {
                System.out.println(userName + "(이)가 카드를 추가로 받습니다.");
            } else {
                System.out.println(userName + "(이)가 죽었습니다.");
                this.users.remove(index);
            }
        }

    }

    private int nextTurnNumber() {
        return this.turnNumber++;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getSize() {
        return size;
    }
}