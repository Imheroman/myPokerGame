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

    public Table() {

    }

    public void startGame() {
        this.cards = new CardList().getCardList();
        Collections.shuffle(cards);
        System.out.println(setGameComment);
    }

    private Card oneMoreCard() {
        Card card = cards.get(this.getSize());
        this.size--;

        return card;
    }

    private void participationUser(User user) {
        int userTurnNumber = this.nextTurnNumber();
        user.setTurnNumber(userTurnNumber);
        System.out.println("User" + userTurnNumber + "(이)가 게임에 참가하였습니다. 카드를 분배하겠습니다.");

        user.setCard(this.firstUserCardSetting());
        System.out.println("User" + userTurnNumber + " 카드가 분배됐습니다. 카드를 확인해 주세요.");
    }

    private ArrayList<Card> firstUserCardSetting() {
        ArrayList<Card> tableCardList = this.getCards();
        ArrayList<Card> userCardList = new ArrayList<>(3);

        if (this.getSize() < 2) {
            throw new IllegalArgumentException("카드의 수가 부족합니다.");
        } else {
            for (int index = 0; index < 3; index++) {
                Card card = tableCardList.get(index);
                userCardList.add(card);
                tableCardList.remove(card);
                size--;
            }
        }

        return userCardList;
    }

    private ArrayList<User> usersSetting(User... userGroup) {
        ArrayList<User> userSet = new ArrayList<>();

        for (User user : userGroup) {
            this.baseUserSetting(user);
            userSet.add(user);
        }

        return userSet;
    }

    public void game(User... userGroup) {
        this.users = usersSetting(userGroup);
        this.checkToBeOrDie();

        while (this.users.size() > 1) {
            for (User user : users) {
                user.addCard(this.oneMoreCard());
            }

            this.checkToBeOrDie();
        }

    }

    private void checkToBeOrDie() {
        Scanner sc = new Scanner(System.in);
        for (int index = 0; index < this.users.size(); index++) {
            User user = this.users.get(index);
            String userName = "User" + user.getTurnNumber();
//            int userTurnNumber =

            System.out.print(userName + " 게임을 계속 진행하겠습니까? (Y/N): ");
            String toBeOrDie = sc.nextLine();

            if (toBeOrDie.equals("Y")) {
                System.out.println(userName + "(이)가 게임을 계속 진행합니다.");
                continue;
            } else {
                System.out.println(userName + "(이)가 죽었습니다.");
                this.users.remove(index);
            }
        }

    }

    private void baseUserSetting(User user) {
        this.participationUser(user);
        user.printUserCards();
    }

    private int nextTurnNumber() {
        return this.turnNumber++;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getSize() {
        return size;
    }
}
