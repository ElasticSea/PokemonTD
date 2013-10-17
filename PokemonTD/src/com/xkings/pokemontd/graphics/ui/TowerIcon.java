package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.entity.tower.TowerType;
import com.xkings.pokemontd.manager.TowerManager;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class TowerIcon extends DisplayBlock {

    protected TowerType towerType;
    private final SpriteBatch spriteBatch;
    private final TowerManager towerManager;

    TowerIcon(Rectangle rectangle, TowerType towerType, SpriteBatch spriteBatch, TowerManager towerManager) {
        super(rectangle);
        this.towerType = towerType;
        this.spriteBatch = spriteBatch;
        this.towerManager = towerManager;
    }

    @Override
    public void render() {
        if (towerType != null) {
            spriteBatch.begin();
            spriteBatch.draw(towerType.getTexture(), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (!towerManager.canAfford(towerType)) {
                spriteBatch.draw(towerType.getBlockedTexture(), rectangle.x, rectangle.y, rectangle.width,
                        rectangle.height);
            }
            spriteBatch.end();
        }
    }
}