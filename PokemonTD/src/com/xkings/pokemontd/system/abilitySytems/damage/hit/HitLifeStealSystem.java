package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;
import com.xkings.pokemontd.component.attack.projectile.data.LifeStealData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitLifeStealSystem extends HitSystem<LifeStealData, LifeStealEffect> {

    public HitLifeStealSystem() {
        super(LifeStealData.class, LifeStealEffect.class);
    }

    @Override
    protected Component createEffect(Entity e, LifeStealData effectData) {
        return new LifeStealEffect(effectData.getDuration(), effectData.getLifeRationToSteal());
    }
}
