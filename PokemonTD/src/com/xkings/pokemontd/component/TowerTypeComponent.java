package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.entity.TowerType;

public class TowerTypeComponent extends Component {

    private TowerType towerType;

    public TowerTypeComponent(TowerType towerType) {
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }
}
