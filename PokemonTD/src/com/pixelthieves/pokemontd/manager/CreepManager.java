package com.pixelthieves.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.ui.Clickable;
import com.pixelthieves.pokemontd.system.resolve.ui.GetCreep;

/**
 * Created by Seda on 10/7/13.
 */
public class CreepManager implements Clickable {

    private final App app;
    private Entity clickedCreep;

    public CreepManager(App app) {
        this.app = app;
    }

    @Override
    public boolean hit(float x, float y) {
        clickedCreep = app.getWorld().getSystem(GetCreep.class).getEntity(x, y);
        System.out.println(clickedCreep);
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

