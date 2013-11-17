package com.pixelthieves.pokemontd;

/**
 * User: Seda
 * Date: 20.10.13
 * Time: 20:34
 */

public class Score {
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    public void increase(int count) {
        score += count;
    }
}
