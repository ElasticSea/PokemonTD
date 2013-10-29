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
    public static final Color SELL_COLOR = new Color(Color.RED).mul(0.6f);
    public static final Color BUY_COLOR = new Color(Color.GREEN).mul(0.6f);
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
    private Color damageColorCache;
    private Color speedColorCache;
    private Color rangeColorChache;

    /**
     * public constuctor makes 3 text rectangles uses class DisplayText (damage,range,speed).
     * Makes two anonymous classes  for buttons buy and sell these anonymous classes use public method process which
     * allows sell or upgrade tower and because class TowerInfo extends class CommonInfo (which implements Clickable)
     * there are clickables.add(buy) and clickables.add(sell) which makes function buy and sell.
     *
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
        cost = new TowerCost(new Rectangle(x + offset, y, width - offset * 2, offset), shapeRenderer, spriteBatch,
                font);
        damage = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), font);
        speed = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), font);
        range = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset, offset, offset), font);
        sell = new Button(ui, new Rectangle(x + width - offsetBlocks, y, offsetBlocks, offsetBlocks), font,
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(ui, new Rectangle(x + width - offsetBlocks, y + offsetBlocks, offsetBlocks, offsetBlocks),
                font, BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().buyNewOrUpgrade();
            }
        };
        ui.register(sell);
        ui.register(buy);
        clickables.add(sell);
        clickables.add(buy);
    }

    /**
     * this method overrides method render in class CommonInfo and setted buttons sell and buy
     */
    @Override
    public void render() {
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        this.sell.setEnabled(sellCache);
        this.buy.setEnabled(buyCache);

        super.render();
        this.damage.render(damageCache, damageColorCache);
        this.speed.render(speedCache, speedColorCache);
        this.range.render(rangeCache, rangeColorChache);
        this.cost.render(costCache);
        this.sell.render("sell", Color.WHITE, SELL_COLOR);
        this.buy.render("buy", Color.WHITE, BUY_COLOR);
    }

    public void render(TextureAtlas.AtlasRegion region, Treasure cost, String name, boolean sell, boolean buy) {
        render(region, "", "", "", cost, name, sell, buy);
    }

    public void render(TextureAtlas.AtlasRegion region, String damage, String speed, String range, Treasure cost,
                       String name, boolean sell, boolean buy) {
        render(region, damage, Color.WHITE, speed, Color.WHITE, range, Color.WHITE, cost, name, sell, buy);
    }

    public void render(TextureAtlas.AtlasRegion region, String damage, Color damageColor, String speed,
                       Color speedColor, String range, Color rangeColor, Treasure cost, String name, boolean sell,
                       boolean buy) {
        this.damageCache = damage;
        this.damageColorCache = damageColor;
        this.speedCache = speed;
        this.speedColorCache = speedColor;
        this.rangeCache = range;
        this.rangeColorChache = rangeColor;
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
