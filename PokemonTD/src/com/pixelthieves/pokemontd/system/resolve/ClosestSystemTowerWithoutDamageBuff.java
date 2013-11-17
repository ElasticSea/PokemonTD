package com.pixelthieves.pokemontd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.attack.effects.buff.DamageBuffEffect;

public class ClosestSystemTowerWithoutDamageBuff extends ClosestSystemTower {

    @Mapper
    ComponentMapper<DamageBuffEffect> damageBufferMapper;

    @Override
    protected boolean isRequirementMet(Entity e) {
     //   System.out.println("Has damage buff [" + e + "]: " + !damageBufferMapper.has(e));
        return !damageBufferMapper.has(e);
    }
}
