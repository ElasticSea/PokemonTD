package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.pixelthieves.pokemontd.component.attack.effects.DotEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.data.DotData;

/**
 * Created by Seda on 10/4/13.
 */
public class HitDotSystem extends HitEffectSystem<DotData, DotEffect> {

    public HitDotSystem() {
        super(DotData.class, DotEffect.class);
    }


    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected DotEffect resetEffect(Entity e, Entity target, DotEffect effect, DotData effectData) {
        effect.set(effectData.getEffect(), effectData.getInterval(), effectData.getIterations(),
                damageMapper.get(e).getDps() * effectData.getDamageMultiplier());
        return effect;
    }

    @Override
    protected DotEffect createEffect(Entity e, Entity target, DotData effectData) {
        return resetEffect(e, target, new DotEffect(), effectData);
    }
}
