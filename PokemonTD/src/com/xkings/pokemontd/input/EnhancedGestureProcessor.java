package com.xkings.pokemontd.input;

import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.GestureProcessor;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.manager.TowerManager;

/**
 * Created by Tomas on 10/7/13.
 */
public class EnhancedGestureProcessor extends GestureProcessor {
    private final TowerManager towerManager;

    public EnhancedGestureProcessor(TowerManager towerManager, CameraHandler camera) {
        super(camera);
        this.towerManager = towerManager;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector2 scaledCoordinates = camera.screenToWorld(x, y);
        return towerManager.createTower((int) scaledCoordinates.x, (int) scaledCoordinates.y);
    }
}
