package com.pixelthieves.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.NameComponent;
import com.pixelthieves.pokemontd.component.SpriteComponent;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.entity.tower.TowerType;

import java.util.ArrayList;

/**
 * Created by Tomas on 10/8/13.
 */
class EntityInfo extends GuiBox {

    private final Ui ui;
    private final TowerTypeInfo towerTypeInfo;
    private final TowerEntityInfo towerEntityInfo;
    private final ArrayList<InteractiveElement> clickables;
    private final ShopEntityInfo shopEntityInfo;
    private final ShopTypeInfo shopTypeInfo;
    private CreepEntityInfo creepEntityInfo;
    private Entity entity;
    private Object lastObject;

    EntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font,
               Player player) {
        super(ui, rectangle);
        this.ui = ui;
        towerTypeInfo = new TowerTypeInfo(ui, this, shapeRenderer, spriteBatch, font);
        towerEntityInfo = new TowerEntityInfo(ui, this, shapeRenderer, spriteBatch, font);
        creepEntityInfo = new CreepEntityInfo(ui, this, shapeRenderer, spriteBatch, font);
        shopTypeInfo = new ShopTypeInfo(ui, this, shapeRenderer, spriteBatch, font);
        shopEntityInfo = new ShopEntityInfo(ui, this, shapeRenderer, spriteBatch, font, player);

        clickables = new ArrayList<InteractiveElement>();
        clickables.add(towerTypeInfo);
        clickables.add(towerEntityInfo);
        clickables.add(creepEntityInfo);
        clickables.add(shopTypeInfo);
        clickables.add(shopEntityInfo);
    }

    @Override
    public void render() {
        super.render();
        disableClickables();
        TowerType towerType = ui.getTowerManager().getSelectedTowerType();

        entity = ui.getCreepManager().getClicked();
        if (entity != null) {
            ui.getTowerManager().reset();
            check(entity);
            lastObject = entity;
            creepEntityInfo.render(entity);
            return;
        }


        if (towerType != null) {
            check(towerType);
            lastObject = towerType;

            if (towerType.getName().equals(TowerName.Shop)) {
                shopTypeInfo.render(towerType);
            } else {
                towerTypeInfo.render(towerType);
            }
            return;
        }

        entity = ui.getTowerManager().getClicked();
        if (entity != null) {
            check(entity);
            lastObject = entity;

            if (entity.getComponent(TowerTypeComponent.class).getTowerType().getName().equals(TowerName.Shop)) {
                shopEntityInfo.render(entity);
            } else {
                towerEntityInfo.render(entity);
            }
            return;
        }

        check(null);

    }

    private void check(Object o) {
        if (lastObject != o) {
            ui.getAbilityInfo().reset();
        }
    }

    private void disableClickables() {
        for (int i = 0; i < clickables.size(); i++) {
            clickables.get(i).setEnabled(false);
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
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        for (InteractiveElement clickable : clickables) {
            clickable.set(this);
            clickable.refresh();
        }
    }
}