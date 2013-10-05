package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.SpriteComponent;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class Creep extends ConcreteEntity {

    public Creep(World world, AtlasRegion texture, float x, float y, int size) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0,0, 0));
        addComponent(new PathComponent(null));
        addComponent(new SpriteComponent(texture));
        addComponent(new SizeComponent(size, size, 0));
    }

    public static void registerCreep(World world, TowerType towerType, float x, float y, int size) {
        Creep creep = new Creep(world, towerType.getTexture(), x, y, size);
        creep.register();
    }
}
