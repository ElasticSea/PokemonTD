package com.pixelthieves.pokemontd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 11/18/13.
 */
public class GameServiceSession {
    private final List<Integer> scores = new ArrayList<Integer>();
    private final List<Achievement> achievements = new ArrayList<Achievement>();

    public void addScore(int score) {
        scores.add(score);
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public boolean isEmpty() {
        return scores.size() == 0 && achievements.size() == 0;
    }

    public void clear() {
        scores.clear();
        achievements.clear();
    }
}
