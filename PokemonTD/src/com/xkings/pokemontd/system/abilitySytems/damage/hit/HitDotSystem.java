package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;
import com.xkings.pokemontd.component.attack.projectile.data.DotData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitDotSystem extends HitSystem<DotData, DotEffect> {

    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    public HitDotSystem() {
        super(DotData.class, DotEffect.class);
    }

    @Override
    protected Component createEffect(Entity e, DotData effectData) {
        return new DotEffect(effectData.getEffect(), effectData.getInterval(), effectData.getIterations(),
                damageMapper.get(e).getDps() * effectData.getDamageMultiplier() / effectData.getIterations());
    }
}
