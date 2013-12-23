package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.entity.tower.TowerType;

public class TowerTypeComponent extends Component {

    private TowerType towerType;

    public TowerTypeComponent(TowerType towerType) {
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }
}
