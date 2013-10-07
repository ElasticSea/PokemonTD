package com.xkings.pokemontd.manager;

import com.artemis.World;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.entity.Tower;
import com.xkings.pokemontd.entity.TowerType;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager {

    private final Blueprint blueprint;
    private final World world;

    public TowerManager(World world, Blueprint blueprint) {
        this.world = world;
        this.blueprint = blueprint;
    }

    public boolean createTower(TowerType towerType, int x, int y) {
        System.out.println(x+" "+y);
        if (blueprint.isWalkable(x, y )) {
            Tower.registerTower(world, towerType, x+0.5f, y+0.5f);
            return true;
        }
        return false;
    }
}
