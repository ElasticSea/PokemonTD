package com.pixelthieves.pokemontd.input;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.core.graphics.camera.CameraHandler;
import com.pixelthieves.pokemontd.graphics.ui.Clickable;
import com.pixelthieves.pokemontd.manager.CreepManager;
import com.pixelthieves.pokemontd.manager.TowerManager;

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
        clickables = Arrays.asList(towerManager, creepManager);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        boolean condition = false;
        Vector2 world = camera.screenToWorld(x, y);
        for (Clickable clickable : clickables) {
            if (clickable.hit(world.x, world.y)) {
                condition = true;
            }
        }
        return condition;
    }

}
