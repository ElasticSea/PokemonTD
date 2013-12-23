package com.pixelthieves.elementtd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.elementtd.component.HealthComponent;
import com.pixelthieves.elementtd.component.attack.effects.DotEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class DotSystem extends EffectSystem<DotEffect> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<DotEffect> dotMapper;

    public DotSystem() {
        super(DotEffect.class);
    }

    @Override
    protected void finished(DotEffect effect, Entity e) {

    }

    @Override
    protected void started(DotEffect effect, Entity e) {

    }

    @Override
    protected void processEffect(DotEffect effect, Entity e) {
        HealthComponent healthComponent = healthMapper.getSafe(e);
        if (healthComponent != null) {
            healthComponent.getHealth().decease(dotMapper.get(e).getDamage());
        }
    }

    @Override
    protected void resetEffect(DotEffect effect, Entity e) {

    }

    @Override
    protected void reattachEffect(DotEffect effect, Entity e) {

    }
}
