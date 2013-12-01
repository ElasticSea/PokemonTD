package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class CloseExitTab extends MenuTab {

    public enum Type {
        CLOSE, EXIT, CLOSE_AND_EXIT;
    }

    private final EndTab end;
    private final boolean restart;
    protected MenuButton closeButton;
    protected MenuButton endButton;

    CloseExitTab(final Menu menu, MenuTab parent, Rectangle rectangle, Type type, boolean restart, int count) {
        super(menu, parent, rectangle, count);
        this.restart = restart;
        this.end = new EndTab(menu, this, rectangle);
        Rectangle center = rects.get(rects.size() - 1);
        Rectangle left = new Rectangle(center.x, center.y, center.width / 2, center.height);
        Rectangle right = new Rectangle(center.x + center.width / 2, center.y, center.width / 2, center.height);

        Rectangle closeRect = null;
        Rectangle exitRect = null;
        switch (type) {
            case CLOSE:
                closeRect = center;
                break;
            case EXIT:
                exitRect = center;
                break;
            case CLOSE_AND_EXIT:
                closeRect = left;
                exitRect = right;
                break;
        }
        if (closeRect != null) {
            closeButton = new MenuButton(menu, closeRect) {
                @Override
                public void process(float x, float y) {
                    close();
                }
            };
            register(closeButton);
        }

        if (exitRect != null) {
            endButton = new MenuButton(menu, exitRect) {
                @Override
                public void process(float x, float y) {
                    if (CloseExitTab.this.restart) {
                        menu.switchCard(end);
                    } else {
                        System.exit(0);
                    }
                }
            };
            register(endButton);
        }
    }

    @Override
    public void render() {
        super.render();
        if (closeButton != null) {
            closeButton.render("Close");
        }
        if (endButton != null) {
            endButton.render(restart ? "End" : "Exit");
        }
    }
}
