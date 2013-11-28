package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Seda on 11/19/13.
 */
class ExitTab extends MenuTab {

    private final EndTab end;
    private final boolean restart;
    protected MenuButton close;
    protected final MenuButton endButton;

    ExitTab(final Menu menu, Rectangle rectangle, boolean closeButton, boolean restart, int count) {
        super(menu, null, rectangle, count);
        this.restart = restart;
        this.end = new EndTab(menu, this, rectangle);
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

        endButton = new MenuButton(menu, exitRect) {
            @Override
            public void process(float x, float y) {
                if (ExitTab.this.restart) {
                    menu.switchCard(end);
                } else {
                    System.exit(0);
                }
            }
        };
        register(endButton);
    }

    @Override
    public void render() {
        super.render();
        if (close != null) {
            close.render("CLOSE");
        }
        endButton.render(restart ? "END" : "EXIT");
    }
}
