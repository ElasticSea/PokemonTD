package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/19/13.
 */
class CommonMenu extends CloseExitTab {

    private final Options options;
    private final Help help;
    private LeaderboardTabClose leaderboard;
    private final MenuButton optionsButton;
    private final MenuButton helpButton;
    private MenuButton leaderboardButton;

    CommonMenu(final Menu menu, Rectangle rectangle, Type type, boolean restart, int count) {
        super(menu, null, rectangle, type, restart, count);
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
            leaderboard = new LeaderboardTabClose(menu, this, menu.getRectangle((int) width, (int) (height * 1.5f)), 7,
                    Type.CLOSE);
            leaderboardButton = new MenuButton(menu, rects.get(3)) {
                @Override
                public void process(float x, float y) {
                    menu.switchCard(leaderboard);
                }
            };
            register(leaderboardButton);
            cards.add(leaderboard);
        }
        register(optionsButton);
        register(helpButton);
        cards.add(help);
        cards.add(options);
    }

    @Override
    public void render() {
        super.render();
        optionsButton.render("Options");
        helpButton.render("Help");
        if (leaderboardButton != null) {
            leaderboardButton.render("Leaderboard");
        }
    }
}
