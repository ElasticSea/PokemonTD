package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.SpriteComponent;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class Tower extends ConcreteEntity {

    public Tower(World world, AtlasRegion texture, int x, int y, int size) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new SpriteComponent(texture));
        addComponent(new SizeComponent(size, size, 0));
    }

    public static void registerTower(World world, TowerType towerType, int x, int y, int size) {
        Tower tower = new Tower(world, towerType.getTexture(), x, y, size);
        tower.register();
    }
}
