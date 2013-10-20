package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.entity.creep.CreepType;
import com.xkings.pokemontd.manager.Interest;
import com.xkings.pokemontd.manager.WaveManager;

/**
 * User: Seda
 * Date: 20.10.13
 * Time: 10:47
 */

public class Status extends GuiBox{
    private final Player player;
    private final SpriteBatch spriteBatch;
    private final BitmapFont pixelFont;
    private float xOffset;
    private float yOffset;
    private final WaveManager waveManager;
    private final Interest interest;

    Status(Player player, Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, WaveManager waveManager, Interest interest) {
        super(rectangle, offset, shapeRenderer);
        this.player = player;
        this.spriteBatch = spriteBatch;
        this.pixelFont = App.getAssets().getPixelFont();
        this.waveManager = waveManager;
        this.interest = interest;
    }

    @Override
    public void render() {
        pixelFont.setScale(rectangle.height / 200);
        super.render();
        float iconSize = (rectangle.height)/6;
        xOffset = rectangle.width - (offset + iconSize*4);
        yOffset = rectangle.y + offset*11;
        spriteBatch.begin();
        drawAmount(player.getTreasure().getGold(), "coin", iconSize, (int) (rectangle.height));
        drawAmount2(player.getHealth().getCurrentHealth(), "hearth", iconSize, (int) (rectangle.height));
        CreepType nextWave = waveManager.getNextWave();
        if (nextWave != null) {
            float textureOffset = rectangle.height / 5f;
            spriteBatch.draw(waveManager.getNextWave().getTexture(), rectangle.x + textureOffset,
                    rectangle.y + textureOffset, rectangle.width - textureOffset * 2,
                    rectangle.height - textureOffset * 2);
        }
        int textOffset = offset * 2;
        drawWaveInfo(textOffset);
        //drawInterest(textOffset);
        spriteBatch.end();
    }

    private void drawAmount(int amount, String icon, float size, int textOffset) {
        spriteBatch.draw(Assets.getTexture(icon), xOffset+size*1.25f, yOffset, size, size);
        xOffset -= offset + size;
        drawText(amount);
        xOffset -= textOffset;
    }
    private void drawAmount2(int amount, String icon, float size, int textOffset) {
        spriteBatch.draw(Assets.getTexture(icon), xOffset+size*8.5f , yOffset + size*2, size, size);
        xOffset -= offset + size;
        drawText2(amount);
        yOffset -= textOffset;
    }

    private void drawText(int amount) {
        String text = String.valueOf(amount < 10000 ? amount : amount/1000 + "k");
        float fontY = ((rectangle.height + pixelFont.getBounds(text).height)) / 2f;
        pixelFont.drawMultiLine(spriteBatch, text, xOffset-fontY/2, rectangle.y + fontY, rectangle.height / 2f,
                BitmapFont.HAlignment.RIGHT);
    }

    private void drawText2(int amount) {
        String text = String.valueOf(amount < 10000 ? amount : amount/1000 + "k");
        float fontY = ((rectangle.height + pixelFont.getBounds(text).height)) / 2f;
        pixelFont.drawMultiLine(spriteBatch, text, xOffset+fontY*1.6f, rectangle.y + fontY*1.6f, rectangle.height / 2f,
                BitmapFont.HAlignment.RIGHT);
    }

    private void drawWaveInfo(int textOffset) {
        pixelFont.drawMultiLine(spriteBatch, "Wave in ", rectangle.x + textOffset*1.2f,
                rectangle.y + rectangle.height - pixelFont.getCapHeight()*4.7f, 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, String.valueOf(waveManager.getRemainingTime()),
                rectangle.x + textOffset*16f, rectangle.y + pixelFont.getCapHeight()*2,
                0, BitmapFont.HAlignment.RIGHT);
    }
    private void drawInterest(int textOffset) {
        pixelFont.drawMultiLine(spriteBatch, "Interest ", rectangle.x + textOffset*1.2f,
                rectangle.y + rectangle.height - pixelFont.getCapHeight()*4.7f, 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, String.valueOf(interest.getRemainingTime()),
                rectangle.x + textOffset*16f, rectangle.y + pixelFont.getCapHeight()*2,
                0, BitmapFont.HAlignment.RIGHT);
    }
}
