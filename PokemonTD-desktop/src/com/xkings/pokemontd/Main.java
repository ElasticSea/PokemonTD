package com.xkings.pokemontd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.File;

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

        System.out.println(new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile());

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokemonTD";
        cfg.useGL20 = true;
        cfg.width = 1920;
        cfg.height = 1080;
        cfg.fullscreen = true;

        new LwjglApplication(new App(args), cfg);
    }
}
