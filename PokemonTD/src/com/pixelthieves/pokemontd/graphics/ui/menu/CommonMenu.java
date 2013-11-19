package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.GameService;

/**
 * Created by Tomas on 11/19/13.
 */
class CommonMenu extends ExitTab {

    private final Options options;
    private final Tutorial tutorial;
    private final MenuButton optionsButton;
    private final MenuButton tutorialButton;
    private final MenuButton signInButton;

    CommonMenu(final Menu menu, Rectangle rectangle, boolean closeButton) {
        super(menu, rectangle, closeButton);
        tutorial = new Tutorial(menu, this, new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        options = new Options(menu, this, rectangle);
        tutorialButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(tutorial);
            }
        };
        optionsButton = new MenuButton(menu, rects.get(2)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(options);
            }
        };

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
        register(optionsButton);
        register(tutorialButton);
        register(signInButton);
        cards.add(options);
        cards.add(tutorial);
    }

    @Override
    public void render() {
        super.render();
        optionsButton.render("OPTIONS");
        tutorialButton.render("TUTORIAL");
        signInButton.render(menu.getGameSevice().isSignedIn() ? "SIGN OUT" : "SIGN IN");
    }
}
