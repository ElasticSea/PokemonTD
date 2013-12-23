package com.pixelthieves.elementtd.system.resolve;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.elementtd.component.WaveComponent;

public class FirstCreepSystem extends PickEntitySystem {

    /**
     * Finds first reachable creep in wave
     */
    public FirstCreepSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, WaveComponent.class), true);
    }

    @Override
    protected void process(Entity e) {
        // already found something, that is enough for me
        if (closestEntity != null) {
            return;
        }
        closestEntity = e;
    }
}
