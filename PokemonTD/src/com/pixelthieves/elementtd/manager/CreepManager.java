package com.pixelthieves.elementtd.manager;

import com.artemis.Entity;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.ui.Clickable;
import com.pixelthieves.elementtd.system.resolve.ui.GetCreep;

/**
 * Created by Tomas on 10/7/13.
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

