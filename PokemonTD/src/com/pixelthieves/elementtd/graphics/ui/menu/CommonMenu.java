package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class CommonMenu extends CloseExitTab {

    private final Options options;
    private final Help help;
    private final MenuButton moreGames;
    private LeaderboardTabClose leaderboard;
    private final MenuButton optionsButton;
    private final MenuButton helpButton;
    private MenuButton leaderboardButton;

    CommonMenu(final Menu menu, Rectangle rectangle, Type type, boolean restart, int count) {
        super(menu, null, menu.getRectangle(6), type, restart, 6);
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
        if (menu.getGameSevice().canSingIn()) {
            leaderboard = new LeaderboardTabClose(menu, this, menu.getRectangle((int) width, (int) (height * 1.5f)), 8,
                    Type.CLOSE);
            leaderboardButton = new MenuButton(menu, rects.get(3)) {
                @Override
                public void process(float x, float y) {
                    if (menu.getGameSevice().isSignedIn()) {
                        menu.switchCard(leaderboard);
                    } else {
                        menu.getGameSevice().signIn(null);
                    }
                }
            };
            register(leaderboardButton);
            cards.add(leaderboard);
        }
        moreGames = new MenuButton(menu, rects.get(4)) {
            @Override
            public void process(float x, float y) {
                Gdx.net.openURI("https://play.google.com/store/apps/developer?id=PixelThieves");
                // menu.getApp().getAdService().showAd(AdService.AdType.MoreApps);
            }
        };
        register(optionsButton);
        register(moreGames);
        register(helpButton);
        cards.add(help);
        cards.add(options);
    }

    @Override
    public void render() {
        super.render();
        optionsButton.render("Options");
        moreGames.render("More Games");
        helpButton.render("Help");
        if (leaderboardButton != null) {
            leaderboardButton.render(menu.getGameSevice().isSignedIn() ? "Leaderboard" : "Sign In");
        }
    }
}
