package com.pixelthieves.elementtd.system.resolve;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.utils.Collision;
import com.pixelthieves.elementtd.component.VisibleComponent;

public abstract class IntersectEnemySystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<VisibleComponent> visibilityMapper;

    private Vector3 entityPosition;
    private Vector3 entitySize;

    /**
     * Finds closest enemy
     *
     * @param enemyType only enemy has this type
     */
    public IntersectEnemySystem(Class<? extends Component> enemyType) {
        super(Aspect.getAspectForAll(PositionComponent.class, enemyType));
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 size = sizeMapper.get(e).getPoint();
        if (!visibilityMapper.has(e) || visibilityMapper.get(e).isVisible()) {
            if (Collision.intersectRects(entityPosition, position, entitySize, size)) {
                intersect(e);
            }
        }
    }

    protected abstract void intersect(Entity e);

    public void start(Entity e) {
        this.entityPosition = positionMapper.get(e).getPoint();
        this.entitySize = sizeMapper.get(e).getPoint();
        this.process();
    }

}
