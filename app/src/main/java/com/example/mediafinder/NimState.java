package com.example.mediafinder;

public class NimState {
    private int count = 0;
    private int turn = 0;

    public int getCount() {
        return count;
    }

    public int getTurn() {
        return turn;
    }

    public int player() {
        return turn % 2 + 1;
    }

    public void increment( int by ) {
        count += by;
        turn++;
    }

    public boolean gameOver() {
        return count >= 20;
    }
}
