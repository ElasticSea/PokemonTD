package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Difficulty;

/**
 * Created by Tomas on 11/19/13.
 */
class DifficultyTab extends ChildTab {

    private final MenuButton[] buttons;

    DifficultyTab(final Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle, Difficulty.values().length + 1);
        this.buttons = new MenuButton[Difficulty.values().length];
        int i = 0;
        for (final Difficulty difficulty : Difficulty.values()) {
            buttons[i] = new MenuButton(menu, rects.get(i),difficulty.toString()) {

                @Override
                public void process(float x, float y) {
                    menu.switchCard((MenuTab)null);
                    App app = menu.getApp();
                    app.setDifficulty(difficulty);
                    app.setSessionStarted(true);
                    app.freeze(false);
                }
            };
            register(buttons[i++]);
        }
    }

    @Override
    public void render() {
        super.render();
        for (MenuButton button : buttons) {
            button.renderText();
        }
    }
}
