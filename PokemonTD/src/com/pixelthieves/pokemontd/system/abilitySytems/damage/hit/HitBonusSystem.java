package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.Health;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.projectile.data.BonusAttack;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitBonusSystem extends HitSystem<BonusAttack> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;

    public HitBonusSystem() {
        super(BonusAttack.class);
    }

    @Override
    protected void hit(BonusAttack effectData, Entity e, Entity target) {
        float damage = damageMapper.get(e).getDamage();
        HealthComponent healthComponent = healthMapper.getSafe(target);
        if (healthComponent != null) {
        Health health = healthComponent.getHealth();
            for (int i = 0; i < effectData.getIterations(); i++) {
                dealDamage(damage, health);
            }
        }
    }

    private void dealDamage(float damage, Health health) {
        health.decease((int) damage);
    }
}
