package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.Element;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.entity.tower.TowerType;

import java.util.List;

/**
 * Created by Tomas on 10/27/13.
 */
public class ShopIcons extends PickTable<ElementIcon> {

    private final Player player;
    private final Treasure currentElements;

    ShopIcons(Gui ui,Rectangle rectangle) {
        super(ui, rectangle);
        this.player = ui.getPlayer();
        this.currentElements = new Treasure(0);
        for (int i = 0; i < pickIcons.size(); i++) {
            ElementIcon elementIcon = pickIcons.get(i);
            elementIcon.setPlayer(player);
            elementIcon.setElement(i < Element.values().length ? Element.values()[i] : null);
            elementIcon.setCurrentElements(currentElements);
        }
    }

    @Override
    protected ElementIcon createPickIcon() {
        return new ElementIcon(ui,new Rectangle(), spriteBatch);
    }
}
