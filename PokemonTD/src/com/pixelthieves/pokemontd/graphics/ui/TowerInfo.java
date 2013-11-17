package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.attack.AbilityComponent;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.component.attack.projectile.HitAbility;
import com.pixelthieves.pokemontd.component.attack.projectile.data.EffectData;
import com.pixelthieves.pokemontd.component.attack.projectile.data.NormalData;
import com.pixelthieves.pokemontd.entity.tower.TowerType;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class TowerInfo extends CommonInfo {
    public static final Color SELL_COLOR = new Color(Color.RED).mul(0.6f);
    public static final Color BUY_COLOR = new Color(Color.GREEN).mul(0.6f);
    protected final DisplayText damage;
    protected final DisplayText speed;
    protected final DisplayText range;
    protected final Button sell;
    protected final Button buy;
    private final TowerCost cost;
    private final Icon ability;
    private float damageCache;
    private float speedCache;
    private float rangeCache;
    private boolean sellCache;
    private boolean buyCache;
    private Treasure costCache;
    private Color damageColorCache;
    private Color speedColorCache;
    private Color rangeColorCache;
    private EffectData abilityCache;
    private EffectName effectNameCache;
    private BitmapFont.TextBounds largestBounds;
    private float lastDamageCache;
    private float lastSpeedCache;
    private float lastRangeCache;
    private float splashCache;

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
        float offset = height / 5;
        float offsetBlocks = height / 2;
        float offsetBlocks2 = offsetBlocks / 2f;
        cost = new TowerCost(new Rectangle(x + offset, y, width - offset * 2, offset), width - offsetBlocks,
                shapeRenderer, spriteBatch, font);
        damage = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
        speed = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
        range = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);

        ability = new Icon(ui, damage.x + damage.width + offsetBlocks2, y + offsetBlocks2, height - offsetBlocks,
                height - offsetBlocks) {

            @Override
            public void process(float x, float y) {
                ui.getAbilityInfo().update(abilityCache, effectNameCache, splashCache, speedCache, damageCache);
            }
        };
        int buttonWidth = (int) (offsetBlocks * 1.5f);
        sell = new Button(ui, new Rectangle(x + width - buttonWidth, y, buttonWidth, offsetBlocks), font,
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(ui, new Rectangle(x + width - buttonWidth, y + buttonWidth, offsetBlocks, offsetBlocks), font,
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().buyNewOrUpgrade();
            }
        };
        ui.register(sell);
        ui.register(buy);
        ui.register(ability);
        clickables.add(sell);
        clickables.add(buy);
        clickables.add(ability);
    }

    /**
     * this method overrides method render in class CommonInfo and setted buttons sell and buy
     */
    @Override
    public void render() {
        this.sell.setEnabled(sellCache);
        this.buy.setEnabled(buyCache);
        this.ability.setEnabled(effectNameCache != null);
        super.render();

        String damageText = "DMG: " + (int) (damageCache);
        String speedText = "SPD: " + (int) (speedCache);
        String rangeText = "RNG: " + (int) (rangeCache);

        if (damageCache != lastDamageCache || speedCache != lastSpeedCache || rangeCache != lastRangeCache) {
            largestBounds = getLargestBounds(damageText, speedText, rangeText);
            refresh();
        }
        lastDamageCache = damageCache;
        lastSpeedCache = speedCache;
        lastRangeCache = rangeCache;

        if (damageCache > 0) {
            this.damage.render(damageText, damageColorCache);
        }
        if (speedCache > 0) {
            this.speed.render(speedText, speedColorCache);
        }
        if (rangeCache > 0) {
            this.range.render(rangeText, rangeColorCache);
        }
        this.cost.render(costCache);
        this.sell.render("sell", Color.WHITE, SELL_COLOR);
        this.buy.render("buy", Color.WHITE, BUY_COLOR);
        if (effectNameCache != null) {
            this.ability.render(Assets.getTexture("abilities/" + effectNameCache.name().toLowerCase()),
                    effectNameCache.name());
        }
    }

    private void updateTextField() {
        this.damage.width = largestBounds.width;
        this.speed.width = largestBounds.width;
        this.range.width = largestBounds.width;
    }


    private void updateLargestBounds() {
        String damageText = "DMG: " + (int) (damageCache);
        String speedText = "SPD: " + (int) (speedCache);
        String rangeText = "RNG: " + (int) (rangeCache);
        largestBounds = getLargestBounds(damageText, speedText, rangeText);
    }

    private BitmapFont.TextBounds getLargestBounds(String... texts) {
        BitmapFont.TextBounds largestBounds = new BitmapFont.TextBounds();
        for (String text : texts) {
            BitmapFont.TextBounds bounds = gui.getFont().getBounds(text);

            largestBounds.width = Math.max(bounds.width, largestBounds.width);
            largestBounds.height = Math.max(bounds.height, largestBounds.height);

        }
        return largestBounds;
    }

    public void render(TextureAtlas.AtlasRegion region, Treasure cost, String name, boolean sell, boolean buy) {
        render(region, 0f, 0f, 0f, cost, name, null, null, sell, buy);
    }

    public void render(TextureAtlas.AtlasRegion region, float damage, float speed, float range, Treasure cost,
                       String name, EffectName effectName, TowerType type, boolean sell, boolean buy) {
        render(region, damage, Color.WHITE, speed, Color.WHITE, range, Color.WHITE, cost, name, effectName, type, sell,
                buy);
    }

    public void render(TextureAtlas.AtlasRegion region, float damage, Color damageColor, float speed, Color speedColor,
                       float range, Color rangeColor, Treasure cost, String name, EffectName effectName, TowerType type,
                       boolean sell, boolean buy) {
        this.damageCache = damage;
        this.damageColorCache = damageColor;
        this.speedCache = speed;
        this.speedColorCache = speedColor;
        this.rangeCache = range;
        this.rangeColorCache = rangeColor;
        this.costCache = cost;
        this.effectNameCache = effectName;
        if (type != null) {
            this.abilityCache = getAbility(type);
            this.splashCache = getSpash(type);
        }
        this.sellCache = sell;
        this.buyCache = buy;
        render(region, name);
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void refresh() {
        super.refresh();

        float offset = height / 5;
        float offsetBlocks = height / 2;
        float offsetBlocks2 = offsetBlocks / 2f;
        float textOffset = x + offset * 5;
        if (this instanceof ShopEntityInfo) {
            cost.set(textOffset, y + offset, width - textOffset, offset * 3);
        } else {
            cost.set(x + offset, y, width - offset * 2, offset);
        }
        damage.set(textOffset, y + offset * 3, offset * 2, offset);
        speed.set(textOffset, y + offset * 2, offset * 2, offset);
        range.set(textOffset, y + offset, offset, offset);
        updateLargestBounds();
        updateTextField();
        ability.set(damage.x + damage.width + offsetBlocks2, y + offsetBlocks2, height - offsetBlocks,
                height - offsetBlocks);
        int buttonWidth = (int) (offsetBlocks * 1.5f);
        sell.set(x + width - buttonWidth, y, buttonWidth, offsetBlocks);
        buy.set(x + width - buttonWidth, y + offsetBlocks, buttonWidth, offsetBlocks);
        cost.refresh();
        damage.refresh();
        speed.refresh();
        range.refresh();
        ability.refresh();
        sell.refresh();
        buy.refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    protected EffectData getAbility(TowerType tower) {
        AbilityComponent attack = tower.getAttack();
        return attack instanceof HitAbility ? getEffectData((HitAbility) attack) : (EffectData) attack;
    }

    private EffectData getEffectData(HitAbility attack) {
        for (EffectData effectData : attack.getEffectData()) {
            if (!(effectData instanceof NormalData)) {
                return effectData;
            }
        }
        return null;
    }

    private float getSpash(TowerType tower) {
        AbilityComponent attack = tower.getAttack();
        return attack instanceof HitAbility ? ((HitAbility) attack).getAoe() : 0;
    }

}
