package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.xkings.pokemontd.component.attack.effects.SlowEffect;
import com.xkings.pokemontd.component.attack.projectile.data.SlowData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitChangeDirection extends HitEffectSystem<SlowData, SlowEffect> {

    public HitChangeDirection() {
        super(SlowData.class, SlowEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected SlowEffect resetEffect(Entity e, SlowEffect effect, SlowData effectData) {
        effect.set(effectData.getEffect(), effectData.getDuration(), effectData.getSlowRatio());
        return effect;
    }

    @Override
    protected SlowEffect createEffect(Entity e, SlowData effectData) {
        return resetEffect(e, new SlowEffect(), effectData);
    }
}
