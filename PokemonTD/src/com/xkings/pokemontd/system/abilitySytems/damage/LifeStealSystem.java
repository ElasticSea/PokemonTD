package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.TempLifeEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class LifeStealSystem extends EffectSystem<TempLifeEffect> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public LifeStealSystem() {
        super(TempLifeEffect.class);
    }

    @Override
    protected void finished(TempLifeEffect effect, Entity e) {

    }

    @Override
    protected void started(TempLifeEffect effect, Entity e) {
        Health health = healthMapper.get(e).getHealth();
        float lifeStole = health.getMaxHealth() * effect.getRatio();
        effect.setStoleLife(lifeStole);
        health.decease(lifeStole);
    }

    @Override
    protected void processEffect(TempLifeEffect effect, Entity e) {
        Health health = healthMapper.get(e).getHealth();
        if (health.isAlive()) {
            health.increase(effect.getStoleLife());
            effect.setStoleLife(0);
        }
        e.removeComponent(effect);
        world.changedEntity(e);
    }

    @Override
    protected void resetEffect(TempLifeEffect effect, Entity e) {
        processEffect(effect, e);
        started(effect, e);
    }

    @Override
    protected void reattachEffect(TempLifeEffect effect, Entity e) {

    }

}
