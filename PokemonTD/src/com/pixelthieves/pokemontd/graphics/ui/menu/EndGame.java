package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;

/**
 * Created by Tomas on 11/19/13.
 */
class EndGame extends ExitTab {

    private Menu menu;
    private final DisplayText score;
    private final DisplayText congratulations;
    private final MenuButton publishScoreButton;

    EndGame(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, false, count);
        this.menu = menu;
        congratulations = new DisplayText(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER);
        score = new DisplayText(ui, rects.get(1), ui.getFont(), BitmapFont.HAlignment.CENTER);

        publishScoreButton = new MenuButton(menu, rects.get(rects.size() - 2)) {
            @Override
            public void process(float x, float y) {
                if (!menu.getGameSevice().isSignedIn()) {
                    menu.getGameSevice().signIn();
                }
                if (menu.getGameSevice().isSignedIn()) {
                    menu.getGameSevice().submitScore(menu.getPlayer().getScore().getScore());
                    menu.switchCard(Menu.Type.LEADERBOARD);
                }
            }
        };
        register(publishScoreButton);
        this.setCloseTabWhenNotClicked(false);
        this.setRenderLines(false);
    }

    @Override
    public void render() {
        super.render();
        congratulations.render("CONGRATULATIONS");
        score.render(menu.getPlayer().getScore() + "");
        publishScoreButton.render(menu.getGameSevice().isSignedIn() ? "SUBMIT SCORE" : "SIGN IN TO SUBMIT");
    }
}
