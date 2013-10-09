package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
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
        App.getAssets().getPixelFont().setScale(rectangle.height/8/32);
    }

    @Override
    public void render() {
        spriteBatch.begin();
        float offset = rectangle.height / 4;
        if (currentTowerInfo.getAtlasRegion() != null) {
            spriteBatch.draw(currentTowerInfo.getAtlasRegion(), offset, offset, offset * 2, offset * 2);
            drawText(currentTowerInfo.getName(), rectangle.height, rectangle.height - offset);
            drawText("Atk: " + String.valueOf(currentTowerInfo.getAttack()), rectangle.height,
                    rectangle.height - offset * 2);
            drawText("Rng: "+String.valueOf(currentTowerInfo.getRange()), rectangle.height+offset*3,
                    rectangle.height - offset*2);
        }
        spriteBatch.end();
    }

    private void drawText(String text, float x, float y)
    {
        App.getAssets().getPixelFont().draw(spriteBatch, text, x, y);
    }

    @Override
    public void process(float x, float y) {


    }
}