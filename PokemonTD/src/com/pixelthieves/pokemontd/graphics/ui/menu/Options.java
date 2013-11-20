package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
public class Options extends ChildTab {

    private Menu menu;
    private final Button guiButton;
    private final Button musicButton;
    private final GUI gui;
    private static final Music theme = Assets.getMusic("theme.ogg");

    {
        theme.setLooping(true);
        theme.setVolume(0.2f);
        theme.play();
    }

    public static boolean MUTE;

    Options(final Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);
        this.menu = menu;


        gui = new GUI(menu, this, rectangle, count);
        guiButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(gui);
            }
        };

        musicButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                MUTE = !MUTE;
                if (MUTE) {
                    theme.stop();
                } else {
                    theme.play();
                }
            }
        };

        register(guiButton);
        register(musicButton);
    }

    @Override
    public void render() {
        super.render();
        guiButton.render("GRAPHICAL INTERFACE");
        musicButton.render(theme.isPlaying() ? "MUTE MUSIC" : "PLAY MUSIC");
    }
}
