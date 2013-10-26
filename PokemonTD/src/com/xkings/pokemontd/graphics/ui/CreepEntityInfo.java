package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.CreepAbilityComponent;
import com.xkings.pokemontd.component.CreepTypeComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.SpriteComponent;

/**
 * User: Seda
 * Date: 26.10.13
 * Time: 22:03
 */

public class CreepEntityInfo extends InteractiveBlock {

    private final SpriteBatch spriteBatch;
    private final Ui ui;
    private final BitmapFont pixelFont;
    private final DisplayPicture picture;
    private final DisplayText health;
    private final DisplayText type;
    private final DisplayText name;
    private TextureAtlas.AtlasRegion region;
    private TextureAtlas.AtlasRegion regionCache;
    private String nameCache;
    private int healthCache;
    private String typeCache;


    public CreepEntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();

        float offset = height / 5;

        picture = new DisplayPicture(x + offset, y + offset, height - offset * 2, height - offset * 2, shapeRenderer,
                spriteBatch, Color.DARK_GRAY);

        health = new DisplayText(new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), shapeRenderer,
                spriteBatch);
        type = new DisplayText(new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), shapeRenderer,
                spriteBatch);
        name = new DisplayText(new Rectangle(x + offset, y + offset / 7, height - offset * 2, offset), shapeRenderer,
                spriteBatch, BitmapFont.HAlignment.CENTER);
    }

    @Override
    public void render() {
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        picture.render(regionCache);
        this.health.render("HP: " + healthCache);
        this.name.render(nameCache);
        this.type.render("" + typeCache);
    }



    public void render(Entity entity) {
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        CreepTypeComponent nameComponent = entity.getComponent(CreepTypeComponent.class);
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        CreepAbilityComponent creepTypeComponent = entity.getComponent(CreepAbilityComponent.class);
        if (spriteComponent != null && nameComponent != null && healthComponent != null) {
            this.regionCache = spriteComponent.getSprite();
            this.nameCache = nameComponent.getCreepType().getName().toString();
            this.healthCache = healthComponent.getHealth().getCurrentHealth();
            this.typeCache = creepTypeComponent.getCreepAbilityType().toString();
            render();
        }


    }

    /*@Override
    public void setEnabled(boolean enabled) {
        for (Clickable clickable : clickables) {
            clickable.setEnabled(enabled);
        }
    }         */
    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
