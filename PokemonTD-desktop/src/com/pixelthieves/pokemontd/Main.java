package com.pixelthieves.pokemontd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixelthieves.core.utils.ParamHolder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.process;

public class Main {
    public static void main(String[] args) {
        Settings texturePackerSettings = new Settings();
        texturePackerSettings.edgePadding = false;
        texturePackerSettings.combineSubdirectories = true;

        String source = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile().toString() +
                "/unprocessed/textures/";
        String destination = new File(".").toString() + "/data/textures/";
        process(texturePackerSettings, source, destination, "packed");

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokemonTD";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 640;
        if (new ParamHolder(args).getParam("-fullscreen") != null) {
            cfg.fullscreen = true;
            cfg.width = 2560;
            cfg.height = 1440;
        }

        new LwjglApplication(new App(new GameService() {
            private Map<String, String> leaderboard;

            @Override
            public boolean isSignedIn() {
                return true;
            }

            @Override
            public void signIn() {

            }

            @Override
            public void signOut() {

            }

            @Override
            public void skipSignIn() {

            }

            @Override
            public void submitScore(int score) {

            }

            @Override
            public void submitAchievement(Achievement achievement) {

            }

            @Override
            public void showAchievementsRequested() {

            }

            @Override
            public void showLeaderboardsRequested() {

            }

            @Override
            public Map<String, String> getLeaderboard() {
                if (leaderboard == null) {
                    leaderboard = new HashMap<String, String>();
                    for (int i = 0; i < 8; i++) {
                        leaderboard.put("Tomas Cupal "+i, "879543");
                    }
                }
                return leaderboard;
            }

        }, args), cfg);
    }
}
