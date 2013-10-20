package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.entity.creep.CreepType;

public class CreepTypeComponent extends Component {

    private final CreepType creepType;

    public CreepTypeComponent(CreepType creepType) {
        this.creepType = creepType;
    }

    public CreepType getCreepType() {
        return creepType;
    }
}
