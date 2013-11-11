package com.xkings.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;
import com.xkings.pokemontd.component.attack.effects.buff.SpeedBuffEffect;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;
import com.xkings.pokemontd.system.resolve.ClosestSystemTower;
import com.xkings.pokemontd.system.resolve.ClosestSystemTowerWithoutDamageBuff;
import com.xkings.pokemontd.system.resolve.ClosestSystemTowerWithoutSpeedBuff;

/**
 * Created by Tomas on 10/4/13.
 */
public class ApplyBuffSystem extends ApplyAbilitySystem<BuffAbility> {

    @Mapper
    ComponentMapper<BuffAbility> buffMapper;
    @Mapper
    ComponentMapper<SpeedBuffEffect> speedBuffMapper;
    @Mapper
    ComponentMapper<DamageBuffEffect> damageBuffMapper;

    public ApplyBuffSystem() {
        super(BuffAbility.class, ClosestSystemTower.class);
    }

    @Override
    protected void run(BuffAbility ability, Entity entity) {
        BuffAbility data = buffMapper.get(entity);
        switch (data.getType()) {
            case SPEED:
                this.setClosestEntityAlgorithm(ClosestSystemTowerWithoutSpeedBuff.class);
                break;
            case DAMAGE:
                this.setClosestEntityAlgorithm(ClosestSystemTowerWithoutDamageBuff.class);
                break;
            case RANGE:
                throw new UnsupportedOperationException("This is yet to be supported.");
        }
        super.run(ability, entity);
    }

    @Override
    protected void processTarget(BuffAbility ability, Entity entity, Entity target) {
        switch (ability.getType()) {
            case SPEED:
               speedBuff(ability, target);
                break;
            case DAMAGE:
                damageBuff(ability, target);
                break;
        }

    }

    private void speedBuff(BuffAbility ability, Entity target) {
        SpeedBuffEffect speedBuff = speedBuffMapper.getSafe(target);
        if (speedBuff == null) {
            target.addComponent(new SpeedBuffEffect().set(ability.getDuration(), ability.getRatio()));
            target.changedInWorld();
        }
    }

    private void damageBuff(BuffAbility ability, Entity target) {
        DamageBuffEffect damageBuff = damageBuffMapper.getSafe(target);
        if (damageBuff == null) {
            target.addComponent(new DamageBuffEffect().set(ability.getDuration(), ability.getRatio()));
            target.changedInWorld();
        }
    }
}
