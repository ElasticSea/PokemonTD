package com.xkings.pokemontd.manager;

import com.artemis.World;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.entity.Player;
import com.xkings.pokemontd.entity.Tower;
import com.xkings.pokemontd.entity.TowerType;

import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager {

    private final Blueprint blueprint;
    private final World world;
    private final Player player;
    private TowerType selectedTower = null;

    public TowerManager(World world, Blueprint blueprint, Player player) {
        this.world = world;
        this.blueprint = blueprint;
        this.player = player;
    }

    public boolean createTower(TowerType towerType, int x, int y) {
        if (blueprint.isWalkable(x, y)) {
            Tower.registerTower(world, towerType, x + 0.5f, y + 0.5f);
            return true;
        }
        return false;
    }

    public List<TowerType> getCurrentTree() {
        return TowerType.getHierarchy().get(selectedTower);
    }

    public boolean canAffort(TowerType towerType) {
        return player.getTreasure().includes(towerType.getCost());
    }
}
