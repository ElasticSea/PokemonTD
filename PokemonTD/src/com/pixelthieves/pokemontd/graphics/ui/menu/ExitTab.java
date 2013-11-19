package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class ExitTab extends MenuTab {

    protected MenuButton close;
    protected final MenuButton exit;

    ExitTab(Menu menu, Rectangle rectangle, boolean closeButton) {
        super(menu, null, rectangle);
        Rectangle rect2 = rects.get(rects.size() - 1);
        Rectangle closeRect = new Rectangle(rect2.x, rect2.y, rect2.width / 2, rect2.height);
        Rectangle exitRect = new Rectangle(rect2.x + rect2.width / 2, rect2.y, rect2.width / 2, rect2.height);

        if (closeButton) {
            close = new MenuButton(menu, closeRect) {
                @Override
                public void process(float x, float y) {
                    close();
                }
            };
            register(close);
        } else {
            exitRect = rect2;
        }

        exit = new MenuButton(menu, exitRect) {
            @Override
            public void process(float x, float y) {
                System.exit(0);
            }
        };
        register(exit);
    }

    @Override
    public void render() {
        super.render();
        if (close != null) {
            close.render("CLOSE");
        }
        exit.render("EXIT");
    }
}
