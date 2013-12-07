package com.pixelthieves.pokemontd.graphics.ui.menu;

/**
 * Created by Tomas on 11/19/13.
 */
class EndTab extends ChildTab {

    protected final MenuButton restart;
    protected final MenuButton exit;

    EndTab(final Menu menu, MenuTab parent) {
        super(menu, parent, menu.getRectangle(3), 3);
        restart = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().restart();
                menu.switchCard(Menu.Type.MAIN);
            }
        };
        exit = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().leaveGame();
            }
        };
        register(restart);
        register(exit);
    }

    @Override
    public void render() {
        super.render();
        restart.render("Restart");
        exit.render("Exit");
    }
}
