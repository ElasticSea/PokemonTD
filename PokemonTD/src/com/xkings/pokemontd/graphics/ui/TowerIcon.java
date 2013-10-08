package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.entity.TowerType;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class TowerIcon extends DisplayBlock {

    protected final TowerType towerType;
    private final SpriteBatch spriteBatch;

    TowerIcon(Rectangle rectangle, TowerType towerType, SpriteBatch spriteBatch) {
        super(rectangle);
        this.towerType = towerType;
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(towerType.getTexture(), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        spriteBatch.end();
    }
}