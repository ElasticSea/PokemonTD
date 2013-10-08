package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.graphics.camera.CameraHandler;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderDebugSystem extends EntityProcessingSystem {
    private final CameraHandler camera;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    public RenderDebugSystem(CameraHandler camera) {
        super(Aspect.getAspectForAll(PositionComponent.class, RangeComponent.class));
        this.camera = camera;
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        float range = rangeMapper.get(e).getRange();

        shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(position.x, position.y, range,
                (int) (12 * (float) Math.cbrt(range) / camera.getCamera().zoom));
        shapeRenderer.end();
    }

}
