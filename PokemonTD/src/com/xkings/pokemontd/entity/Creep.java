package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.SpeedComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class Creep extends ConcreteEntity {

    private Creep(CreepType creepType, WaveComponent waveComponent, World world, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(null));
        addComponent(new SpriteComponent(creepType.getTexture()));
        addComponent(new SizeComponent(creepType.getSize(), creepType.getSize(), 0));
        addComponent(new SpeedComponent(creepType.getSpeed()));
        addComponent(new HealthComponent(creepType.getHealth()));
        addComponent(new TreasureComponent(creepType.getTreasure()));
        addComponent(waveComponent);
    }

    public static void registerCreep(World world, WaveComponent waveComponent, CreepType creepType, float x, float y) {
        Creep creep = new Creep(creepType, waveComponent, world, x, y);
        creep.register();
    }
}
