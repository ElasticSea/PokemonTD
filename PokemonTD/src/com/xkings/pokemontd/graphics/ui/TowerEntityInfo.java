package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.component.SpeedComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.NameComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

/**
 * User: Seda
 * Date: 25.10.13
 * Time: 18:51
 */

public class TowerEntityInfo extends TowerInfo {

    public TowerEntityInfo(Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                           BitmapFont font) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font);
    }

    public void render(Entity entity) {
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        NameComponent nameComponent = entity.getComponent(NameComponent.class);
        RangeComponent rangeComponent = entity.getComponent(RangeComponent.class);
        Color damageColor = Color.WHITE;
        DamageComponent damageComponent = entity.getComponent(DamageComponent.class);
        Color rangeColor = Color.WHITE;
        SpeedComponent speedComponent = entity.getComponent(SpeedComponent.class);
        Color speedColor = Color.WHITE;
        TreasureComponent costComponent = entity.getComponent(TreasureComponent.class);

        if (spriteComponent != null && nameComponent != null && rangeComponent != null && damageComponent != null &&
                speedComponent != null) {

            float damage = damageComponent.getDamage();
            float speed = speedComponent.getSpeed();
            float range = rangeComponent.getRange();

            DamageBuffEffect damageBuff = entity.getComponent(DamageBuffEffect.class);
            if (damageBuff != null) {
                damage *= damageBuff.getRatio();
                damageColor = Color.GREEN;
            }
                                      /*
            DamageBuffEffect speedBuff = entity.getComponent(DamageBuffEffect.class);
            if(damageBuff != null){
                damage*= damageBuff.getRatio();
                damageColor = Color.GREEN;
            }

            DamageBuffEffect damageBuff = entity.getComponent(DamageBuffEffect.class);
            if(damageBuff != null){
                damage*= damageBuff.getRatio();
                damageColor = Color.GREEN;
            }           */

            render(spriteComponent.getSprite(), "Dmg: " + (int) damage, damageColor, "Spd: " + (int) speed, speedColor,
                    "Rng: " + (int) range, rangeColor, costComponent.getTreasure(), nameComponent.getName(), true,
                    false);
        }

    }

}
