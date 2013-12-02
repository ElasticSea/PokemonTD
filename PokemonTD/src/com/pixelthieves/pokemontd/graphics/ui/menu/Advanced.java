package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 12/1/13.
 */
public class Advanced extends TutorialTab {
    Advanced(Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle);
    }

    @Override
    protected String getText() {
        return "In Pokemon tower defense the goal is to prevent the creeps from reaching the end of the path by building towers and surviving all creep waves. Every time a creep gets through the whole way, you loose life.\n\n" +
                "Information about current live count, your gold, what the next wave will be and interestManager can be seen in the upper right box.\n\n" +
                "To build a tower pick one of the towers in the lower right box, place it where you want it on the map, and confirm it by pressing BUY button.\n\n" +
                "You can also upgrade the towers by clicking at already built towers and then choosing an upgrade in the lower right box.\n\n" +
                "To build some towers you need more than just gold - elements {WATER, FIRE, NATURE, LIGHT, DARKNESS, PURE, SOUL}. To get the elements you need, build a shop tower, which can be also found in lower right corner along with other basic tower types. When you choose particular element a creep carrying the element will spawn. To claim element you need to kill this creep.\n\n" +
                "It´s generally good idea to save your money, because every 15 seconds you get interest.";
    }
}
