package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.SpeedComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.TintComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;
import com.xkings.pokemontd.component.attack.effects.SlowEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class SlowSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<SlowEffect> effectMMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public SlowSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, SlowEffect.class, TintComponent.class));
    }

    @Override
    protected void process(Entity e) {
        SlowEffect effect = effectMMapper.get(e);
        SpeedComponent speed = speedMapper.get(e);
        if (!effect.isStarted()) {
            effect.setOldSpeed(speed.getSpeed());
            speed.scaleSpeed(1 - effect.getSlowRatio());
        }
        effect.update(world.delta);
        if (effect.isReady()) {
            speed.setSpeed(effect.getOldSpeed());
        }
    }
}
