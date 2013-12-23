package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.entity.creep.CreepAbilityType;

/**
 * Created by Tomas on 9/8/13.
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
