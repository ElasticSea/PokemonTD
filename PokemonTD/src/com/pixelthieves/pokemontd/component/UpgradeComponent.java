package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.pixelthieves.pokemontd.entity.tower.TowerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/5/13.
 */
public class UpgradeComponent extends Component {

    private final List<TowerType> pastUpgrades = new ArrayList<TowerType>();

    public void add(UpgradeComponent upgradeComponent) {
        for (TowerType towerType : upgradeComponent.pastUpgrades) {
            add(towerType);
        }
    }

    public void add(TowerType towerType) {
        pastUpgrades.add(towerType);
    }

    public int getGoldWorthThoseUpgrades() {
        int gold = 0;
        for (TowerType upgrade : pastUpgrades) {
            gold += upgrade.getCost().getGold() * (upgrade.getCost().hasElements() ? 0.75f : 1);
        }
        return gold;
    }

}
