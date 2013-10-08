package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.component.SizeComponent;

public class ClosestEnemySystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    private Entity closestEntity;
    private float closestDistance;
    private Vector3 entityPosition;
    private float entityRange;
    private Entity entity;

    /**
     * Finds closest enemy
     *
     * @param enemyType only enemy has this type
     */
    public ClosestEnemySystem(Class<? extends Component> enemyType) {
        super(Aspect.getAspectForAll(PositionComponent.class, enemyType));
    }

    @Override
    protected void process(Entity entity) {
        Vector3 position = positionMapper.get(entity).getPoint();
        Vector3 size = sizeMapper.get(entity).getPoint();
        float tx = entityPosition.x - position.x;
        float ty = entityPosition.y - position.y;
        float distance = (float) Math.sqrt(tx * tx + ty * ty);
        float range = entityRange + (size.x + size.y) / 2f;
        if (this.entity != entity && entity.isEnabled() && distance <= range && distance < closestDistance) {
            closestDistance = distance;
            closestEntity = entity;
        }
    }

    public void start(Entity entity, Vector3 position, float range) {
        this.entity = entity;
        this.entityPosition = position;
        this.entityRange = range;
        this.closestDistance = Float.MAX_VALUE;
        this.closestEntity = null;
        this.process();
    }

    public Entity getClosestEntity() {
        return closestEntity;
    }
}
