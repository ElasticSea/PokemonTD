package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;
import com.xkings.pokemontd.component.attack.projectile.data.LifeStealData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitLifeStealSystem extends HitEffectSystem<LifeStealData, LifeStealEffect> {

    public HitLifeStealSystem() {
        super(LifeStealData.class, LifeStealEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected LifeStealEffect resetEffect(Entity e, Entity target, LifeStealEffect effect, LifeStealData effectData) {
        effect.set( effectData.getDuration(), effectData.getLifeRatioToSteal());
        return effect;
    }

    @Override
    protected LifeStealEffect createEffect(Entity e, Entity target, LifeStealData effectData) {
        return resetEffect(e, target, new LifeStealEffect(), effectData);
    }
}
