package com.pixelthieves.elementtd.system.resolve;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.pixelthieves.elementtd.component.TowerTypeComponent;

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
