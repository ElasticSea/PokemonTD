package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;

/**
 * Created by Seda on 11/19/13.
 */
class EndGame extends ExitTab {

    private Menu menu;
    private final DisplayText score;
    private final DisplayText congratulations;

    EndGame(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, false, true, count);
        this.menu = menu;
        congratulations = new DisplayText(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER);
        score = new DisplayText(ui, rects.get(1), ui.getFont(), BitmapFont.HAlignment.CENTER);

        this.setCloseTabWhenNotClicked(false);
        this.setRenderLines(false);
    }

    @Override
    public void render() {
        super.render();
        congratulations.render("CONGRATULATIONS");
        score.render(menu.getPlayer().getScore() + "");
    }
}
