package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.xkings.pokemontd.graphics.ui.Clickable;
import com.xkings.pokemontd.system.GetCreep;
import com.xkings.pokemontd.system.GetTower;

/**
 * Created by Tomas on 10/7/13.
 */
public class CreepManager implements Clickable{

    private final World world;
    private Entity clickedCreep;

    public CreepManager(World world) {
        this.world = world;
    }

    @Override
    public boolean hit(float x, float y) {
        clickedCreep = world.getSystem(GetCreep.class).getEntity(x, y);
        return clickedCreep != null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    public Entity getClicked() {
        return clickedCreep;
    }
}

