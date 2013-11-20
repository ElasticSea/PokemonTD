package com.pixelthieves.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.component.NameComponent;
import com.pixelthieves.pokemontd.component.SpriteComponent;

/**
 * User: Seda
 * Date: 26.10.13
 * Time: 23:40
 */

public class ShopEntityInfo extends TowerTypeInfo {
    private final Player player;

    ShopEntityInfo(Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font,
                   Player player) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font);
        this.player = player;
    }

    public void render(Entity entity) {
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        NameComponent nameComponent = entity.getComponent(NameComponent.class);
        if (spriteComponent != null && nameComponent != null) {
            render(spriteComponent.getSprite(), 0f, 0f, 0f, player.getTreasure(), nameComponent.getName(), null, null,
                    true, false);
        }
    }

}
