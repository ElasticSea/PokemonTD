package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.component.UpgradeComponent;
import com.xkings.pokemontd.entity.StaticObject;
import com.xkings.pokemontd.entity.Tower;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.entity.creep.CreepType;

import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager {

    public static final Color TINT = new Color(1, 1, 1, 0.5f);
    private Entity placeholderTower;

    public void toggleSellingTowers() {
        if (status == Status.SELLING_TOWER) {
            status = Status.NONE;
        } else {
            status = Status.SELLING_TOWER;
        }
    }

    public void setPickedTower(TowerType towerType) {
        if (towerType != null) {
            if (selectedTowerEntity.getTower() == null) {
                status = Status.PLACING_TOWER;
            } else {
                upgradingTower(selectedTowerEntity, towerType);
            }
            this.selectedTower = towerType;
        }
    }


    public boolean process(Entity entity, int x , int y) {
        switch (status) {
            case NONE:
                CreepType.save();
                System.out.println("Find: "+entity);
                // getTowerInfoSystem.getInfo(selectedTower)
                selectedTowerEntity.setTower(entity);
                selectedTowerEntity.setX(x);
                selectedTowerEntity.setY(y);
                selectedTower = null;
                // return none(x, y);
                break;
            case PLACING_TOWER:
                placeTower(x, y);
                break;
            case CONFIRMING_PLACING:
                buyAndPlaceTower(x, y);
                break;
            case SELLING_TOWER:
                sellTower(x, y);
                break;
        }

        return false;
    }

    public enum Status {
        NONE, PLACING_TOWER, CONFIRMING_PLACING, SELLING_TOWER, UPGRADING_TOWER;
    }

    private Status status = Status.NONE;

    private final GenericBlueprint<Entity> blueprint;
    private final World world;
    private final Player player;
    private TowerType selectedTower = null;
    private SelectedTower selectedTowerEntity = new SelectedTower(0, 0, null);


    public TowerManager(World world, GenericBlueprint<Entity> blueprint, Player player) {
        this.world = world;
        this.blueprint = blueprint;
        this.player = player;
    }


    private boolean placeTower(int x, int y) {
        if (selectedTower != null && blueprint.isWalkable(x, y)) {
            if (canAfford(selectedTower)) {
                status = Status.CONFIRMING_PLACING;
                selectedTowerEntity.setX(x);
                selectedTowerEntity.setY(y);
                this.placeholderTower =
                        StaticObject.registerFakeTower(this.world, selectedTower, x + 0.5f, y + 0.5f, TINT);
                return true;
            }
        }
        return false;
    }

    private boolean buyAndPlaceTower(int x, int y) {
        if (selectedTower != null && blueprint.isWalkable(x, y)) {
            if (selectedTowerEntity.getX() == x && selectedTowerEntity.getY() == y) {
                buyTower(selectedTower);
                Entity entity = Tower.registerTower(world, selectedTower, x + 0.5f, y + 0.5f);
                blueprint.setWalkable(entity, x, y);
                selectedTowerEntity.setTower(entity);
                selectedTowerEntity.setX(x);
                selectedTowerEntity.setY(y);
                this.setStatus(Status.NONE);
                selectedTower = null;
                this.placeholderTower.deleteFromWorld();
                return true;
            }
        }
        reset();
        return false;
    }

    private void reset() {
        this.placeholderTower.deleteFromWorld();
        status = Status.NONE;
        selectedTowerEntity.setTower(null);
        selectedTowerEntity.setX(0);
        selectedTowerEntity.setY(0);
        selectedTower = null;
    }

    private boolean upgradingTower(SelectedTower currentEntity, TowerType updgrade) {
        if (canAfford(updgrade)) {
            buyTower(updgrade);
            Entity entity =
                    Tower.registerTower(world, updgrade, currentEntity.getX() + 0.5f, currentEntity.getY() + 0.5f);
            entity.getComponent(UpgradeComponent.class).add(currentEntity.getTower().getComponent(UpgradeComponent
                    .class));
            entity.getComponent(UpgradeComponent.class).add(currentEntity.getTower().getComponent(TowerTypeComponent
                    .class).getTowerType());
            replaceTower(entity, currentEntity.getX(), currentEntity.getY());
            selectedTowerEntity.setTower(entity);
            this.setStatus(Status.NONE);
            return true;
        }
        return false;
    }

    private void replaceTower(Entity entity, int x, int y) {
        blueprint.getWalkable(x, y).deleteFromWorld();
        blueprint.setWalkable(entity, x, y);
    }

    private Entity getTower(int x, int y) {
        if (blueprint.getWalkable(x, y) != null) {
            TowerTypeComponent towerTypeComponent = blueprint.getWalkable(x, y).getComponent(TowerTypeComponent.class);
            if (towerTypeComponent != null) {
                return blueprint.getWalkable(x, y);
            }
        }
        return null;
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
                int goldWorthThoseUpgrades =
                        blueprint.getWalkable(x, y).getComponent(UpgradeComponent.class).getGoldWorthThoseUpgrades();
                player.getTreasure().addGold(towerTreasure.getTreasure().getGold() + goldWorthThoseUpgrades);
                blueprint.getWalkable(x, y).deleteFromWorld();
                blueprint.setWalkable(null, x, y);
                selectedTowerEntity.setTower(null);
            }
            return true;
        }
        return false;
    }

    private void buyTower(TowerType towerType) {
        player.getTreasure().subtract(towerType.getCost());
    }

    public List<TowerType> getCurrentTree() {
        Entity tower = selectedTowerEntity.getTower();
        // FIXME get hierarchy working
        return TowerType.getHierarchy().get(
                tower != null ? tower.getComponent(TowerTypeComponent.class).getTowerType() : null);
        // return new ArrayList<TowerType>();
    }

    public boolean canAfford(TowerType towerType) {
        return player.getTreasure().includes(towerType.getCost());
    }

    private void setStatus(Status status) {
        this.status = status;
    }

}

