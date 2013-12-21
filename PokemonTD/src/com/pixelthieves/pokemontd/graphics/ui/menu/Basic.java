package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 12/1/13.
 */
public class Basic extends TutorialTab {
    Basic(Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle, "QUICK TIPS");
    }

    @Override
    protected String getText() {
        return "PLACING A TOWER\n" +
                "Pick a tower from box in lower right corner, place it on the map near the path. Buy the tower, by clicking on green button in middle info bar.\n\n" +
                "USER INTERFACE SIZE\n" +
                "In order to change User Interface size, click on menu in upper left corner, follow OPTIONS > GRAPHICAL INTERFACE and change gui size by clicking on minus or plus sign.\n\n" +
                "Elements\n" +
                "Every 6 wave you will receive a free element that you can swap for one of the element in the shop tower\n\n"+
                "Element Keepers\n" +
                "When you choose element in the shop, it will trigger a Element Keeper that is spawn like ordinary wave, it is just one creep that is more powerful than a whole wave.\n\n"+
                "Soul Element\n" +
                "When you hit 60th wave, you can choose a soul gem, which is key to build the most powerful towers in game.";
    }
}
