package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.entity.creep.CreepAbilityType;

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
