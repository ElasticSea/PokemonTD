package com.pixelthieves.pokemontd.entity.tower;

import com.artemis.Entity;
import com.artemis.World;
import com.pixelthieves.core.component.*;
import com.pixelthieves.core.entity.ConcreteEntity;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.*;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;

/**
 * Created by Tomas on 10/5/13.
 */
public class Tower extends ConcreteEntity {

    private Tower(TowerType towerType, World world, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new SpriteComponent(towerType.getTexture()));
        addComponent(new SizeComponent(towerType.getSize(), towerType.getSize(), 0));
        addComponent(new BuffableSpeedComponent(towerType.getSpeed()));
        addComponent(new NameComponent(towerType.getName().toString()));
        addComponent(new RangeComponent(towerType.getRange()));
        addComponent(new TimeComponent());
        addComponent(new TreasureComponent(new Treasure(towerType.getCost())));
        addComponent(new TowerTypeComponent(towerType));
        addComponent(new UpgradeComponent());
        addComponent(new BuffableDamageComponent(towerType.getDamage()));
        addComponent(new EffectComponent(towerType.getEffectName()));
        if (towerType.getAttack() != null) {
            addComponent(towerType.getAttack());
        }
    }

    public static Entity registerTower(World world, TowerType towerType, float x, float y) {
        Tower tower = new Tower(towerType, world, x, y);
        if(towerType.getName().equals(TowerName.Shop)) {
            // FIXME shop component probably wont hold treasure
            tower.addComponent(new ShopComponent(new Treasure(0)));
        }
        tower.register();
        return tower.entity;
    }
}
