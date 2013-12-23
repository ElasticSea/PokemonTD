package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.Element;
import com.pixelthieves.elementtd.Player;

/**
 * Created by Tomas on 10/27/13.
 */
public class ShopIcons extends PickTable<ElementIcon> {

    private final Player player;

    ShopIcons(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        this.player = ui.getPlayer();
        for (int i = 0; i < pickIcons.size(); i++) {
            ElementIcon elementIcon = pickIcons.get(i);
            elementIcon.setPlayer(player);
            elementIcon.setElement(i < Element.values().length ? Element.values()[i] : null);
        }
    }

    @Override
    protected ElementIcon createPickIcon() {
        return new ElementIcon(ui, new Rectangle(), spriteBatch);
    }
}
