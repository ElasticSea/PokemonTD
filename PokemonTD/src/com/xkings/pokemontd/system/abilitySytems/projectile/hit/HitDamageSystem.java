package com.xkings.pokemontd.system.abilitySytems.projectile.hit;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.TargetComponent;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.OneTimeDamageComponent;
import com.xkings.pokemontd.component.attack.ProjectileComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitDamageSystem extends HitSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;


    public HitDamageSystem() {
        super(OneTimeDamageComponent.class);
    }

    public void onHit(Entity entity, Entity target) {
        float damage = damageMapper.get(entity).getDamage();
        System.out.println(damage);
        healthMapper.get(target).getHealth().decrees((int) damage);
    }

}
