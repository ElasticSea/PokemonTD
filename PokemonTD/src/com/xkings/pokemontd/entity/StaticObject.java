package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.SpriteComponent;

/**
 * Created by Tomas on 10/5/13.
 */
public class StaticObject extends ConcreteEntity {

    private StaticObject(StaticObjectType staticObjectType, World world, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(null));
        addComponent(new SpriteComponent(staticObjectType.getTexture()));
        addComponent(new SizeComponent(staticObjectType.getSize(), staticObjectType.getSize(), 0));
    }

    public static void registerStaticObject(World world, StaticObjectType staticObjectType, float x, float y) {
        StaticObject staticObject = new StaticObject(staticObjectType, world, x, y);
        staticObject.register();
    }
}
