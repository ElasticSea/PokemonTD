package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.component.SpeedComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.NameComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TreasureComponent;

/**
 * User: Seda
 * Date: 25.10.13
 * Time: 18:51
 */

public class TowerEntityInfo  extends TowerInfo{

    public TowerEntityInfo(Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(ui, rectangle, shapeRenderer, spriteBatch);
    }

    public void render(Entity entity){
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        NameComponent nameComponent = entity.getComponent(NameComponent.class);
        RangeComponent rangeComponent = entity.getComponent(RangeComponent.class);
        DamageComponent damageComponent = entity.getComponent(DamageComponent.class);
        SpeedComponent speedComponent = entity.getComponent(SpeedComponent.class);
        TreasureComponent costComponent = entity.getComponent(TreasureComponent.class);

        if (spriteComponent != null && nameComponent != null && rangeComponent != null && damageComponent !=null && speedComponent !=null) {
            render(spriteComponent.getSprite(),"Dmg: " +(int) damageComponent.getDamage(),"Spd: " + (int) speedComponent.getSpeed(),"Rng: " +(int) rangeComponent.getRange(),"Cost: " + costComponent.getTreasure().getGold(), nameComponent.getName(), true, false);
        }

    }


}
