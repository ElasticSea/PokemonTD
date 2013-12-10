package com.pixelthieves.pokemontd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;

public class ClosestSystemTower extends ClosestSystem {

    @Mapper
    ComponentMapper<TowerTypeComponent> towerTypeMapper;
    /**
     * Finds closest creep
     */
    public ClosestSystemTower() {
        super(TowerTypeComponent.class);
    }

}
