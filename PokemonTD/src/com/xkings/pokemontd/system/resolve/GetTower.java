package com.xkings.pokemontd.system.resolve;

import com.xkings.pokemontd.component.TowerTypeComponent;

public class GetTower extends GetEntity {
    /**
     * Finds desired tower on given coordinates
     */
    public GetTower() {
        super(TowerTypeComponent.class);
    }

}


