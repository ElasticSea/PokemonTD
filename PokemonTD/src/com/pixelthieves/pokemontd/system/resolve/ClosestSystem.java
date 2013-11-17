package com.pixelthieves.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;

public class ClosestSystem extends PickEntitySystem {
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;

    private Entity closestEntity;

    /**
     * Finds closest entity
     *
     * @param entityType only concrete entity has this type
     */
    public ClosestSystem(Class<? extends Component> entityType) {
        super(Aspect.getAspectForAll(PositionComponent.class, entityType), false);
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 size = sizeMapper.get(e).getPoint();
        float distance = calculateDistance(entityPosition, position, size);
        if (isRequirementMet(e) && distance < closestDistance) {
            closestDistance = distance;
            closestEntity = e;
        }
    }

    protected boolean isRequirementMet(Entity e) {
        return true;
    }

    @Override
    public Entity getPickedEntity() {
        return closestEntity;
    }
}
