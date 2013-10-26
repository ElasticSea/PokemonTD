package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.ShopComponent;
import com.xkings.pokemontd.component.TowerTypeComponent;

public class GetTower extends GetEntity {
    /**
     * Finds desired tower on given coordinates
     */
    public GetTower() {
        super(TowerTypeComponent.class);
    }

}


