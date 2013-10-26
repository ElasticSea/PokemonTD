package com.xkings.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.component.SpeedComponent;
import com.xkings.core.component.TimeComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;
import com.xkings.pokemontd.component.attack.projectile.ProjectileComponent;
import com.xkings.pokemontd.system.ClosestTowerSystem;
import com.xkings.pokemontd.system.ClosestTowerWithoutDamageBuffSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class ApplyBuffSystem extends ApplyAbilitySystem {

    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    @Mapper
    ComponentMapper<ProjectileComponent> projectileMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<BuffAbility> buffMapper;
    @Mapper
    ComponentMapper<DamageBuffEffect> damageBuffMapper;

    public ApplyBuffSystem() {
        super(BuffAbility.class, ClosestTowerSystem.class);
    }

    @Override
    protected void run(Entity entity) {
        BuffAbility data = buffMapper.get(entity);
        switch (data.getType()) {
            case SPEED:
                throw new UnsupportedOperationException("This is yet to be supported.");
            case DAMAGE:
                this.setClosestEntityAlgorithm(ClosestTowerWithoutDamageBuffSystem.class);
                break;
            case RANGE:
                throw new UnsupportedOperationException("This is yet to be supported.");
        }
        super.run(entity);
    }

    @Override
    protected void processTarget(Entity entity, Entity target) {
        BuffAbility data = buffMapper.get(entity);
        DamageBuffEffect damageBuff = damageBuffMapper.get(target);

        if (damageBuff == null) {
            System.out.println("Added buff");
            target.addComponent(new DamageBuffEffect(data.getDuration(), data.getRatio()));
            target.changedInWorld();
        }
    }
}
