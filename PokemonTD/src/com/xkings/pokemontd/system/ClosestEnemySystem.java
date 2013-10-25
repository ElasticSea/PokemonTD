package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.VisibleComponent;

public class ClosestEnemySystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<VisibleComponent> visibilityMapper;

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
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 size = sizeMapper.get(e).getPoint();
        if (!visibilityMapper.has(e) || visibilityMapper.get(e).isVisible()) {
            float distance = calculateDistance(entityPosition, position, size);
            if (this.entity != e && e.isEnabled() && distance <= entityRange && distance < closestDistance) {
                closestDistance = distance;
                closestEntity = e;
            }
        }
    }

    Vector3[] corners = new Vector3[]{new Vector3(), new Vector3(), new Vector3(), new Vector3()};

    private float calculateDistance(Vector3 originalPosition, Vector3 position, Vector3 size) {
        corners[0].x = position.x - size.x / 2;
        corners[0].y = position.y - size.y / 2;
        corners[1].x = corners[0].x + size.x;
        corners[1].y = corners[0].y;
        corners[2].x = corners[0].x;
        corners[2].y = corners[0].y + size.y;
        corners[3].x = corners[0].x + size.x;
        corners[3].y = corners[0].y + size.y;

        float distance = Float.MAX_VALUE;
        for(Vector3 corner : corners){
            float candidate = calculateDistance(corner, originalPosition);
            if(candidate < distance){
                distance  =candidate;
            }
        }
        return distance;
    }

    private float calculateDistance(Vector3 a,  Vector3 b ) {
        float tx = b.x - a.x;
        float ty = b.y - a.y;
        return (float) Math.sqrt(tx * tx + ty * ty);

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
