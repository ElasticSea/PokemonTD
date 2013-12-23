package com.pixelthieves.elementtd.graphics.ui.menu;

import com.pixelthieves.elementtd.Difficulty;

/**
 * Created by Tomas on 11/19/13.
 */
class DifficultyTab extends ChildTab {

    private final MenuButton[] buttons;
    private final ModeTab modeTab;

    DifficultyTab(final Menu menu, MenuTab parent) {
        super(menu, parent, menu.getRectangle(Difficulty.values().length + 1), Difficulty.values().length + 1);
        this.buttons = new MenuButton[Difficulty.values().length];
        this.modeTab = new ModeTab(menu, this);
        int i = 0;
        for (final Difficulty difficulty : Difficulty.values()) {
            buttons[i] = new MenuButton(menu, rects.get(i), difficulty.toString()) {

                @Override
                public void process(float x, float y) {
                    menu.getApp().setDifficulty(difficulty);
                    menu.switchCard(modeTab);
                }
            };
            register(buttons[i++]);
        }
        this.setCloseTabWhenNotClicked(false);
    }

    @Override
    public void render() {
        super.render();
        for (MenuButton button : buttons) {
            button.renderText();
        }
    }
}
