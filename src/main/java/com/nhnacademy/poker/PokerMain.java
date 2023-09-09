package com.nhnacademy.poker;

/**
 * Hello world!
 */
public class PokerMain {
    public static void main(String[] argss) {
        Table table = new Table();
        table.startGame();

        User user1 = new User();
        User user2 = new User();

        table.game(user1, user2);
    }
}
