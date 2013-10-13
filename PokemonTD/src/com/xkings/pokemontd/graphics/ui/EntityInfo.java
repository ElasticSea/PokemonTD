package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.CurrentTowerInfo;

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
        App.getAssets().getPixelFont().setScale(rectangle.height / 8 / 32);
        spriteBatch.begin();
        float offset = rectangle.height / 4;
        if (currentTowerInfo.getAtlasRegion() != null) {
            spriteBatch.draw(currentTowerInfo.getAtlasRegion(), rectangle.x + offset, rectangle.y + offset, offset * 2,
                    offset * 2);
            drawText(currentTowerInfo.getName(), rectangle.height, rectangle.height - offset);
            drawText("Atk: " + String.valueOf(currentTowerInfo.getAttack()), rectangle.height,
                    rectangle.height - offset * 2);
            drawText("Rng: " + String.valueOf(currentTowerInfo.getRange()), rectangle.height + offset * 3,
                    rectangle.height - offset * 2);
        }
        spriteBatch.end();
    }

    private void drawText(String text, float x, float y) {
        App.getAssets().getPixelFont().draw(spriteBatch, text, rectangle.x + x, rectangle.y + y);
    }

    @Override
    public void process(float x, float y) {


    }
}