package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.entity.tower.TowerType;
import com.xkings.pokemontd.manager.TowerManager;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class TowerIcon extends InteractiveBlock {

    protected TowerType towerType;
    private final SpriteBatch spriteBatch;
    private TowerManager towerManager;

    TowerIcon(Rectangle rectangle, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render() {
        if (towerType != null && towerManager != null) {
            spriteBatch.begin();
            spriteBatch.draw(towerType.getTexture(), x, y, width, height);
            if (!towerManager.canAfford(towerType)) {
                spriteBatch.draw(towerType.getBlockedTexture(), x, y, width, height);
            }
            spriteBatch.end();
        }
    }

    public void setTowerManager(TowerManager towerManager) {
        this.towerManager = towerManager;
    }
}