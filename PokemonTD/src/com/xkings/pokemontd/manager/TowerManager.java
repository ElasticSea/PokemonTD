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

    public boolean createTower(int x, int y) {
        if (selectedTower != null && blueprint.isWalkable(x, y)) {
            if (canAfford(selectedTower)) {
                buyTower(selectedTower);
                Tower.registerTower(world, selectedTower, x + 0.5f, y + 0.5f);
                return true;
            }
        }
        return false;
    }

    private void buyTower(TowerType towerType) {
        player.getTreasure().subtract(towerType.getCost());
    }

    public List<TowerType> getCurrentTree() {
        return TowerType.getHierarchy().get(selectedTower);
    }

    public boolean canAfford(TowerType towerType) {
        return player.getTreasure().includes(towerType.getCost());
    }

    public void setCurrentTower(TowerType currentTower) {
        if (canAfford(currentTower)) {
            selectedTower = currentTower;
        }
    }
}
