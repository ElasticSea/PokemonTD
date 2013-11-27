package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class CommonMenu extends ExitTab {

    private final Options options;
    private final Help help;
    private final MenuButton optionsButton;
    private final MenuButton helpButton;

    CommonMenu(final Menu menu, Rectangle rectangle, boolean closeButton, boolean restart, int count) {
        super(menu, rectangle, closeButton, restart,count);
        help = new Help(menu, this, rectangle, count);
        options = new Options(menu, this, rectangle, count);
        helpButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(help);
            }
        };
        optionsButton = new MenuButton(menu, rects.get(2)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(options);
            }
        };
        register(optionsButton);
        register(helpButton);
        cards.add(help);
        cards.add(options);
    }

    @Override
    public void render() {
        super.render();
        optionsButton.render("OPTIONS");
       helpButton.render("HELP");
    }
}
