package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.pixelthieves.pokemontd.entity.creep.CreepAbilityType;

/**
 * Created by Seda on 9/8/13.
 */
public class CreepAbilityComponent extends Component {

    private final CreepAbilityType creepAbilityType;

    public CreepAbilityComponent(CreepAbilityType creepAbilityType) {
        this.creepAbilityType = creepAbilityType;
    }

    public CreepAbilityType getCreepAbilityType() {
        return creepAbilityType;
    }
}
