package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.entity.creep.CreepType;
import com.xkings.pokemontd.manager.WaveManager;

/**
 * Created by Tomas on 10/11/13.
 */
public class WaveInfo extends GuiBox {
    private final SpriteBatch spriteBatch;
    private final BitmapFont pixelFont;
    private final WaveManager waveManager;
    private final DisplayText waveText;
    private final DisplayText waveNumberText;
    private final DisplayText abilityText;
    private final DisplayPicture creepTexture;

    WaveInfo(Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
             WaveManager waveManager, BitmapFont font) {
        super(rectangle, offset, shapeRenderer);
        this.spriteBatch = spriteBatch;
        this.waveManager = waveManager;
        this.pixelFont = font;
        float textHeight = height / 7f;
        Rectangle scaled =
                new Rectangle(x + offset*2, y + offset, width - offset * 4, height - offset * 2);
        float quarterSize = scaled.height / 4f;
        Rectangle waveRectangle =
                new Rectangle(scaled.x, scaled.y + scaled.height - textHeight, scaled.width, textHeight);
        Rectangle abilityRectangle = new Rectangle(scaled.x, scaled.y, scaled.width, textHeight);
        this.waveText = new DisplayText(waveRectangle, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.LEFT);
        this.waveNumberText =
                new DisplayText(waveRectangle, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.RIGHT);
        this.abilityText =
                new DisplayText(abilityRectangle, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.CENTER);
        this.creepTexture =
                new DisplayPicture(scaled.x + quarterSize, scaled.y + quarterSize, quarterSize * 2, quarterSize * 2,
                        shapeRenderer, spriteBatch);
    }

    @Override
    public void render() {
        super.render();
        CreepType nextWave = waveManager.getNextWave();
        if (nextWave != null) {
            waveText.render("Wave");
            waveNumberText.render(String.valueOf(nextWave.getId()));
            abilityText.render(nextWave.getAbilityType().toString());
            creepTexture.render(nextWave.getTexture());
        }
    }

}
