package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;
import com.xkings.pokemontd.component.attack.effects.buff.SpeedBuffEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class SpeedBuffSystem extends EffectSystem<SpeedBuffEffect> {

    @Mapper
    ComponentMapper<BuffableSpeedComponent> speedMapper;

    public SpeedBuffSystem() {
        super(SpeedBuffEffect.class);
    }

    @Override
    protected void finished(SpeedBuffEffect effect, Entity e) {
        speedMapper.get(e).setBuff(null);
    }

    @Override
    protected void started(SpeedBuffEffect effect, Entity e) {
        speedMapper.get(e).setBuff(effect);
    }

    @Override
    protected void processEffect(SpeedBuffEffect effect, Entity e) {

    }

    @Override
    protected void resetEffect(SpeedBuffEffect effect, Entity e) {

    }

    @Override
    protected void reattachEffect(SpeedBuffEffect effect, Entity e) {
        started(effect, e);
    }
}
