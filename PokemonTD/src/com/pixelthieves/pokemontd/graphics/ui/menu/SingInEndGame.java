package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;

/**
 * Created by Tomas on 11/19/13.
 */
class SingInEndGame extends EndGame {

    private Menu menu;
    private final MenuButton publishScoreButton;

    SingInEndGame(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, count);
        this.menu = menu;

        publishScoreButton = new MenuButton(menu, rects.get(rects.size() - 2)) {
            @Override
            public void process(float x, float y) {
                if (!menu.getGameSevice().isSignedIn()) {
                    menu.getGameSevice().signIn();
                }
                if (menu.getGameSevice().isSignedIn()) {
                    menu.getGameSevice().submitScore(menu.getPlayer().getScore());
                    menu.switchCard(Menu.Type.LEADERBOARD);
                }
            }
        };
        register(publishScoreButton);
    }

    @Override
    public void render() {
        super.render();
        publishScoreButton.render(menu.getGameSevice().isSignedIn() ? "SUBMIT SCORE" : "SIGN IN TO SUBMIT");
    }
}
