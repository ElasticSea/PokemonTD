package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.projectile.HitAbility;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;
import com.xkings.pokemontd.component.attack.projectile.data.NormalData;
import com.xkings.pokemontd.entity.tower.TowerType;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:08
 */

public class TowerTypeInfo extends TowerInfo {

    TowerTypeInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                  BitmapFont font, AbilityInfo abilityInfo) {
        super(ui, rectangle, shapeRenderer, spriteBatch, font, abilityInfo);
    }

    public void render(TowerType tower) {
        boolean canbuy = ui.getTowerManager().canAfford(tower);
        render(tower.getTexture(), "Dmg: " + (int) tower.getDamage(), "Spd: " + (int) tower.getSpeed(),
                "Rng: " + (int) tower.getRange(), tower.getCost(), (tower.getName().toString()), getAbility(tower), tower.getSpeed(), tower.getDamage(), false, canbuy);
    }

    private EffectData getAbility(TowerType tower) {
        AbilityComponent attack = tower.getAttack();
        return  attack  instanceof HitAbility ? getEffectData((HitAbility) attack) : (EffectData)attack ;
    }

    private EffectData getEffectData(HitAbility attack) {
        for (EffectData effectData : attack.getEffectData()){
             if (!(effectData instanceof NormalData)) {
                 return effectData;
             }
        }
        return null;
    }

}
