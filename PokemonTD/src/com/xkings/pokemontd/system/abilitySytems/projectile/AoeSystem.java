package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class AoeSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    private float damage;
    private float aoe;
    private Vector3 position;

    public AoeSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, PositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        if (position.dst(positionMapper.get(entity).getPoint()) <= aoe) {
              healthMapper.get(entity).getHealth().decrees((int) damage);
        }
    }

    public void start(Vector3 position, float damage, float aoe) {
        this.position = position;
        this.damage = damage;
        this.aoe = aoe;
        this.process();
    }

}
