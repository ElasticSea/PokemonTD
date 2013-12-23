package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.Callable;

/**
 * Created by Tomas on 11/19/13.
 */
public class SignInEndGame extends ScoreTabClose {

    public static final Color GREEN = Color.valueOf("40BA44");
    private Menu menu;
    private final MenuButton publishScoreButton;

    SignInEndGame(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, count);
        this.menu = menu;

        publishScoreButton = new MenuButton(menu, rects.get(rects.size() - 2)) {
            @Override
            public void process(float x, float y) {
                if (menu.getGameSevice().isSignedIn()) {
                    submit();
                } else {
                    menu.getGameSevice().signIn(new Callable() {
                        @Override
                        public Object call() throws Exception {
                            submit();
                            return null;
                        }
                    });
                }
            }

            private void submit() {
                menu.getGameSevice().submitScore(menu.getPlayer().getScore());
                menu.switchCard(Menu.Type.LEADERBOARD);
            }
        };
        register(publishScoreButton);
        rateMe.setEnabled(!menu.getGameSevice().isSignedIn());
    }


    @Override
    public void render() {
        super.render();
        publishScoreButton.render(menu.getGameSevice().isSignedIn() ? "Submit Score" : "Sign In To Submit", Color.WHITE,
                GREEN);
    }
}