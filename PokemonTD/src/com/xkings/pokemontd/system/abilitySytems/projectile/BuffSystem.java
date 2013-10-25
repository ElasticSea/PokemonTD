package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffEffect;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class BuffSystem extends EffectSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<DamageBuffEffect> buffMapper;

    public BuffSystem() {
        super(DamageBuffEffect.class);
    }

    @Override
    protected void processEffect(Entity e) {
        System.out.println(buffMapper.get(e));
        //healthMapper.get(e).getHealth().decrees(dotMapper.get(e).getDamage());
    }
}
