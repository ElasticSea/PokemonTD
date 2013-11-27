package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.GameService;

/**
 * Created by Tomas on 11/19/13.
 */
class SignInCommonMenu extends CommonMenu {

    private final MenuButton signInButton;

    SignInCommonMenu(final Menu menu, Rectangle rectangle, boolean closeButton, boolean restart, int count) {
        super(menu, rectangle, closeButton, restart, count);
        signInButton = new MenuButton(menu, rects.get(3)) {
            @Override
            public void process(float x, float y) {
                GameService gameSevice = menu.getGameSevice();
                if (gameSevice.isSignedIn()) {
                    gameSevice.signOut();
                } else {
                    gameSevice.signIn();
                }
            }
        };
        register(signInButton);
    }

    @Override
    public void render() {
        super.render();
        signInButton.render(menu.getGameSevice().isSignedIn() ? "SIGN OUT" : "SIGN IN");
    }
}
