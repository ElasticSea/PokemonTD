package com.pixelthieves.pokemontd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixelthieves.core.services.Achievement;
import com.pixelthieves.core.services.GameService;
import com.pixelthieves.core.utils.Param;
import com.pixelthieves.core.utils.ParamHolder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.process;

public class Main {

    public static final boolean CAN_SIGN_IN = true;

    public static void main(String[] args) {
        ParamHolder params = new ParamHolder(args);
        if(params.getParam("-h","--h", "-help","--help") != null){
            System.out.println("Usage: -fullscreen                   triggers fullscreen.");
            System.out.println("       -resolution [width] [height]  sets application resoltuion.");
            System.exit(0);
        }
        Param resolution = params.getParam("-resolution");
        if (params.getParam("-textures") != null) {
            Settings texturePackerSettings = new Settings();
            texturePackerSettings.edgePadding = false;
            texturePackerSettings.combineSubdirectories = true;

            String source = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile().toString() +
                    "/unprocessed/textures/";
            String destination = new File(".").toString() + "/data/textures/";
            process(texturePackerSettings, source, destination, "packed");

        }
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokemonTD";
        cfg.useGL20 = true;
        cfg.width = resolution != null ? Integer.valueOf(resolution.getArgument(0)) : 800;
        cfg.height = resolution != null ? Integer.valueOf(resolution.getArgument(1)) : 640;
        cfg.fullscreen = params.getParam("-fullscreen") != null;

        new LwjglApplication(new App(new GameService() {
            private Map<String, String> leaderboard;

            @Override
            public boolean canSingIn() {
                return CAN_SIGN_IN;
            }

            @Override
            public boolean isSignedIn() {
                return true;
            }

            @Override
            public void signIn(Callable handler) {
                if(CAN_SIGN_IN && handler!=null){
                    try {
                        handler.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
                }
                return leaderboard;
            }

        }, args), cfg);
    }
}
