package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.SpeedComponent;
import com.xkings.core.entity.ConcreteEntity;
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
    }

    public static void registerTower(World world, TowerType towerType, float x, float y) {
        Tower tower = new Tower(towerType, world, x, y);
        tower.register();
    }
}
