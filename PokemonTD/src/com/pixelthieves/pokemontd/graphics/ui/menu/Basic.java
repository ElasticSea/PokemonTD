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
                "In order to change User Interface size, click on menu in upper left corner, follow OPTIONS > GRAPHICAL INTERFACE and change gui size by clicking on minus or plus sign";
    }
}
