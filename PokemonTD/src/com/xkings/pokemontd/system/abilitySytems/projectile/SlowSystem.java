package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.core.component.SpeedComponent;
import com.xkings.pokemontd.component.attack.effects.SlowEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class SlowSystem extends EffectSystem {

    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<SlowEffect> effectMMapper;

    public SlowSystem() {
        super(SlowEffect.class);
    }

    @Override
    protected void started(Entity e) {
        SlowEffect effect = effectMMapper.get(e);
        SpeedComponent speed = speedMapper.get(e);
        effect.setOldSpeed(speed.getSpeed());
        speed.scaleSpeed(1 - effect.getSlowRatio());
    }

    @Override
    protected void processEffect(Entity e) {
        SlowEffect effect = effectMMapper.get(e);
        SpeedComponent speed = speedMapper.get(e);
        speed.setSpeed(effect.getOldSpeed());
    }
}
