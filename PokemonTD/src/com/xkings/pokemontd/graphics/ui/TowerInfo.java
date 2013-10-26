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

    public TowerInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();
        float offset = height / 5;
        //Rectangle offsetBlock = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
        //Rectangle blockInBlock = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);

        picture = new DisplayPicture(x + offset, y + offset, height - offset * 2, height - offset * 2, shapeRenderer,
                spriteBatch, Color.DARK_GRAY);

        damage = new DisplayText(new Rectangle(x + offset * 5, y + offset * 3.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        speed = new DisplayText(new Rectangle(x + offset * 5, y + offset * 2.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        range = new DisplayText(new Rectangle(x + offset * 5, y + offset * 1.5f, offset * 2, offset), shapeRenderer,
                spriteBatch);
        name = new DisplayText(new Rectangle(x + offset, y + offset / 7, height - offset * 2, offset), shapeRenderer,
                spriteBatch, BitmapFont.HAlignment.CENTER);
        sell = new Button(new Rectangle(x + offset * 7, y + offset / 4, offset * 2, offset), shapeRenderer, spriteBatch,
                BitmapFont.HAlignment.CENTER, new Color(Color.RED).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(new Rectangle(x + offset * 5, y + offset / 4, offset * 2, offset), shapeRenderer, spriteBatch,
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
        TextureAtlas.AtlasRegion atlasRegion = tower.getTexture();
        String name = tower.getName().toString();
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        picture.render(atlasRegion);
        damage.render("Atk: " + String.valueOf((int) tower.getDamage()));
        speed.render("Spd: " + String.valueOf((int) tower.getSpeed()));
        this.range.render("Rng: " + String.valueOf((int) tower.getRange()));
        this.name.render(name);

        sell.setEnabled(false);
        buy.setEnabled(true);
        buy.render();
    }

    public void render(TextureAtlas.AtlasRegion region, int damage, int speed, int range, String name, boolean sell,
                       boolean buy) {
        float offset = this.height / 5;
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        picture.render(region);
        this.damage.render("Atk: " + damage);
        this.speed.render("Spd: " + speed);
        this.range.render("Rng: " + range);
        this.name.render(name);
        if (sell) {
            this.sell.render();
        } else if (buy) {
            this.buy.render();
        }
        this.sell.setEnabled(sell);
        this.buy.setEnabled(buy);
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
