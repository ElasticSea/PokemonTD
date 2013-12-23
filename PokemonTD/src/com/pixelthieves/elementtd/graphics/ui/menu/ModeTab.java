package com.pixelthieves.elementtd.graphics.ui.menu;

import com.pixelthieves.elementtd.Mode;

/**
 * Created by Tomas on 11/19/13.
 */
class ModeTab extends ChildTab {

    private final MenuButton[] buttons;
    private final MenuTab menuTab;

    ModeTab(final Menu menu, MenuTab parent) {
        super(menu, parent, menu.getRectangle(Mode.values().length + 1), Mode.values().length + 1);
        this.buttons = new MenuButton[Mode.values().length];
        this.menuTab = new MapTab(menu, this, this);
        int i = 0;
        for (final Mode mode : Mode.values()) {
            buttons[i] = new MenuButton(menu, rects.get(i), mode.toString()) {

                @Override
                public void process(float x, float y) {
                    menu.getApp().setMode(mode);
                    menu.switchCard(menuTab);
                }
            };
            register(buttons[i++]);
        }
    }

    @Override
    public void render() {
        super.render();
        for (MenuButton button : buttons) {
            button.renderText();
        }
    }
}
