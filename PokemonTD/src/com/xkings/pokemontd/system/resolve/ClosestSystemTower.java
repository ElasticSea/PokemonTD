package com.xkings.pokemontd.system.resolve;

import com.xkings.pokemontd.component.TowerTypeComponent;

public class ClosestSystemTower extends ClosestSystem {

    /**
     * Finds closest creep
     */
    public ClosestSystemTower() {
        super(TowerTypeComponent.class);
    }

}
