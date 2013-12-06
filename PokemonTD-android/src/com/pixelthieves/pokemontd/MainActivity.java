package com.pixelthieves.pokemontd;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.leaderboard.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MainActivity extends LibgdxBaseGameActivity implements GameService {

    private final static java.util.Map<Achievement, Integer> achievemtsMap = getAchievemtsMap();
    private final GameServiceSession session;
    private Map<String, String> leaderboard;
    private boolean leaderboardLoading;
    private App application;

    public MainActivity() {
        session = new GameServiceSession();
    }

    private static java.util.Map<Achievement, Integer> getAchievemtsMap() {
        java.util.Map<Achievement, Integer> map = new HashMap<Achievement, Integer>();
        map.put(Achievement.Champion, R.string.achievement_champion);
        map.put(Achievement.Ethernal, R.string.achievement_ethernal);
        map.put(Achievement.Immortal, R.string.achievement_immortal);
        map.put(Achievement.Keeper, R.string.achievement_keeper);
        map.put(Achievement.Trifty, R.string.achievement_trifty);
        map.put(Achievement.Healty, R.string.achievement_healthy);
        map.put(Achievement.Novice, R.string.achievement_novice);
        map.put(Achievement.Jeweller, R.string.achievement_jeweller);
        return map;
    }

    @Override
    public boolean canSingIn() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD;
    }

    @Override
    public boolean isSignedIn() {
        return super.isSignedIn();
    }

    @Override
    public void signIn(final Callable handler) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                beginUserInitiatedSignIn();
                if (handler != null) {
                    try {
                        handler.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
            //showMessage(R.string.your_progress_will_be_uploaded);
            for (int score : session.getScores()) {
                getGamesClient().submitScore(getString(getLeaderboardType(application.getDifficulty())), score);
            }
            for (Achievement achievement : session.getAchievements()) {
                getGamesClient().unlockAchievement(getString(achievemtsMap.get(achievement)));
            }
            session.clear();
        }
    }

    private int getLeaderboardType(Difficulty difficulty) {
        /*switch (difficulty) {
            case Easy:
                return R.string.leaderboard_easy;
            case Normal:
                return R.string.leaderboard_normal;
            case Hard:
                return R.string.leaderboard_hard;
            case Insane:
                return R.string.leaderboard_insane;
        }        */
        return R.string.leaderboard_classic;
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
        getGamesClient().loadTopScores(listener, getString(getLeaderboardType(application.getDifficulty())), span,
                leaderboardCollection, results, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = detectOpenGLES20();
        application = new App(this);
        initialize(application, cfg);
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

    private boolean detectOpenGLES20() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }
}