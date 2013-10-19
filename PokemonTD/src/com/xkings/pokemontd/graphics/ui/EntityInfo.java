package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.component.RangeComponent;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.NameComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.entity.tower.TowerType;

/**
 * Created by Tomas on 10/8/13.
 */
class EntityInfo extends InteractiveBlock {

    private final SpriteBatch spriteBatch;
    private final Button sell;
    private final DisplayText textA;
    private final DisplayText textB;
    private final Ui ui;
    private final Button buy;
    private final DisplayText textC;
    private Entity entity;
    private float offset;

    EntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.ui = ui;
        this.spriteBatch = spriteBatch;
        offset = rectangle.height / 4;
        textA = new DisplayText(new Rectangle(rectangle.x + offset * 3, rectangle.y + offset, offset * 2, offset),
                shapeRenderer, spriteBatch, "textA");
        textB = new DisplayText(new Rectangle(rectangle.x + offset * 3, rectangle.y + offset * 2, offset * 2, offset),
                shapeRenderer, spriteBatch, "textB");
        textC = new DisplayText(new Rectangle(rectangle.x + offset, rectangle.y, offset * 2, offset), shapeRenderer,
                spriteBatch, "textC");
        sell = new Button(new Rectangle(rectangle.x + offset * 5, rectangle.y + offset, offset * 2, offset),
                shapeRenderer, spriteBatch, "SELL", new Color(Color.RED).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        buy = new Button(new Rectangle(rectangle.x + offset * 5, rectangle.y + offset, offset * 2, offset),
                shapeRenderer, spriteBatch, "BUY", new Color(Color.GREEN).mul(0.6f)) {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().getNewOrUpgrade();
            }
        };
        ui.register(sell);
        ui.register(buy);
    }

    @Override
    public void render() {
        TowerType towerType = ui.getTowerManager().getSelectedTowerType();
        if (towerType != null) {
            renderTower(towerType);
            return;
        }

        entity = ui.getTowerManager().getClicked();
        if (entity != null) {
            renderTower(entity);
            return;
        }

        entity = ui.getCreepManager().getClicked();
        if (entity != null) {
            renderCreep(entity);
            return;
        }

    }

    @Override
    public void process(float x, float y) {
    }

    private void renderCreep(Entity entity) {
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        NameComponent nameComponent = entity.getComponent(NameComponent.class);
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        if (spriteComponent != null && nameComponent != null && healthComponent != null) {
            renderCommon(spriteComponent.getSprite(), nameComponent.getName(),
                    String.valueOf(healthComponent.getHealth().getHealth()), "");
        }
    }

    private void renderTower(Entity entity) {

        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        NameComponent nameComponent = entity.getComponent(NameComponent.class);
        RangeComponent rangeComponent = entity.getComponent(RangeComponent.class);
        if (spriteComponent != null && nameComponent != null && rangeComponent != null) {
            renderCommon(spriteComponent.getSprite(), nameComponent.getName(),
                    String.valueOf(rangeComponent.getRange()), "");
            buy.setEnabled(false);
            sell.setEnabled(true);
            sell.render();
        }
    }

    private void renderTower(TowerType towerType) {
        TextureAtlas.AtlasRegion atlasRegion = towerType.getTexture();
        String name = towerType.getName().toString();
        String range = String.valueOf(towerType.getRange());
        renderCommon(atlasRegion, name, range, "");
        sell.setEnabled(false);
        buy.setEnabled(true);
        buy.render();
    }

    private void renderCommon(TextureAtlas.AtlasRegion atlasRegion, String name, String text0, String text1) {
        App.getAssets().getPixelFont().setScale(rectangle.height / 8 / 32);
        spriteBatch.begin();
        spriteBatch.draw(atlasRegion, rectangle.x + offset, rectangle.y + offset, offset * 2, offset * 2);
        spriteBatch.end();
        textA.render(text0);
        textB.render(text1);
        textC.render(name);
    }

}