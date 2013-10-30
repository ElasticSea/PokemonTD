package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.projectile.data.BonusAttack;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitBonusSystem extends OldHitSystem<BonusAttack> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;

    public HitBonusSystem() {
        super(BonusAttack.class);
    }

    @Override
    public void onHit(BonusAttack effectData, Entity e, Entity target) {
        float damage = damageMapper.get(e).getDamage();
        Health health = healthMapper.get(target).getHealth();

        dealDamage(damage, health);
        if (App.CHANCE.happens(effectData.getChance())) {
            for (int i = 0; i < effectData.getIterations(); i++) {
                dealDamage(damage, health);
            }
        }
    }

    private void dealDamage(float damage, Health health) {
        health.decease((int) damage);
    }

}
