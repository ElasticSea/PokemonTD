package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.DamageComponent;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.*;
import com.xkings.pokemontd.entity.StaticObject;
import com.xkings.pokemontd.entity.TextInfo;
import com.xkings.pokemontd.entity.creep.Creep;
import com.xkings.pokemontd.entity.creep.CreepAbilityType;
import com.xkings.pokemontd.entity.creep.CreepType;
import com.xkings.pokemontd.entity.datatypes.StaticObjectType;
import com.xkings.pokemontd.map.Path;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomas on 10/4/13.
 */
public class IndestructibilitySystem extends EntityProcessingSystem {

    private final Blueprint blueprint;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public IndestructibilitySystem(Blueprint blueprint) {
        super(Aspect.getAspectForAll(WaveComponent.class));
        this.blueprint = blueprint;
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 block = App.getBlockPosition(position.x, position.y);
        healthMapper.get(e).getHealth().setDestructible(blueprint.isWalkable((int)block.x,(int)block.y));
    }
}
