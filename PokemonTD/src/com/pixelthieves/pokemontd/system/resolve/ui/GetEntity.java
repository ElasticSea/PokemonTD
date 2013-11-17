package com.pixelthieves.pokemontd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;

public class GetEntity extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;

    private Entity entity;
    private float x;
    private float y;

    /**
     * Finds desired entity on given coordinates
     */
    public GetEntity(Class<? extends Component> filter) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, filter));
    }

    @Override
    protected void process(Entity entity) {
        Vector3 position = positionMapper.get(entity).getPoint();
        Vector3 size = sizeMapper.get(entity).getPoint();
        if (x >= position.x - size.x / 2 && x < position.x + size.x / 2) {
            if (y >= position.y - size.y / 2 && y < position.y + size.y / 2) {
                this.entity = entity;
            }
        }
    }

    public Entity getEntity(float x, float y) {
        this.x = x;
        this.y = y;
        this.entity = null;
        this.process();
        return entity;
    }

}
