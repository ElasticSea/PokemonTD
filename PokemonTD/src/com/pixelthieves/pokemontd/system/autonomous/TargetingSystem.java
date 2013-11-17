package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.RotationComponent;
import com.pixelthieves.core.component.TargetComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class TargetingSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;


    public TargetingSystem() {
        super(Aspect.getAspectForAll(TargetComponent.class, PositionComponent.class, RotationComponent.class));
    }

    @Override
    protected void process(Entity e) {
        Vector3 a = positionMapper.get(e).getPoint();
        PositionComponent positionComponent = positionMapper.get(targetMapper.get(e).getTarget());
        if (positionComponent != null) {
            Vector3 b = positionComponent.getPoint();
            rotationMapper.get(e).getPoint().x = (float) (Math.atan2(b.y - a.y, b.x - a.x) / Math.PI * 180f);
        }
    }
}
