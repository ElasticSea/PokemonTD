package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.projectile.data.NormalData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitNormalSystem extends HitSystem<NormalData> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    public HitNormalSystem() {
        super(NormalData.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });

    }

    @Override
    protected void hit(NormalData effectData, Entity e, Entity target) {
        float damage = damageMapper.get(e).getDamage();
        healthMapper.get(target).getHealth().decease((int) damage);
    }

}
