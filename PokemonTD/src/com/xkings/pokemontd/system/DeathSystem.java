package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.AttackComponent;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class DeathSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public DeathSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class));
    }


    @Override
    protected void process(Entity entity) {
        Health health = healthMapper.get(entity).getHealth();
        if(!isAlive(health)){
            entity.deleteFromWorld();
        }
    }

    private boolean isAlive(Health health) {
        return health.getHealth() > 0;
    }

}
