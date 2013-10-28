package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.main.Assets;
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
    private Treasure cost;

    public TowerCost(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.font = Assets.createFont("pixelFont");
        this.font.setScale(0.50f);
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
        caches = new ArrayList<costValueCache>();


        if (cost.getGold() > 0) {
            costValueCache coin = new costValueCache(cost.getGold(), Assets.getTexture("coin"));
            caches.add(coin);
        }
        for (Element element : Element.values()) {
            int element1 = cost.getElement(element);
            if (element1 > 0) {
                caches.add(new costValueCache(element1, Assets.getTexture("gems/" + element.toString().toLowerCase())));
            }
        }
        render();
    }


    private float getOffset(String text) {
        return font.getBounds(text).width / 2f;
    }

    private float maximalHeight;

    private class costValueCache implements Renderable {
        private final String text;
        private final TextureAtlas.AtlasRegion region;
        private final BitmapFont.TextBounds bounds;
        private float xPosition;

        private costValueCache(int count, TextureAtlas.AtlasRegion region) {
            this.text = String.valueOf(count);
            this.bounds = new BitmapFont.TextBounds(font.getBounds(text));
            this.region = region;

            for (costValueCache cashe : caches) {
                this.xPosition += cashe.bounds.width + maximalHeight;
            }
            maximalHeight = Math.max(bounds.height, maximalHeight);
        }

        @Override
        public void render() {
            float fontX = x + xPosition;
            float fontY = y + (height + maximalHeight) / 2f;

            spriteBatch.begin();
            font.draw(spriteBatch, text, fontX, fontY);
            spriteBatch.end();

            spriteBatch.begin();
            spriteBatch.draw(region, fontX + bounds.width, y, maximalHeight, maximalHeight);
            spriteBatch.end();
        }

    }
}