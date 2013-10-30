package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;
import com.xkings.pokemontd.component.attack.projectile.data.DotData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitDotSystem extends HitEffectSystem<DotData, DotEffect> {

    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;

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
    protected DotEffect resetEffect(Entity e, DotEffect effect, DotData effectData) {
        effect.set(effectData.getEffect(), effectData.getInterval(), effectData.getIterations(),
                damageMapper.get(e).getDps() * effectData.getDamageMultiplier() / effectData.getIterations());
        return effect;
    }

    @Override
    protected DotEffect createEffect(Entity e, DotData effectData) {
        return resetEffect(e, new DotEffect(), effectData);
    }
}
