package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class DamageBuffSystem extends EffectSystem<DamageBuffEffect> {

    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;

    public DamageBuffSystem() {
        super(DamageBuffEffect.class);
    }

    @Override
    protected void finished(DamageBuffEffect effect, Entity e) {
        damageMapper.get(e).setBuff(null);
    }

    @Override
    protected void started(DamageBuffEffect effect, Entity e) {
        damageMapper.get(e).setBuff(effect);
    }

    @Override
    protected void processEffect(DamageBuffEffect effect, Entity e) {

    }

    @Override
    protected void resetEffect(DamageBuffEffect effect, Entity e) {

    }

    @Override
    protected void reattachEffect(DamageBuffEffect effect, Entity e) {
        started(effect, e);
    }
}
