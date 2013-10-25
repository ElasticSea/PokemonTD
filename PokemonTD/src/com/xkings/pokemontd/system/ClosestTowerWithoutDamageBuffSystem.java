package com.xkings.pokemontd.system;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

public class ClosestTowerWithoutDamageBuffSystem extends ClosestTowerSystem {

    @Mapper
    ComponentMapper<DamageBuffEffect> damageBufferMapper;

    @Override
    protected boolean isRequirementMet(Entity e) {
        System.out.println("Yo this entity ["+damageBufferMapper.has(e)+"] damage buff");
        return !damageBufferMapper.has(e);
    }
}
