package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.pixelthieves.pokemontd.entity.tower.TowerType;

public class TowerTypeComponent extends Component {

    private TowerType towerType;

    public TowerTypeComponent(TowerType towerType) {
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }
}
