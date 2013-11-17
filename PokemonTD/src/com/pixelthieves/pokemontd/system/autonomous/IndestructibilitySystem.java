package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.pathfinding.Blueprint;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.*;

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
