package com.pixelthieves.elementtd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.component.HealthComponent;
import com.pixelthieves.elementtd.component.WaveComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class IndestructibilitySystem extends EntityProcessingSystem {

    private final App app;
    public Vector3 STORE = new Vector3();
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public IndestructibilitySystem(App app) {
        super(Aspect.getAspectForAll(WaveComponent.class));
        this.app = app;
        this.STORE = new Vector3();
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        App.getBlockPositionOptimized(position.x, position.y, STORE);
        healthMapper.get(e).getHealth().setDestructible(app.getMap().getGameBlueprint().isWalkable((int) STORE.x, (int) STORE.y));
    }
}
