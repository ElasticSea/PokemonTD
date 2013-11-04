package com.xkings.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.VisibleComponent;
import com.xkings.pokemontd.component.WaveComponent;

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
