package com.xkings.pokemontd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

public class ClosestTowerWithoutDamageBuffSystem extends ClosestTowerSystem {

    @Mapper
    ComponentMapper<DamageBuffEffect> damageBufferMapper;

    @Override
    protected boolean isRequirementMet(Entity e) {
        return !damageBufferMapper.has(e);
    }
}
