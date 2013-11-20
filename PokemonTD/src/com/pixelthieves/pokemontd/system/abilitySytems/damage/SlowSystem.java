package com.pixelthieves.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.attack.effects.SlowEffect;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class SlowSystem extends EffectSystem<SlowEffect> {

    @Mapper
    ComponentMapper<BuffableSpeedComponent> speedMapper;

    public SlowSystem() {
        super(SlowEffect.class);
    }

    @Override
    protected void finished(SlowEffect effect, Entity e) {
    }

    @Override
    protected void started(SlowEffect effect, Entity e) {
        BuffableSpeedComponent speed = speedMapper.get(e);
        effect.setOldSpeed(speed.getSpeed());
        speed.scaleSpeed(1 - effect.getSlowRatio());
    }

    @Override
    protected void processEffect(SlowEffect effect, Entity e) {
        BuffableSpeedComponent speed = speedMapper.get(e);
        speed.setSpeed(effect.getOldSpeed());
    }

    @Override
    protected void resetEffect(SlowEffect effect, Entity e) {
        processEffect(effect, e);
        started(effect, e);
    }

    @Override
    protected void reattachEffect(SlowEffect effect, Entity e) {

    }
}
