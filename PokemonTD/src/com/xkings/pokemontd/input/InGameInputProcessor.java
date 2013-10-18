package com.xkings.pokemontd.input;

import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.GestureProcessor;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.system.GetCreep;
import com.xkings.pokemontd.system.GetTower;

/**
 * Created by Tomas on 10/7/13.
 */
public class InGameInputProcessor extends GestureProcessor {
    private final TowerManager towerManager;
    private final GetCreep getCreepSystem;
    private final CreepManager creepManager;

    public InGameInputProcessor(GetCreep getCreepSystem, TowerManager towerManager,
                                CreepManager creepManager, CameraHandler camera) {
        super(camera);
        this.getCreepSystem = getCreepSystem;
        this.towerManager = towerManager;
        this.creepManager = creepManager;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector2 world = camera.screenToWorld(x, y);
        System.out.println(world);


        Entity entity = null;
        if (!towerManager.hit(world.x, world.y)) {
            System.out.println("Search creeps");
            entity = getCreepSystem.getEntity(world.x, world.y);
            creepManager.process(entity);
        }
        return entity != null;
    }

}
