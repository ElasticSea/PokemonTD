package com.xkings.pokemontd.system.abilitySytems.projectile.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.attack.effects.SlowEffect;
import com.xkings.pokemontd.component.attack.projectile.data.SlowData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitSlowSystem extends HitSystem {

    @Mapper
    ComponentMapper<SlowData> dataMapper;
    @Mapper
    ComponentMapper<SlowEffect> effectMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    public HitSlowSystem() {
        super(SlowData.class);
    }

    @Override
    public void onHit(Entity e, Entity target) {
        SlowData data = dataMapper.get(e);
        SlowEffect effect = effectMapper.get(target);
        if (effect == null) {
            if (App.CHANCE.happens(data.getChance())) {
                target.addComponent(new SlowEffect(data.getDuration(), data.getSlowRatio()));
                world.changedEntity(target);
            }
        } else {
            effect.reset();
        }
    }

}
