package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.graphics.ui.DisplayText;

/**
 * Created by Tomas on 11/19/13.
 */
class ScoreTabClose extends CloseExitTab {

    protected final MenuButton rateMe;
    private Menu menu;
    private final DisplayText score;
    private final DisplayText congratulations;

    ScoreTabClose(final Menu menu, Rectangle rectangle, int count) {
        super(menu, null,rectangle, Type.EXIT, true, count);
        this.menu = menu;
        congratulations = new DisplayText(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER);
        score = new DisplayText(ui, rects.get(1), ui.getFont(), BitmapFont.HAlignment.CENTER);

        this.setCloseTabWhenNotClicked(false);
        this.setRenderLines(false);

        rateMe = new MenuButton(menu, rects.get(2)){

            @Override
            public void process(float x, float y) {
                Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.pixelthieves.pokemontd");
            }
        };

        register(rateMe);
    }

    @Override
    public void render() {
        super.render();
        congratulations.render("Congratulations");
        score.render(menu.getPlayer().getScore() + "");
        if(rateMe.isEnabled()){
            rateMe.render("RATE THIS GAME");
        }
    }
}
