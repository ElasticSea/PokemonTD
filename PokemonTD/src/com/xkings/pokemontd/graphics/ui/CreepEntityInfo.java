package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.component.CreepAbilityComponent;
import com.xkings.pokemontd.component.CreepTypeComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.SpriteComponent;

/**
 * User: Seda
 * Date: 26.10.13
 * Time: 22:03
 */

public class CreepEntityInfo extends CommonInfo {

    private final DisplayText health;
    private final DisplayText type;
    private int healthCache;
    private String typeCache;

    public CreepEntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                           BitmapFont font) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font);

        float offset = height / 5;

        health = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
        type = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
    }

    @Override
    public void render() {
        super.render();
        this.health.render("HP: " + healthCache);
        this.type.render("" + typeCache);
    }

    public void render(Entity entity) {
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        CreepTypeComponent nameComponent = entity.getComponent(CreepTypeComponent.class);
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        CreepAbilityComponent creepTypeComponent = entity.getComponent(CreepAbilityComponent.class);
        if (spriteComponent != null && nameComponent != null && healthComponent != null && creepTypeComponent != null) {
            this.healthCache = healthComponent.getHealth().getCurrentHealth();
            this.typeCache = creepTypeComponent.getCreepAbilityType().toString();
            render(spriteComponent.getSprite(), nameComponent.getCreepType().getName().toString());
        }
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void refresh() {
        System.out.println("refresg");
        super.refresh();
        float offset = height / 5;
        health.set(x + offset * 5, y + offset * 3, offset * 2, offset);
        type.set(x + offset * 5, y + offset * 2, offset * 2, offset);
        health.refresh();
        type.refresh();
    }
}
