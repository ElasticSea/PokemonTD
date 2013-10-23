package com.xkings.pokemontd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.File;
import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import static com.badlogic.gdx.tools.imagepacker.TexturePacker2.process;

public class Main {
	public static void main(String[] args) {
        Settings texturePackerSettings = new Settings();
        texturePackerSettings.paddingX = 0;
        texturePackerSettings.paddingY = 0;
        texturePackerSettings.edgePadding = false;

        String source = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile().toString() + "/unprocessed/textures/";
        String destination = new File(".").toString() + "/data/textures/";
        process(source, destination, "packed");

        System.out.println(new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile());

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PokemonTD";
		cfg.useGL20 = true;
		cfg.width = 1366;
		cfg.height = 768;
		
		new LwjglApplication(new App(args), cfg);
	}
}
