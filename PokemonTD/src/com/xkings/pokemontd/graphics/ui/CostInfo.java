package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Treasure;

import java.util.ArrayList;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class CostInfo extends CommonInfo {
    private final TowerCost treasure;
    protected final ArrayList<Clickable> clickables = new ArrayList<Clickable>();
    private Treasure treasureCache;

    public CostInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                    BitmapFont font) {
        super(ui,rectangle,shapeRenderer,spriteBatch,font);
        float offset = height / 5;
        treasure = new TowerCost(new Rectangle(x + offset, y, width - offset * 2, offset), shapeRenderer,
                spriteBatch, font);
    }

    @Override
    public void render() {
        super.render();
        treasure.render(treasureCache);
    }

    public void render(TextureAtlas.AtlasRegion region, String name, Treasure  treasure) {
        render(region,name);
        this.treasureCache = treasure;
    }

    @Override
    public void setEnabled(boolean enabled) {
        for (Clickable clickable : clickables) {
            clickable.setEnabled(enabled);
        }
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
