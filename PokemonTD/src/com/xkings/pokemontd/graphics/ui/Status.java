package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Player;
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
        pixelFont.setScale(height / 200);
        super.render();
        float iconSize = (height)/6;
        xOffset = width - (offset + iconSize*4);
        yOffset = y + offset*11;
        spriteBatch.begin();
        drawAmount(player.getTreasure().getGold(), "coin", iconSize, (int) (height));
        drawAmount2(player.getHealth().getCurrentHealth(), "hearth", iconSize, (int) (height));
        int textOffset = offset * 2;
        drawWaveInfo(textOffset);
        //drawInterest(textOffset);
        spriteBatch.end();
    }

    private void drawAmount(int amount, String icon, float size, int textOffset) {
        spriteBatch.draw(Assets.getTexture(icon), xOffset+size*2.75f, yOffset+size+size/4, size, size);
        xOffset -= offset + size;
        drawText(amount);
        xOffset -= textOffset;
    }
    private void drawAmount2(int amount, String icon, float size, int textOffset) {
        spriteBatch.draw(Assets.getTexture(icon), xOffset+size*9.9f , yOffset + size*2.8f, size, size);
        xOffset -= offset + size;
        drawText2(amount);
        yOffset -= textOffset;
    }

    private void drawText(int amount) {
        String text = String.valueOf(amount < 10000 ? amount : amount/1000 + "k");
        float fontY = ((height + pixelFont.getBounds(text).height)) / 2f;
        pixelFont.drawMultiLine(spriteBatch, text, xOffset-fontY/2.3f, y + fontY*1.15f, height/1.3f,
                BitmapFont.HAlignment.RIGHT);
    }

    private void drawText2(int amount) {
        String text = String.valueOf(amount < 10000 ? amount : amount/1000 + "k");
        float fontY = ((height + pixelFont.getBounds(text).height)) / 2;
        pixelFont.drawMultiLine(spriteBatch, text, xOffset+fontY*2.1f, y + fontY*1.6f, height / 2f,
                BitmapFont.HAlignment.RIGHT);
    }

    private void drawWaveInfo(int textOffset) {
        pixelFont.drawMultiLine(spriteBatch, "Wave in", x + textOffset*1.2f,
                y + height*1.15f - pixelFont.getCapHeight()*4.7f, 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, String.valueOf(waveManager.getRemainingTime()),
                x + textOffset*16f, y + pixelFont.getCapHeight()*2,
                0, BitmapFont.HAlignment.RIGHT);
    }
    private void drawInterest(int textOffset) {
        pixelFont.drawMultiLine(spriteBatch, "Interest ", x + textOffset*1.2f,
                y + height - pixelFont.getCapHeight()*4.7f, 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, String.valueOf(interest.getRemainingTime()),
                x + textOffset*16f, y + pixelFont.getCapHeight()*2,
                0, BitmapFont.HAlignment.RIGHT);
    }
}
