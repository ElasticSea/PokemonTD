package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Component;
import com.artemis.Entity;
import com.xkings.pokemontd.component.attack.effects.SlowEffect;
import com.xkings.pokemontd.component.attack.projectile.data.SlowData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitSlowSystem extends HitSystem<SlowData, SlowEffect> {

    public HitSlowSystem() {
        super(SlowData.class, SlowEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        initAoe();
    }

    @Override
    protected Component createEffect(Entity e, SlowData effectData) {
        return new SlowEffect(effectData.getEffect(), effectData.getDuration(), effectData.getSlowRatio());
    }
}
