package com.pixelthieves.pokemontd.system.resolve.ui;

import com.pixelthieves.pokemontd.component.TowerTypeComponent;

public class GetTower extends GetEntity {
    /**
     * Finds desired tower on given coordinates
     */
    public GetTower() {
        super(TowerTypeComponent.class);
    }

}


