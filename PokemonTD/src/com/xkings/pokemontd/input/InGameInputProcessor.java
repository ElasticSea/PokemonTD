package com.xkings.pokemontd.input;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.GestureProcessor;
import com.xkings.pokemontd.graphics.ui.Clickable;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.TowerManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class InGameInputProcessor extends GestureDetector.GestureAdapter {
    private final List<Clickable> clickables;
    private final CameraHandler camera;

    public InGameInputProcessor(TowerManager towerManager, CreepManager creepManager, CameraHandler camera) {
        this.camera = camera;
        clickables = Arrays.asList(towerManager,creepManager);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println(InGameInputProcessor.class);
        Vector2 world = camera.screenToWorld(x, y);
        for (Clickable clickable : clickables){
            if(clickable.hit(world.x, world.y)){
                return true;
            }
        }
        return false;
    }

}
