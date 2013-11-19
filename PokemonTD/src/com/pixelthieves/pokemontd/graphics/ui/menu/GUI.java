package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;

/**
 * Created by Tomas on 11/19/13.
 */
class GUI extends ChildTab {

    private Menu menu;
    private final MenuButton minus;
    private final MenuButton plus;
    private final DisplayText guiSize;
    private final MenuButton defaultGuiSize;

    GUI(final Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle);
        this.menu = menu;
        Rectangle rect = rects.get(0);
        minus = new MenuButton(menu, new Rectangle(rect.x, rect.y, rect.width / 4, rect.height)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().makeGuiSmaller();
            }
        };
        plus = new MenuButton(menu, new Rectangle(rect.x + rect.width * 3 / 4, rect.y, rect.width / 4, rect.height)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().makeGuiLarger();
            }
        };

        guiSize = new DisplayText(ui, new Rectangle(minus.x + minus.width, rect.y, rect.width / 2, rect.height),
                menu.getFont(), BitmapFont.HAlignment.CENTER);

        defaultGuiSize = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().resetGuiSize();
            }
        };

        register(minus);
        register(plus);
        register(guiSize);
        register(defaultGuiSize);
    }

    @Override
    public void render() {
        super.render();
        minus.render("-");
        plus.render("+");
        guiSize.render("GUI SIZE");
        defaultGuiSize.render("RESET");
    }
}
