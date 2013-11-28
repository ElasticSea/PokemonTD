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
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.WaveComponent;

/**
 * Created by Seda on 10/4/13.
 */
public class IndestructibilitySystem extends EntityProcessingSystem {

    public Vector3 STORE = new Vector3();
    private final Blueprint blueprint;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public IndestructibilitySystem(Blueprint blueprint) {
        super(Aspect.getAspectForAll(WaveComponent.class));
        this.blueprint = blueprint;
        this.STORE = new Vector3();
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        App.getBlockPositionOptimized(position.x, position.y, STORE);
        healthMapper.get(e).getHealth().setDestructible(blueprint.isWalkable((int) STORE.x, (int) STORE.y));
    }
}
