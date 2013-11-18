package com.pixelthieves.pokemontd;

import android.os.Bundle;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.leaderboard.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends LibgdxBaseGameActivity implements GameService {

    private final static java.util.Map<Achievement, Integer> achievemtsMap = getAchievemtsMap();
    private final GameServiceSession session;
    private Map<String, String> leaderboard;
    private boolean leaderboardLoading;

    public MainActivity() {
        session = new GameServiceSession();
    }

    private static java.util.Map<Achievement, Integer> getAchievemtsMap() {
        java.util.Map<Achievement, Integer> map = new HashMap<Achievement, Integer>();
        map.put(Achievement.Ethernal, R.string.achievement_ethernal);
        map.put(Achievement.Immortal, R.string.achievement_immortal);
        map.put(Achievement.Keeper, R.string.achievement_keeper);
        map.put(Achievement.Trifty, R.string.achievement_trifty);
        map.put(Achievement.Healty, R.string.achievement_heathy);
        return map;
    }

    @Override
    public boolean isSignedIn() {
        return super.isSignedIn();
    }

    @Override
    public void signIn() {
        beginUserInitiatedSignIn();
    }

    @Override
    public void signOut() {
        super.signOut();
    }

    @Override
    public void skipSignIn() {
        showMessage(R.string.sign_in_why);
    }

    private void showMessage(final int id) {
        final MainActivity context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, context.getString(id), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void submitScore(int score) {
        session.addScore(score);
        process();
    }

    @Override
    public void submitAchievement(Achievement achievement) {
        session.addAchievement(achievement);
        process();
    }

    private void process() {
        if (isSignedIn() && !session.isEmpty()) {
            showMessage(R.string.your_progress_will_be_uploaded);
            for (int score : session.getScores()) {
                getGamesClient().submitScore(getString(R.string.leaderboard_alpha), score);
            }
            for (Achievement achievement : session.getAchievements()) {
                getGamesClient().unlockAchievement(getString(achievemtsMap.get(achievement)));
            }
            session.clear();
        }
    }

    @Override
    public void showAchievementsRequested() {

    }

    @Override
    public void showLeaderboardsRequested() {

    }

    @Override
    public Map<String, String> getLeaderboard() {
        if (leaderboard == null && !leaderboardLoading) {
            loadLeaderboard(LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC, 10);
        }
        return leaderboard;
    }

    public void loadLeaderboard(int span, int leaderboardCollection, int results) {
        leaderboardLoading = true;
        OnLeaderboardScoresLoadedListener listener = new OnLeaderboardScoresLoadedListener() {
            @Override
            public void onLeaderboardScoresLoaded(int i, LeaderboardBuffer leaderboards,
                                                  LeaderboardScoreBuffer leaderboardScores) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                for (LeaderboardScore leaderboardScore : leaderboardScores) {
                    map.put(leaderboardScore.getScoreHolder().getDisplayName(), leaderboardScore.getDisplayScore());
                }
                leaderboard = map;
                leaderboardLoading = false;
            }
        };
        getGamesClient().loadTopScores(listener, getString(R.string.leaderboard_alpha), span, leaderboardCollection,
                results, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        initialize(new App(this), cfg);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {

    }

    @Override
    public int getLogLevel() {
        return 0;
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {
        process();
    }
}