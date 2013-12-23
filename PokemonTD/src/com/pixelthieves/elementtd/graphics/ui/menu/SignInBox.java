package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class SignInBox extends MenuTab {

    private final MenuButton signInButton;
    private final MenuButton skipButton;

    SignInBox(final Menu menu, Rectangle rectangle, int count) {
        super(menu, null, rectangle, count);
        signInButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.getGameSevice().signIn(null);
            }
        };
        skipButton = new MenuButton(menu, rects.get(rects.size() - 1)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(Menu.Type.MAIN);
                menu.getGameSevice().skipSignIn();
            }
        };
        register(signInButton);
        register(skipButton);
        this.setCloseTabWhenNotClicked(false);
    }

    @Override
    public void render() {
        if (menu.getGameSevice().isSignedIn()) {
            menu.switchCard(Menu.Type.MAIN);
            return;
        }

        super.render();
        signInButton.render("Sign In");
        skipButton.render("Skip");
    }
}
