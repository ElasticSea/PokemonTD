package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.attack.projectile.data.AoeComponent;
import com.xkings.pokemontd.system.abilitySytems.damage.AoeSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitAoeSystem extends HitSystem {

    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<AoeComponent> aoeMapper;


    public HitAoeSystem() {
        super(AoeComponent.class);
    }

    public void onHit(Entity entity, Entity target) {
        float damage = damageMapper.get(entity).getDamage();
        world.getSystem(AoeSystem.class).start(positionMapper.get(target).getPoint(), damage,
                aoeMapper.get(entity).getRange());
    }

}
