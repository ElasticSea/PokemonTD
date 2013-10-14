package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.entity.creep.CreepType;
import com.xkings.pokemontd.manager.WaveManager;

/**
 * Created by Tomas on 10/11/13.
 */
public class WaveInfo extends GuiBox {
    private final SpriteBatch spriteBatch;
    private final BitmapFont pixelFont;
    private final WaveManager waveManager;

    WaveInfo(Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
             WaveManager waveManager) {
        super(rectangle, offset, shapeRenderer);
        this.spriteBatch = spriteBatch;
        this.waveManager = waveManager;
        this.pixelFont = App.getAssets().getPixelFont();
    }

    @Override
    public void render() {
        pixelFont.setScale(rectangle.height / 32 / 10);
        super.render();
        spriteBatch.begin();
        CreepType nextWave = waveManager.getNextWave();
        if(nextWave != null){
            float eigth = rectangle.height/8f;
        spriteBatch.draw(waveManager.getNextWave().getTexture(), rectangle.x + eigth, rectangle.y + eigth, eigth * 6,
                eigth * 6);
        }
        int textOffset = offset * 2;
        pixelFont.drawMultiLine(spriteBatch, "Next wave", rectangle.x + textOffset,
                rectangle.y + rectangle.height - pixelFont.getCapHeight(), 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, String.valueOf(waveManager.getRemainingTime()),
                rectangle.x + rectangle.width - textOffset, rectangle.y + rectangle.height - pixelFont.getCapHeight(),
                0, BitmapFont.HAlignment.RIGHT);
        spriteBatch.end();
    }

}
