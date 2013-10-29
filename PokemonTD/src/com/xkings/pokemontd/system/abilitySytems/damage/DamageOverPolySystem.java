package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class DamageOverPolySystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    private float damage;
    private Vector2[] poly;

    public DamageOverPolySystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, PositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        Vector3 point = positionMapper.get(entity).getPoint();
        if (Collision.pointInsidePolygon(poly, point.x, point.y)) {
            healthMapper.get(entity).getHealth().decease((int) damage);
        }
    }

    public void start(Vector2[] poly, float damage) {
        this.poly = poly;
        this.damage = damage;
        this.process();
    }

}
