package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.entity.Player;
import com.xkings.pokemontd.entity.Tower;
import com.xkings.pokemontd.entity.TowerType;

import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager {

    public enum Status {
        NONE, PLACING_TOWER, SELLING_TOWER;
    }

    private Status status = Status.NONE;

    private final GenericBlueprint<Entity> blueprint;
    private final World world;
    private final Player player;
    private TowerType selectedTower = null;

    public TowerManager(World world, GenericBlueprint<Entity> blueprint, Player player) {
        this.world = world;
        this.blueprint = blueprint;
        this.player = player;
    }

    public boolean process(int x, int y) {
        switch (status) {
            case NONE:
                return none(x, y);
            case PLACING_TOWER:
                return placeTower(x, y);
            case SELLING_TOWER:
                return sellTower(x, y);
        }

        return false;
    }

    private boolean none(int x, int y) {
        if (blueprint.getWalkable(x, y) != null) {
            TowerTypeComponent towerTypeComponent = blueprint.getWalkable(x, y).getComponent(TowerTypeComponent.class);
            if (towerTypeComponent != null) {
                selectedTower = towerTypeComponent.getTowerType();
                return true;
            }
        }
        return false;
    }

    private boolean sellTower(int x, int y) {
        // DISCUS If I cast entity from blueprint into Entity Entity tower = blueprint.getWalkable(x,
        // y); It won't find a appropriate component.
        if (blueprint.getWalkable(x, y) != null) {
            TreasureComponent towerTreasure = blueprint.getWalkable(x, y).getComponent(TreasureComponent.class);
            if (towerTreasure != null) {
                player.getTreasure().addGold(towerTreasure.getTreasure().getGold());
                blueprint.getWalkable(x, y).deleteFromWorld();
                blueprint.setWalkable(null, x, y);
            }
            return true;
        }
        return false;
    }

    private boolean placeTower(int x, int y) {
        if (selectedTower != null && blueprint.isWalkable(x, y)) {
            if (canAfford(selectedTower)) {
                buyTower(selectedTower);
                blueprint.setWalkable(Tower.registerTower(world, selectedTower, x + 0.5f, y + 0.5f), x, y);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
