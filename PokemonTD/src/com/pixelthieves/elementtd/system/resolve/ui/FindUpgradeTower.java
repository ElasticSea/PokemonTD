package com.pixelthieves.elementtd.system.resolve.ui;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.pixelthieves.elementtd.component.UpgradeComponent;

public class FindUpgradeTower extends FindAttackTower {

    @Mapper
    ComponentMapper<UpgradeComponent> upgradeMapper;

    public FindUpgradeTower() {
        super();
    }
    public ImmutableBag<Entity> getEntities() {
        Bag<Entity> bag = new Bag<Entity>(0);
        for (int i = 0; i < entities.size(); i++) {
            if(upgradeMapper.get(entities.get(i)).getGoldWorthThoseUpgrades() != 0){
                bag.add(entities.get(i));
            }
        }
        return bag;
    }
}

