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
        pixelFont.setScale(rectangle.height / 96);
        // pixelFont.setA
        super.render();
        float iconSize = rectangle.height - offset * 2;
        xOffset = rectangle.width - (offset + iconSize);
        yOffset = rectangle.y + offset;
        spriteBatch.begin();
        drawAmount(player.getTreasure().getGold(), "coin", iconSize, (int) (rectangle.height * 2));
        drawAmount(player.getHealth().getLives(), "hearth", iconSize, (int) (rectangle.height * 2));

        xOffset =  rectangle.x + offset;
        for (Element element : Element.values()){
            int elementCount = player.getTreasure().getElement(element);
            if (elementCount > 0) {
                drawElements(element, iconSize);
                xOffset += offset + iconSize;
            }
        }
        spriteBatch.end();
    }

    private void drawAmount(int amount, String icon, float size, int textOffset) {
        spriteBatch.draw(Assets.getTexture(icon), xOffset, yOffset, size, size);
        xOffset -= offset + size;
        drawText(amount);
        xOffset -= textOffset;
    }

    private void drawText(int amount) {
        String text = String.valueOf(amount < 10000 ? amount : amount/1000 + "k");
        float fontY = (rectangle.height + pixelFont.getBounds(text).height) / 2f;
        pixelFont.drawMultiLine(spriteBatch, text, xOffset, rectangle.y + fontY, rectangle.height / 2f,
                BitmapFont.HAlignment.RIGHT);
    }

    private void drawElements(Element element, float size) {
        String textureName = "gems/" + element.toString().toLowerCase();
        int elementCount = player.getTreasure().getElement(element);
        if (element == Element.PURE && elementCount > 0) {
            elementCount = 3;
        }
        TextureAtlas.AtlasRegion texture = Assets.getTextureArray(textureName).get(elementCount - 1);
        spriteBatch.draw(texture, xOffset, yOffset, size, size);
    }
}
