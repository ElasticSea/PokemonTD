package com.pixelthieves.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.DamageBuffEffect;
import com.pixelthieves.pokemontd.component.attack.effects.buff.SpeedBuffEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.BuffData;
import com.pixelthieves.pokemontd.system.resolve.ClosestSystemTower;
import com.pixelthieves.pokemontd.system.resolve.ClosestSystemTowerWithoutDamageBuff;
import com.pixelthieves.pokemontd.system.resolve.ClosestSystemTowerWithoutSpeedBuff;

/**
 * Created by Tomas on 10/4/13.
 */
public class ApplyBuffSystem extends ApplyAbilitySystem<BuffData> {

    @Mapper
    ComponentMapper<BuffData> buffMapper;
    @Mapper
    ComponentMapper<SpeedBuffEffect> speedBuffMapper;
    @Mapper
    ComponentMapper<DamageBuffEffect> damageBuffMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;

    public ApplyBuffSystem() {
        super(BuffData.class, ClosestSystemTower.class);
    }

    @Override
    protected void run(BuffData ability, Entity entity) {
        BuffData data = buffMapper.get(entity);
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
    protected void processTarget(BuffData ability, Entity entity, Entity target) {
        switch (ability.getType()) {
            case SPEED:
                speedBuff(ability, entity, target);
                break;
            case DAMAGE:
                damageBuff(ability, entity, target);
                break;
        }

    }

    private void speedBuff(BuffData ability, Entity entity, Entity target) {
        SpeedBuffEffect speedBuff = speedBuffMapper.getSafe(target);
        if (speedBuff == null) {
            target.addComponent(new SpeedBuffEffect().set(ability.getDuration(), damageMapper.get(entity).getDamage()));
            target.changedInWorld();
        }
    }

    private void damageBuff(BuffData ability, Entity entity, Entity target) {
        DamageBuffEffect damageBuff = damageBuffMapper.getSafe(target);
        if (damageBuff == null) {
            target.addComponent(
                    new DamageBuffEffect().set(ability.getDuration(), damageMapper.get(entity).getDamage()));
            target.changedInWorld();
        }
    }
}
