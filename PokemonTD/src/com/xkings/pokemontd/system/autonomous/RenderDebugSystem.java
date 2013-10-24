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
import com.xkings.pokemontd.manager.TowerManager;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderDebugSystem extends EntityProcessingSystem {
    private final CameraHandler camera;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer(5000);
    private final TowerManager towerManager;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    public RenderDebugSystem(CameraHandler camera, TowerManager towerManager) {
        super(Aspect.getAspectForAll(PositionComponent.class, RangeComponent.class));
        this.camera = camera;
        this.towerManager = towerManager;
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        float range = rangeMapper.get(e).getRange();
        if ((towerManager.getClicked() == e || towerManager.getPlaceholderTower() == e) && range > 0) {
            shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.circle(position.x, position.y, range, (int) (12 * (float) Math.cbrt(range)) * 4);
            shapeRenderer.end();
        }
    }

}
