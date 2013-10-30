package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.component.RangeComponent;
import com.xkings.pokemontd.component.NameComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;
import com.xkings.pokemontd.component.attack.effects.buff.SpeedBuffEffect;

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
        BuffableDamageComponent damageComponent = entity.getComponent(BuffableDamageComponent.class);
        Color rangeColor = Color.WHITE;
        BuffableSpeedComponent speedComponent = entity.getComponent(BuffableSpeedComponent.class);
        Color speedColor = Color.WHITE;
        TreasureComponent costComponent = entity.getComponent(TreasureComponent.class);

        if (spriteComponent != null && nameComponent != null && rangeComponent != null && damageComponent != null &&
                speedComponent != null) {

            float range = rangeComponent.getRange();

            if (damageComponent.getBuff() != null) {
                damageColor = Color.GREEN;
            }

            if (speedComponent.getBuff() != null) {
                speedColor = Color.YELLOW;
            }
            /*

            DamageBuffEffect damage = entity.getComponent(DamageBuffEffect.class);
            if(damage != null){
                damage*= damage.getRatio();
                damageColor = Color.GREEN;
            }           */

            render(spriteComponent.getSprite(), "Dmg: " + (int) damageComponent.getDamage(), damageColor, "Spd: " + (int) speedComponent.getSpeed(), speedColor,
                    "Rng: " + (int) range, rangeColor, costComponent.getTreasure(), nameComponent.getName(), true,
                    false);
        }

    }

}
