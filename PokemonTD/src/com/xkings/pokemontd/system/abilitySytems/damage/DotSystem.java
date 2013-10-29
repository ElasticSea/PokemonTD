package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class DotSystem extends EffectSystem<DotEffect> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<DotEffect> dotMapper;

    public DotSystem() {
        super(DotEffect.class);
    }

    @Override
    protected void processEffect(DotEffect effect, Entity e) {
        healthMapper.get(e).getHealth().decease(dotMapper.get(e).getDamage());
    }
}
