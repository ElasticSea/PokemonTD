package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.entity.CurrentTowerInfo;

/**
 * Created by Tomas on 10/8/13.
 */
class EntityInfo extends DisplayBlock {

    private final SpriteBatch spriteBatch;
    private final CurrentTowerInfo currentTowerInfo;

    EntityInfo(Rectangle rectangle, SpriteBatch spriteBatch, CurrentTowerInfo currentTowerInfo) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.currentTowerInfo = currentTowerInfo;
    }

    @Override
    public void render() {
        spriteBatch.begin();
        if (currentTowerInfo.getAtlasRegion() != null) {
            spriteBatch.draw(currentTowerInfo.getAtlasRegion(), 50, 50);
        }
        spriteBatch.end();
    }

    @Override
    public void process(float x, float y) {

    }
}