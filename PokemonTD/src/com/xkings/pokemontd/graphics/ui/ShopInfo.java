package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.entity.tower.TowerType;

import java.util.ArrayList;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class ShopInfo extends CommonInfo {
    protected final Ui ui;
    protected final SpriteBatch spriteBatch;
    protected final BitmapFont pixelFont;
    protected final DisplayText damage;
    protected final DisplayText speed;
    protected final DisplayText range;
    private final ArrayList<Clickable> clickables;
    protected TowerType tower;
    private String damageCache;
    private String speedCache;
    private String rangeCache;
    private boolean sellCache;
    private boolean buyCache;

    public ShopInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(ui, rectangle, shapeRenderer, spriteBatch);
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();
        float offset = height / 5;
        clickables = new ArrayList<Clickable>();
        damage = new DisplayText(new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), shapeRenderer,
                spriteBatch);
        speed = new DisplayText(new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), shapeRenderer,
                spriteBatch);
        range = new DisplayText(new Rectangle(x + offset * 5, y + offset, offset * 2, offset), shapeRenderer,
                spriteBatch);
    }

    @Override
    public void render() {
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        this.damage.render(damageCache);
        this.speed.render(speedCache);
        this.range.render(rangeCache);
    }

    public void render(TextureAtlas.AtlasRegion region, String damage, String speed, String range, String name,
                       boolean sell, boolean buy) {
        this.damageCache = damage;
        this.speedCache = speed;
        this.rangeCache = range;
        this.sellCache = sell;
        this.buyCache = buy;
        render(region, name);
        render();
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
