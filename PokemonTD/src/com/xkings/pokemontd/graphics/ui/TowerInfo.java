package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.entity.tower.TowerType;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class TowerInfo extends CommonInfo {
    protected final Ui ui;
    protected final SpriteBatch spriteBatch;
    protected final BitmapFont pixelFont;
    protected final DisplayText damage;
    protected final DisplayText speed;
    protected final DisplayText range;
    protected final Button sell;
    protected final Button buy;
    private final TowerCost cost;
    protected TowerType tower;
    private String damageCache;
    private String speedCache;
    private String rangeCache;
    private boolean sellCache;
    private boolean buyCache;
    private Treasure costCache;

    /**
     * public constuctor makes 3 text rectangles uses class DisplayText (damage,range,speed).
     * Makes two anonymous classes  for buttons buy and sell these anonymous classes use public method process which
     * allows sell or upgrade tower and because class TowerInfo extends class CommonInfo (which implements Clickable)
     * there are clickables.add(buy) and clickables.add(sell) which makes function buy and sell.
     * @param ui
     * @param rectangle
     * @param shapeRenderer
     * @param spriteBatch
     */
    public TowerInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                     BitmapFont font) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font);
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();
        float offset = height / 5;
        float offsetBlocks = height / 2;
        float towerCostOffset = offset / 3f;
        cost = new TowerCost(new Rectangle(x + offset, y, width - offset * 2, offset), shapeRenderer,
                spriteBatch, font);
        damage = new DisplayText(new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), shapeRenderer,
                spriteBatch, font);
        speed = new DisplayText(new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), shapeRenderer,
                spriteBatch, font);
        range = new DisplayText(new Rectangle(x + offset * 5, y + offset, offset, offset), shapeRenderer, spriteBatch,
                font);
        sell = new Button(new Rectangle(x + width - offsetBlocks, y, offsetBlocks, offsetBlocks), shapeRenderer,
                spriteBatch, font, BitmapFont.HAlignment.CENTER, new Color(Color.RED).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(new Rectangle(x + width - offsetBlocks, y + offsetBlocks, offsetBlocks, offsetBlocks),
                shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.CENTER, new Color(Color.GREEN).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().getNewOrUpgrade();
            }
        };
        ui.register(sell);
        ui.register(buy);
        clickables.add(sell);
        clickables.add(buy);
    }

    /**
     *  this method overrides method render in class CommonInfo and setted buttons sell and buy
     */
    @Override
    public void render() {
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        this.sell.setEnabled(sellCache);
        this.buy.setEnabled(buyCache);

        super.render();
        this.damage.render(damageCache);
        this.speed.render(speedCache);
        this.range.render(rangeCache);
        this.cost.render(costCache);
        this.sell.render("sell");
        this.buy.render("buy");
    }

    public void render(TextureAtlas.AtlasRegion region, String damage, String speed, String range, Treasure cost,
                       String name, boolean sell, boolean buy) {
        this.damageCache = damage;
        this.speedCache = speed;
        this.rangeCache = range;
        this.costCache = cost;
        this.sellCache = sell;
        this.buyCache = buy;
        render(region, name);
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
