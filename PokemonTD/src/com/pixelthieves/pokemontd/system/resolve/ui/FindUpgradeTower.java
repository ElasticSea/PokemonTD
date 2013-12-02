package com.pixelthieves.pokemontd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.pixelthieves.pokemontd.component.ShopComponent;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;
import com.pixelthieves.pokemontd.component.UpgradeComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.SpeedBuffEffect;

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

