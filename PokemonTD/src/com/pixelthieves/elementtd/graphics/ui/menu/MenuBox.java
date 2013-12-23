package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
class MenuBox extends CommonMenu {

    private final Button startGame;

    MenuBox(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, Type.EXIT, false,count);
        startGame = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(Menu.Type.RESTART);
            }
        };
        register(startGame);
        this.setCloseTabWhenNotClicked(false);
    }

    @Override
    public void render() {
        super.render();
        startGame.render("Play Game");
    }
}
