package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class LifeStealSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<LifeStealEffect> lifeStealMapper;

    public LifeStealSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, LifeStealEffect.class));
    }

    @Override
    protected void process(Entity entity) {
        System.out.println("Peoces");
        LifeStealEffect lifeSteal = lifeStealMapper.get(entity);
        if (!lifeSteal.isStarted()) {
            registerEffect(entity, lifeSteal);
        }
        lifeSteal.update(world.delta);
        if (lifeSteal.isReady()) {
            unregisterEffect(entity, lifeSteal);
        }
    }

    private void registerEffect(Entity entity, LifeStealEffect lifeSteal) {
        Health health = healthMapper.get(entity).getHealth();
        float lifeStole = health.getMaxHealth() * lifeSteal.getRatio();
        lifeSteal.setStoleLife(lifeStole);
        health.decrees(lifeStole);
    }

    private void unregisterEffect(Entity entity, LifeStealEffect lifeSteal) {
        Health health = healthMapper.get(entity).getHealth();
        if (health.isAlive()) {
            health.increase(lifeSteal.getStoleLife());
            lifeSteal.setStoleLife(0);
        }
        entity.removeComponent(lifeSteal);
        world.changedEntity(entity);
    }
}
