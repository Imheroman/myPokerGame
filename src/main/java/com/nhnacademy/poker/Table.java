package com.nhnacademy.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private ArrayList<Card> cards;
    private int size = 52;
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

    public void participationUser(User user) {
        int userTurnNumber = this.nextTurnNumber();
        System.out.println("User" + userTurnNumber + "(이)가 게임에 참가하였습니다. 카드를 분배하겠습니다.");

        user.setTurnNumber(userTurnNumber);
        user.setCard(this.baseUserCard());
        System.out.println("User" + userTurnNumber + " 카드가 분배됐습니다. 카드를 확인해 주세요.");
    }

    private ArrayList<Card> baseUserCard() {
        ArrayList<Card> tableCardList = this.getCards();
        ArrayList<Card> userCardList = new ArrayList<>(3);

        if (this.getSize() < 3) {
            throw new IllegalArgumentException("카드의 수가 부족합니다.");
        } else {
            for (int index = 0; index < 3; index++) {
                Card card = tableCardList.get(index);
                userCardList.add(card);
                tableCardList.remove(card);
            }
        }

        return userCardList;
    }

    public void game(User... users) {
        int userVolume = users.length;

        for (int i = 0; i < userVolume; i++) {
            this.userSetting(users[i]);
        }

    }

//    public User userSetting() {
//        User user = new User();
//
//        this.participationUser(user);
//        user.printUserCards();
//
//        return user;
//    }

    private void userSetting(User user) {
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
