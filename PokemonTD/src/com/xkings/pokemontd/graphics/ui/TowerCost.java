package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.Element;
import com.xkings.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class TowerCost extends InteractiveBlock {
    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final float maxWidth;
    private Treasure cost;

    public TowerCost(Rectangle rectangle, float maxWidth, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                     BitmapFont font) {
        super(rectangle);
        this.maxWidth = maxWidth;
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.font = font;
    }

    @Override
    public void process(float x, float y) {

    }

    @Override
    public void render() {
        for (costValueCache cache : caches) {
            cache.render();
        }
    }

    List<costValueCache> caches;

    public void render(Treasure cost) {
        this.cost = cost;
        String text = getText(cost);

        float scale = maxWidth / (font.getBounds(text).width);
        float fontScale = font.getScaleX();
        if (scale <= 1) {
            font.setScale(Math.max(Math.round(fontScale / 1.5f), 1));
        }


        caches = new ArrayList<costValueCache>();
        if (cost.getGold() > 0) {
            caches.add(new costValueCache(Color.WHITE, "Cost: "));
            caches.add(new costValueCache(Color.YELLOW, cost.getGold() + " "));
        }
        for (Element element : Element.values()) {
            int element1 = cost.getElement(element);
            if (element1 > 0) {
                caches.add(new costValueCache(Color.WHITE, element1 + "x "));
                caches.add(new costValueCache(element.getColor(), element.toString() + " "));
            }
        }
        render();

        if (scale <= 1) {
            font.setScale(fontScale);
        }
    }

    private String getText(Treasure cost) {
        String text = "";
        if (cost.getGold() > 0) {
            text += "Cost: ";
            text += cost.getGold() + " ";
        }
        for (Element element : Element.values()) {
            int element1 = cost.getElement(element);
            if (element1 > 0) {
                text += element1 + "x";
                text += element.toString() + " ";
            }
        }
        return text;
    }


    private float getOffset(String text) {
        return font.getBounds(text).width / 2f;
    }

    private float maximalHeight;

    private class costValueCache {
        private final String text;
        private final BitmapFont.TextBounds bounds;
        private final Color color;
        private float xPosition;

        private costValueCache(Color color, String text) {
            this.text = text;
            this.bounds = new BitmapFont.TextBounds(font.getBounds(this.text));
            this.color = color;

            for (costValueCache cashe : caches) {
                this.xPosition += cashe.bounds.width;
            }
            maximalHeight = Math.max(bounds.height, maximalHeight);
        }

        public void render() {
            float fontX = x + xPosition;
            float fontY = y + (height + maximalHeight) / 2f;
            spriteBatch.begin();
            font.setColor(color);
            font.draw(spriteBatch, text, fontX, fontY);
            spriteBatch.end();
        }

    }
}