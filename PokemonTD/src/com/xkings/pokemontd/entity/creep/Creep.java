package com.xkings.pokemontd.entity.creep;

import com.artemis.World;
import com.xkings.core.component.*;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.*;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/5/13.
 */
public class Creep extends ConcreteEntity {

    private Creep(CreepType creepType, CreepAbilityType creepAbilityType, Path path, WaveComponent waveComponent,
                  World world, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(path));
        addComponent(new NameComponent(creepType.getName().toString()));
        addComponent(new SpriteComponent(creepType.getTexture()));
        addComponent(new SizeComponent(creepType.getSize(), creepType.getSize(), 0));
        addComponent(new SpeedComponent(creepType.getSpeed()));
        addComponent(new HealthComponent(new Health(creepType.getHealth())));
        addComponent(new TreasureComponent(creepType.getTreasure()));
        addComponent(new TimeComponent());
        addComponent(new CreepAbilityComponent(creepAbilityType));
        addComponent(new DamageComponent(1));
        addComponent(new CreepTypeComponent(creepType));
        addComponent(waveComponent);
    }

    public static void registerCreep(World world, Path path, WaveComponent waveComponent, CreepType creepType, float x,
                                     float y) {
        registerCreep(world, path, waveComponent, creepType, creepType.getAbilityType(), x, y);
    }

    public static void registerCreep(World world, Path path, WaveComponent waveComponent, CreepType creepType,
                                     CreepAbilityType creepAbilityType, float x, float y) {
        Creep creep = new Creep(creepType, creepAbilityType, path, waveComponent, world, x, y);
        creep.register();
        waveComponent.addCreep(creep.entity);
    }
}
