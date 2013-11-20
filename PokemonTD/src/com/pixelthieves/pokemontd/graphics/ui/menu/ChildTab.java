package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class ChildTab extends MenuTab {

    private final MenuButton close;

    ChildTab(Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);
        Rectangle rect2 = rects.get(rects.size() - 1);
        close = new MenuButton(menu, rect2) {
            @Override
            public void process(float x, float y) {
                close();
            }
        };
        register(close);
    }

    @Override
    public void render() {
        super.render();
        close.render("CLOSE");
    }
}
