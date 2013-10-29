package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.LifeStealEffect;
import com.xkings.pokemontd.component.attack.projectile.data.LifeStealData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitLifeStealSystem extends HitSystem {

    @Mapper
    ComponentMapper<LifeStealData> lifeStealDataMapper;
    @Mapper
    ComponentMapper<LifeStealEffect> tempLifeStealMapper;

    public HitLifeStealSystem() {
        super(LifeStealData.class);
    }

    @Override
    public void onHit(Entity entity, Entity target) {
        LifeStealData lifeSteal = lifeStealDataMapper.get(entity);
        LifeStealEffect effect = tempLifeStealMapper.get(target);
        if (effect == null) {
            target.addComponent(new LifeStealEffect(lifeSteal.getDuration(), lifeSteal.getLifeRationToSteal()));
            world.changedEntity(target);
        } else {
            effect.reset();
        }
    }

}
