package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.Health;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.TreasureComponent;
import com.pixelthieves.pokemontd.component.attack.projectile.data.MoneyData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitMoneySystem extends HitSystem<MoneyData> {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TreasureComponent> treasureMapper;

    public HitMoneySystem() {
        super(MoneyData.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });

    }

    @Override
    protected void hit(MoneyData effectData, Entity e, Entity target) {
        float damage = damageMapper.get(e).getDamage();
        HealthComponent healthComponent = healthMapper.getSafe(target);
        if (healthComponent != null) {
            Health health = healthComponent.getHealth();
            health.decease((int) damage);
            if (!health.isAlive()) {
                treasureMapper.get(target).getTreasure().multiplyGold(effectData.getEarnRatio());
            }
        }
    }

}
