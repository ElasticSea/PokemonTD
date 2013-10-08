package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.*;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.CostComponent;
import com.xkings.pokemontd.component.ProjectileComponent;
import com.xkings.pokemontd.component.SpriteComponent;

/**
 * Created by Tomas on 10/5/13.
 */
public class Tower extends ConcreteEntity {

    private Tower(TowerType towerType, World world, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new SpriteComponent(towerType.getTexture()));
        addComponent(new SizeComponent(towerType.getSize(), towerType.getSize(), 0));
        addComponent(new SpeedComponent(towerType.getSpeed()));
        addComponent(new ProjectileComponent(towerType.getProjectile()));
        addComponent(new RangeComponent(towerType.getRange()));
        addComponent(new TimeComponent());
        addComponent(new CostComponent(towerType.getCost()));
    }

    public static void registerTower(World world, TowerType towerType, float x, float y) {
        Tower tower = new Tower(towerType, world, x, y);
        tower.register();
    }
}
