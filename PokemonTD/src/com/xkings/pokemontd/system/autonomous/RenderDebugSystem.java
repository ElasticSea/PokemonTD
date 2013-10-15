package com.xkings.pokemontd.system.autonomous;

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
    private final ShapeRenderer shapeRenderer = new ShapeRenderer(5000);

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
        RangeComponent rangeComponent = rangeMapper.get(e);
        if (rangeComponent.isVisible()) {
            shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.circle(position.x, position.y, rangeComponent.getRange(),
                    (int) (12 * (float) Math.cbrt(rangeComponent.getRange())) * 4);
            shapeRenderer.end();
        }
    }

}
