package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
class SignInGameMenu extends SignInCommonMenu {

    private final Button pause;

    SignInGameMenu(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, true,true, count);
        pause = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                App app = menu.getApp();
                if (!app.isFinishedGame()) {
                    app.freeze(!app.isFreezed());
                }
            }
        };

        register(pause);
    }

    @Override
    public void render() {
        super.render();
        pause.render(menu.getApp().isFreezed() ? "Resume" : "Pause");
    }
}
