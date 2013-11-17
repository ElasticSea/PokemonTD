package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.pixelthieves.pokemontd.component.attack.effects.IncreasingDamageEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.data.IncreasingDamageData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitIncreasingDamageSystem extends HitEffectSystem<IncreasingDamageData, IncreasingDamageEffect> {

    public HitIncreasingDamageSystem() {
        super(IncreasingDamageData.class, IncreasingDamageEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected IncreasingDamageEffect resetEffect(Entity e, Entity target, IncreasingDamageEffect effect,
                                                 IncreasingDamageData effectData) {
        effect.set(effectData.getEffect(), effectData.getDamageIncreasing(), effectData.getDuration(),
                damageMapper.get(e).getDamage(), effectData.getMax());
        return effect;
    }

    @Override
    protected IncreasingDamageEffect createEffect(Entity e, Entity target, IncreasingDamageData effectData) {
        return resetEffect(e, target, new IncreasingDamageEffect(), effectData);
    }
}
