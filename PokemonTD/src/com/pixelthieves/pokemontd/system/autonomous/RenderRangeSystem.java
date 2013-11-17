package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.RangeComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.pokemontd.manager.TowerManager;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderRangeSystem extends EntityProcessingSystem {
    private final ShapeRenderer shapeRenderer;
    private final TowerManager towerManager;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    public RenderRangeSystem(ShapeRenderer shapeRenderer, TowerManager towerManager) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class));
        this.shapeRenderer = shapeRenderer;
        this.towerManager = towerManager;
    }

    @Override
    protected void begin() {
        shapeRenderer.setColor(Color.WHITE);
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        if (rangeMapper.has(e)) {
            float range = rangeMapper.get(e).getRange();
            if ((towerManager.getClicked() == e || towerManager.getPlaceholderTower() == e) && range > 0) {
                shapeRenderer.circle(position.x, position.y, range, (int) (12 * (float) Math.cbrt(range)) * 4);
            }
        }
    }

}
