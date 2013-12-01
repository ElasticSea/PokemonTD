package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.GameService;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
public class Options extends ChildTab {

    private final Button guiButton;
    private final Button musicButton;
    private final GUI guiTab;
    private final AbilityTab abilityTab;
    private static Music theme;
    private MenuButton signInButton;

    private Music getTheme() {
        if (theme == null) {
            theme = Assets.getMusic("theme.ogg");
            theme.setLooping(true);
            theme.setVolume(0.4f);
            theme.play();
        }
        return theme;
    }

    public static boolean MUTE;

    Options(final Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);
        getTheme();
        guiTab = new GUI(menu, this, rectangle, count);
        abilityTab = new AbilityTab(menu, this, rectangle, count);
        guiButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(guiTab);
            }
        };

        musicButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                MUTE = !MUTE;
                if (MUTE) {
                    getTheme().stop();
                } else {
                    getTheme().play();
                }
            }

        };
        if (menu.getGameSevice().canSingIn()) {
            signInButton = new MenuButton(menu, rects.get(2)) {
                @Override
                public void process(float x, float y) {
                    GameService gameSevice = menu.getGameSevice();
                    if (gameSevice.isSignedIn()) {
                        gameSevice.signOut();
                    } else {
                        gameSevice.signIn();
                    }
                }
            };
            register(signInButton);
        }


        register(guiButton);
        register(musicButton);
        cards.add(abilityTab);
    }

    @Override
    public void render() {
        super.render();
        guiButton.render("Graphical Interface");
        musicButton.render(getTheme().isPlaying() ? "Mute Music" : "Play Music");
        if (signInButton != null) {
            signInButton.render(menu.getGameSevice().isSignedIn() ? "Sign Out" : "Sign In");
        }
    }
}
