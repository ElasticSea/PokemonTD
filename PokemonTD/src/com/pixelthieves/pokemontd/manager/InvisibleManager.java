package com.pixelthieves.pokemontd.manager;

import com.artemis.World;
import com.pixelthieves.core.logic.Clock;
import com.pixelthieves.core.logic.UpdateFilter;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.pokemontd.system.autonomous.InvisibleSystem;


/**
 * User: Seda
 * Date: 17.10.13
 * Time: 15:52
 */

public class InvisibleManager implements Updateable {
    private final World world;
    private boolean visibility;

    public InvisibleManager(World world, Clock clock, float interval) {
        this.world = world;
        clock.addService(new UpdateFilter(this, interval));
    }

    @Override
    public void update(float delta) {
        world.getSystem(InvisibleSystem.class).start(visibility);
        switchVisibility();
    }

    private void switchVisibility() {
        this.visibility = !visibility;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {
    }
}
