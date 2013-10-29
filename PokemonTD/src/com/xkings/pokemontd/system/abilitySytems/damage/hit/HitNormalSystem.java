package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.projectile.data.NormalData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitNormalSystem extends HitSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;


    public HitNormalSystem() {
        super(NormalData.class);
    }

    public void onHit(Entity entity, Entity target) {
        float damage = damageMapper.get(entity).getDamage();
        healthMapper.get(target).getHealth().decease((int) damage);
    }

}
