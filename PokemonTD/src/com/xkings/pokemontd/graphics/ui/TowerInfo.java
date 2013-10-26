package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.entity.tower.TowerType;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class TowerInfo extends InteractiveBlock {
    protected final Ui ui;
    protected final SpriteBatch spriteBatch;
    protected final BitmapFont pixelFont;
    protected final DisplayText damage;
    protected final DisplayText speed;
    protected final DisplayText range;
    protected final Button sell;
    protected final Button buy;
    protected final DisplayText name;
    private DisplayPicture picture;
    private ShapeRenderer shapeRenderer;
    protected TowerType tower;
    private TextureAtlas.AtlasRegion regionCache;
    private int damageCache;
    private int speedCache;
    private int rangeCache;
    private String nameCache;
    private boolean sellCache;
    private boolean buyCache;

    public TowerInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();
        float offset = height / 5;
        float offsetBlocks = height/2;
        //Rectangle offsetBlock = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
        //Rectangle blockInBlock = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);

        picture = new DisplayPicture(new Rectangle(x + offset, y + offset, height - offset * 2, height - offset * 2),
                spriteBatch, shapeRenderer, Color.DARK_GRAY);

        damage = new DisplayText(new Rectangle(x + offset * 5, y + offset * 3.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        speed = new DisplayText(new Rectangle(x + offset * 5, y + offset * 2.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        range = new DisplayText(new Rectangle(x + offset * 5, y + offset * 1.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        name = new DisplayText(new Rectangle(x + offset, y + offset / 7, height - offset * 2, offset), shapeRenderer,
                spriteBatch, BitmapFont.HAlignment.CENTER);
        sell = new Button(new Rectangle(x+ width-offsetBlocks, y, offsetBlocks, offsetBlocks), shapeRenderer, spriteBatch,
                BitmapFont.HAlignment.CENTER, new Color(Color.RED).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(new Rectangle(x + width-offsetBlocks, y + offsetBlocks, offsetBlocks, offsetBlocks), shapeRenderer, spriteBatch,
                BitmapFont.HAlignment.CENTER, new Color(Color.GREEN).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().getNewOrUpgrade();
            }
        };
        ui.register(sell);
        ui.register(buy);
        sell.setEnabled(false);
        buy.setEnabled(false);
    }

    @Override
    public void render() {
        float offset = this.height / 5;
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        picture.render(regionCache);
        this.damage.render("Atk: " + damageCache);
        this.speed.render("Spd: " + speedCache);
        this.range.render("Rng: " + rangeCache);
        this.name.render(nameCache);
        if (sellCache) {
            this.sell.render("sell");
        } else if (buyCache) {
            this.buy.render("buy");
        }
        this.sell.setEnabled(sellCache);
        this.buy.setEnabled(buyCache);
    }

    public void render(TextureAtlas.AtlasRegion region, int damage, int speed, int range, String nameCache, boolean sell,
                       boolean buy) {
        this.regionCache = region;
        this.damageCache = damage;
        this.speedCache = speed;
        this.rangeCache = range;
        this.nameCache = nameCache;
        this.sellCache = sell;
        this.buyCache = buy;
        render();
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
