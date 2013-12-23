package com.pixelthieves.elementtd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.elementtd.component.attack.effects.buff.SpeedBuffEffect;
import com.pixelthieves.elementtd.entity.tower.TowerName;

public class ClosestSystemTowerWithoutSpeedBuff extends ClosestSystemTower {

    @Mapper
    ComponentMapper<SpeedBuffEffect> speedBuffEffect;

    @Override
    protected boolean isRequirementMet(Entity e) {
        TowerName name = towerTypeMapper.get(e).getTowerType().getName();
        if(name.equals(TowerName.Noble) || name.equals(TowerName.Majestic) || name.equals(TowerName.Magnificent)){
            return false;
        }
        return !speedBuffEffect.has(e);
    }
}
