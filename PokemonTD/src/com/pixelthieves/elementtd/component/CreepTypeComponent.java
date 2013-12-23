package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.entity.creep.CreepType;

public class CreepTypeComponent extends Component {

    private final CreepType creepType;

    public CreepTypeComponent(CreepType creepType) {
        this.creepType = creepType;
    }

    public CreepType getCreepType() {
        return creepType;
    }
}
