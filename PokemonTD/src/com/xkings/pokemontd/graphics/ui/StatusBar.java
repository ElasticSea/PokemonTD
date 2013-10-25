package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Element;
import com.xkings.pokemontd.Player;


/**
 * Created by Tomas on 10/11/13.
 */
public class StatusBar extends GuiBox {
    private final Player player;
    private final SpriteBatch spriteBatch;
    private final BitmapFont pixelFont;
    private float xOffset;
    private float yOffset;

    StatusBar(Player player, Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle, offset, shapeRenderer);
        this.player = player;
        this.spriteBatch = spriteBatch;
        this.pixelFont = App.getAssets().getPixelFont();
    }

    @Override
    public void render() {
        pixelFont.setScale(height / 96);
        // pixelFont.setA
        super.render();
        float iconSize = height - offset * 2;
        xOffset = width - (offset + iconSize);
        yOffset = y + offset;
        spriteBatch.begin();
        int textOffset = offset * 2;
        drawScore(textOffset);
        xOffset =  x + offset;
        for (Element element : Element.values()){
            int elementCount = player.getTreasure().getElement(element);
            if (elementCount > 0) {
                drawElements(element, iconSize);
                xOffset += offset + iconSize;
            }
        }
        spriteBatch.end();
    }

    private void drawElements(Element element, float size) {
        String textureName = "gems/" + element.toString().toLowerCase();
        int elementCount = player.getTreasure().getElement(element);
        if (element == Element.PURE && elementCount > 0) {
            elementCount = 3;
        }
        TextureAtlas.AtlasRegion texture = Assets.getTextureArray(textureName).get(elementCount - 1);
        spriteBatch.draw(texture, x+width/3, yOffset, size, size);
    }

   private void drawScore(int textOffset){
        pixelFont.drawMultiLine(spriteBatch, "Score ", x+width/2+width/4,
        y + height*2.1f - pixelFont.getCapHeight()*4.7f, 0, BitmapFont.HAlignment.LEFT);
        pixelFont.drawMultiLine(spriteBatch, player.getScore().toString(),
                x + width/2+width/3+width/10, y + pixelFont.getCapHeight()*2,
                0, BitmapFont.HAlignment.RIGHT);

    }
}
