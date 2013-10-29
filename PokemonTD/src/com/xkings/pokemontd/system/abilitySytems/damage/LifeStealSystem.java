package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class LifeStealSystem extends EffectSystem<LifeStealEffect> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<LifeStealEffect> lifeStealMapper;

    public LifeStealSystem() {
        super(LifeStealEffect.class);
    }

    @Override
    protected void started(LifeStealEffect effect, Entity e) {
        Health health = healthMapper.get(e).getHealth();
        float lifeStole = health.getMaxHealth() * effect.getRatio();
        effect.setStoleLife(lifeStole);
        health.decease(lifeStole);
    }

    @Override
    protected void processEffect(LifeStealEffect effect, Entity e) {
        Health health = healthMapper.get(e).getHealth();
        if (health.isAlive()) {
            health.increase(effect.getStoleLife());
            effect.setStoleLife(0);
        }
        e.removeComponent(effect);
        world.changedEntity(e);
    }

}
