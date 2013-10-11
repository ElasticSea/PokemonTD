package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.entity.TowerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/5/13.
 */
public class UpgradeComponent extends Component {

    private final List<TowerType> pastUpgrades = new ArrayList<TowerType>();

    public void add(UpgradeComponent upgradeComponent) {
        for (TowerType towerType : upgradeComponent.pastUpgrades){
            add(towerType);
        }
    }

    public void add(TowerType towerType) {
        pastUpgrades.add(towerType);
    }

    public int getGoldWorthThoseUpgrades() {
        Treasure treasure = new Treasure(0);
        for (TowerType upgrade : pastUpgrades) {
            treasure.add(upgrade.getCost());
        }
        return treasure.getGold();
    }

}
