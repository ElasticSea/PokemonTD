package com.pixelthieves.elementtd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderDebugSystem extends EntityProcessingSystem {
    private final ShapeRenderer shapeRenderer;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;

    public RenderDebugSystem(ShapeRenderer shapeRenderer) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class));
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    protected void begin() {
        shapeRenderer.setColor(Color.WHITE);
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 size = sizeMapper.get(e).getPoint();
        shapeRenderer.rect(position.x - size.x / 2, position.y - size.y / 2, size.x, size.y);
    }

}
