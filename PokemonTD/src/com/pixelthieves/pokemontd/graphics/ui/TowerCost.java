package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class TowerCost extends InteractiveBlock {
    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private  boolean showCost;
    private float scale = 1;
    private Treasure cost;
    private float fontScale;
    private Rectangle bounds;

    public TowerCost(Gui gui, Rectangle rectangle) {
        super(gui, rectangle);
        this.shapeRenderer = gui.getShapeRenderer();
        this.spriteBatch = gui.getSpriteBatch();
        this.font = gui.getFont();
        this.showCost = true;
    }

    public void setShowCost(boolean showCost) {
        this.showCost = showCost;
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
        fontScale = font.getScaleX();
        font.setScale(Math.max(Math.round(fontScale / scale), 1));
        if (!cost.equals(this.cost) || !this.equals(bounds)) {
            this.bounds = new Rectangle(this);
            this.cost = cost;

            caches = new ArrayList<costValueCache>();
            if (showCost) {
                if (cost.getGold() > 0) {
                    caches.add(new costValueCache(Color.WHITE, "Cost: "));
                    caches.add(new costValueCache(Color.YELLOW, cost.getGold() + " "));
                }
            }
            for (Element element : Element.values()) {
                int element1 = cost.getElement(element);
                if (element1 > 0) {
                    caches.add(new costValueCache(Color.WHITE, element1 + "x "));
                    caches.add(new costValueCache(element.getColor(), element.toString() + " "));
                }
            }
            render();

        } else {
            render();
        }

        font.setScale(fontScale);

        if (App.DEBUG != null) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }

    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    private float maximalHeight;

    @Override
    public void refresh() {

    }

    private class costValueCache {
        private final String text;
        private final BitmapFont.TextBounds bounds;
        private final Color color;
        private final costValueCache previousCashe;
        private float xPosition;
        private float yPosition;

        private costValueCache(Color color, String text) {
            this.text = text;
            this.bounds = new BitmapFont.TextBounds(font.getBounds(this.text));
            this.color = color;

            previousCashe = getPreviousCache();
            if (previousCashe != null) {
                this.xPosition = previousCashe.xPosition + previousCashe.bounds.width;
                this.yPosition = previousCashe.yPosition;
                int offset = (int) ((xPosition + bounds.width) / TowerCost.this.bounds.width);
                xPosition = Math.max(0, xPosition - offset * TowerCost.this.bounds.width);
                yPosition = Math.min(0, yPosition - offset * previousCashe.bounds.height);
            }
            maximalHeight = Math.max(bounds.height, maximalHeight);
        }

        private costValueCache getPreviousCache() {
            if (caches.size() > 0) {
                return caches.get(caches.size() - 1);
            } else {
                return null;
            }
        }

        public void render() {
            float fontX = x + xPosition;
            float fontY = y + yPosition + (height + maximalHeight) / 2f;
            spriteBatch.begin();
            font.setColor(color);
            font.draw(spriteBatch, text, fontX, fontY);
            spriteBatch.end();

            if (App.DEBUG != null) {
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.rect(fontX, fontY - bounds.height, bounds.width, bounds.height);
                shapeRenderer.end();
            }
        }

    }
}