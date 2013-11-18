package com.pixelthieves.pokemontd;

import java.util.Map;
/**
 * Created by Tomas on 11/18/13.
 */
public interface GameService {
    public boolean isSignedIn();

    public void signIn();

    public void signOut();

    public void skipSignIn();

    public void submitScore(int score);

    public void submitAchievement(Achievement achievement);

    public void showAchievementsRequested();

    public void showLeaderboardsRequested();

    public Map<String, String> getLeaderboard();
}
