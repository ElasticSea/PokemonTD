package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class LifeStealSystem extends EffectSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<LifeStealEffect> lifeStealMapper;

    public LifeStealSystem() {
        super(LifeStealEffect.class);
    }

    @Override
    protected void started(Entity e) {
        LifeStealEffect lifeSteal = lifeStealMapper.get(e);
        Health health = healthMapper.get(e).getHealth();
        float lifeStole = health.getMaxHealth() * lifeSteal.getRatio();
        lifeSteal.setStoleLife(lifeStole);
        health.decrees(lifeStole);
    }

    @Override
    protected void processEffect(Entity e) {
        LifeStealEffect lifeSteal = lifeStealMapper.get(e);
        Health health = healthMapper.get(e).getHealth();
        if (health.isAlive()) {
            health.increase(lifeSteal.getStoleLife());
            lifeSteal.setStoleLife(0);
        }
        e.removeComponent(lifeSteal);
        world.changedEntity(e);
    }

}
