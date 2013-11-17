package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.Health;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.projectile.data.LifeData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitLifeStealSystem extends HitSystem<LifeData> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageBuffMapper;

    public HitLifeStealSystem() {
        super(LifeData.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });

    }

    @Override
    protected void hit(LifeData effectData, Entity e, Entity target) {
        float damage = damageBuffMapper.get(e).getDamage();
        HealthComponent healthComponent = healthMapper.getSafe(target);
        if (healthComponent != null) {
            Health health = healthComponent.getHealth();
            health.decease((int) damage);
            if (!health.isAlive()) {
                health.setStealLife(true);
                health.setEarnTreasure(false);
            }
        }
    }

}
