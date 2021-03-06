package com.pixelthieves.elementtd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.elementtd.component.HealthComponent;
import com.pixelthieves.elementtd.component.attack.effects.IncreasingDamageEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class IncreasingDamageSystem extends EffectSystem<IncreasingDamageEffect> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public IncreasingDamageSystem() {
        super(IncreasingDamageEffect.class);
    }

    @Override
    protected void process(Entity e) {
        super.process(e);
        // System.out.println("processing");
    }

    @Override
    protected void finished(IncreasingDamageEffect effect, Entity e) {
    }

    @Override
    protected void started(IncreasingDamageEffect effect, Entity e) {
        healthMapper.get(e).getHealth().decease(effect.getCurrentDamage());
       /* BuffableSpeedComponent speed = speedMapper.get(e);
        effect.setOldSpeed(speed.getSpeed());
        speed.scaleSpeed(1 - effect.getSlowRatio()); */
    }

    @Override
    protected void processEffect(IncreasingDamageEffect effect, Entity e) {
       /* BuffableSpeedComponent speed = speedMapper.get(e);
        speed.setSpeed(effect.getOldSpeed());  */
    }

    @Override
    protected void resetEffect(IncreasingDamageEffect effect, Entity e) {
        processEffect(effect, e);
        started(effect, e);
    }

    @Override
    protected void reattachEffect(IncreasingDamageEffect effect, Entity e) {

    }
}
