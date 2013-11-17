package com.pixelthieves.pokemontd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.attack.effects.buff.SpeedBuffEffect;

public class ClosestSystemTowerWithoutSpeedBuff extends ClosestSystemTower {

    @Mapper
    ComponentMapper<SpeedBuffEffect> speedBuffEffect;

    @Override
    protected boolean isRequirementMet(Entity e) {
    //    System.out.println("Has speed buff [" + e + "]: " + !speedBuffEffect.has(e));
        return !speedBuffEffect.has(e);
    }
}
