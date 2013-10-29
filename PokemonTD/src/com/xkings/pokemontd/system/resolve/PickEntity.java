package com.xkings.pokemontd.system.resolve;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Tomas on 10/29/13.
 */
public interface PickEntity {
    void start(Entity entity, Vector3 position, float range);

    Entity getPickedEntity();
}
