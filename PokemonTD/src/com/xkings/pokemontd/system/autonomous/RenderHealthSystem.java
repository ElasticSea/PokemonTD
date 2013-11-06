package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderHealthSystem extends EntityProcessingSystem {
    private final ShapeRenderer shapeRenderer;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public RenderHealthSystem(ShapeRenderer shapeRenderer) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, HealthComponent.class));
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    protected void begin() {
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    protected void end() {
        shapeRenderer.end();
    }

    @Override
    protected void process(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        Vector3 size = sizeMapper.get(e).getPoint();
        Health health = healthMapper.get(e).getHealth();
        if (health.isDestructible()) {

            float healthBarHeight = size.y / 10f;
            float x = position.x - size.x / 2;
            float y = position.y + size.y / 2 + healthBarHeight;

            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(x, y, size.x, healthBarHeight);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(x, y, size.x * health.getRatio(), healthBarHeight);
        }
    }
}
