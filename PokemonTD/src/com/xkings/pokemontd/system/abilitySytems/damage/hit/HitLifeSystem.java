package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.DamageComponent;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.projectile.data.LifeData;
import com.xkings.pokemontd.entity.TextInfo;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitLifeSystem extends HitSystem<LifeData> {

    private final Health health;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageBuffMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    public HitLifeSystem(Health health) {
        super(LifeData.class);
        this.health = health;
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
        Vector3 position = positionMapper.get(target).getPoint();
        float damage = damageBuffMapper.get(e).getDamage();
        Health health = healthMapper.get(target).getHealth();
        health.decease((int) damage);
        if (!health.isAlive()) {
            health.setStealLife(true);
            health.setEarnTreasure(false);
        }
    }

}
