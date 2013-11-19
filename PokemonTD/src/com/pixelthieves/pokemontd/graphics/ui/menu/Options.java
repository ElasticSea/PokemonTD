package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
class Options extends ChildTab {

    private Menu menu;
    private final Button guiButton;
    private final Button musicButton;
    private final GUI gui;
    private final Music theme;

    Options(final Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle);
        this.menu = menu;

        theme = Assets.getMusic("theme.ogg");
        theme.setLooping(true);
        theme.setVolume(0.2f);
        // theme.play();

        gui = new GUI(menu, this, rectangle);
        guiButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(gui);
            }
        };

        musicButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                if (theme.isPlaying()) {
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
