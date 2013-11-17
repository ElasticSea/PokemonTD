package com.pixelthieves.pokemontd.graphics.ui;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.component.CreepAbilityComponent;
import com.pixelthieves.pokemontd.component.CreepTypeComponent;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.SpriteComponent;
import com.pixelthieves.pokemontd.component.attack.effects.AbstractEffect;

/**
 * User: Seda
 * Date: 26.10.13
 * Time: 22:03
 */

public class CreepEntityInfo extends CommonInfo {

    private final DisplayText health;
    private final DisplayText type;
    private final Ui ui;
    private final EffectIcon[] effects;
    private int healthCache;
    private String typeCache;

    public CreepEntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                           BitmapFont font) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font);

        this.ui = ui;
        float offset = height / 5;
        float offsetBlocks = height / 4;

        health = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 3, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
        type = new DisplayText(ui, new Rectangle(x + offset * 5, y + offset * 2, offset * 2, offset), font,
                BitmapFont.HAlignment.LEFT);
        float effectButtonSize = height / 2;
        effects = getEffectButtons(5, picture.x + picture.width*3, picture.y, effectButtonSize, effectButtonSize);

    }

    private EffectIcon[] getEffectButtons(int count, float x, float y, float width, float height) {
        EffectIcon[] icons = new EffectIcon[count];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new EffectIcon(gui, x + width * i, y, width, height) {

                @Override
                public void process(float x, float y) {
                    ui.getAbilityInfo().update(effect);
                }
            };
            gui.register(icons[i]);
        }
        return icons;
    }

    private abstract class EffectIcon extends Icon {

        protected AbstractEffect effect;

        EffectIcon(Gui ui, float x, float y, float width, float height) {
            super(ui, x, y, width, height);
        }

        public AbstractEffect getEffect() {
            return effect;
        }

        public void setEffect(AbstractEffect effect) {
            this.effect = effect;
        }
    }

    @Override
    public void render() {
        super.render();
        this.health.render("HP: " + healthCache);
        this.type.render("" + typeCache);
        for (int i = 0; i < effects.length; i++) {
            EffectIcon icon = effects[i];
            AbstractEffect effect = icon.getEffect();
            if(effect != null){
                icon.render(effect.getTexture(), effect.getName());
            }
        }
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
            setEffects(entity);
        }
    }

    private void setEffects(Entity entity) {
        clearEffects();
        Bag<Component> fillBag = new Bag<Component>();
        entity.getComponents(fillBag);
        int pos = 0;
        for (int i = 0; i < fillBag.size(); i++) {
            Component component = fillBag.get(i);
            if (component instanceof AbstractEffect) {
                effects[pos++].setEffect((AbstractEffect) component);
            }
        }
    }

    private void clearEffects() {
        for (int i = 0; i < effects.length; i++) {
            effects[i].setEffect(null);
        }
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void refresh() {
        super.refresh();
        float offset = height / 5;
        health.set(x + offset * 5, y + offset * 3, offset * 2, offset);
        type.set(x + offset * 5, y + offset * 2, offset * 2, offset);
        health.refresh();
        type.refresh();
    }
}
