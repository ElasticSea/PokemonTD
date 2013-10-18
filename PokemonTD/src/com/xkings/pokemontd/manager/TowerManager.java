package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.component.UpgradeComponent;
import com.xkings.pokemontd.entity.StaticObject;
import com.xkings.pokemontd.entity.tower.Tower;
import com.xkings.pokemontd.entity.tower.TowerName;
import com.xkings.pokemontd.entity.tower.TowerType;
import com.xkings.pokemontd.graphics.ui.Clickable;
import com.xkings.pokemontd.system.GetTower;

import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager implements Clickable {

    public static final Color TINT = new Color(1, 1, 1, 0.5f);
    private Entity placeholderTower;
    private Entity clickedTower;


    public void setPickedTower(TowerType towerType) {
        if (towerType != null) {
            if (status == Status.CONFIRMING_PLACING) {
                removeFakeTower();
            }
            if (clickedTower == null) {
                status = Status.PLACING_TOWER;
                selectedTower = towerType;
            } else {
                upgradingTower(towerType);
            }
        }
    }


    @Override
    public boolean hit(float x, float y) {
        clickedTower = world.getSystem(GetTower.class).getEntity(x, y);
        if (clickedTower == null) {
            switch (status) {
                case PLACING_TOWER:
                    placeTower((int) x, (int) y);
                    break;
                case CONFIRMING_PLACING:
                    buyAndPlaceTower((int) x, (int) y);
                    break;
            }
        } else {
            removeFakeTower();
            status = Status.NONE;
        }
        return clickedTower != null;
    }

    public enum Status {
        NONE, PLACING_TOWER, CONFIRMING_PLACING;
    }

    private Status status = Status.NONE;

    private final World world;
    private final Player player;
    private TowerType selectedTower = null;


    public TowerManager(World world, GenericBlueprint<Entity> blueprint, Player player) {
        this.world = world;
        this.player = player;
    }


    private boolean placeTower(int x, int y) {
        if (selectedTower != null && status != Status.CONFIRMING_PLACING) {
            if (canAfford(selectedTower)) {
                status = Status.CONFIRMING_PLACING;
                Vector2 towerPosition = getTowerPosition(x, y);
                this.placeholderTower =
                        StaticObject.registerFakeTower(this.world, selectedTower, towerPosition.x, towerPosition.y,
                                TINT);
                return true;
            }
        }
        return false;
    }

    private boolean buyAndPlaceTower(int x, int y) {
        if (placeholderTower != null) {
            Vector3 placeholderPosition = placeholderTower.getComponent(PositionComponent.class).getPoint();
            Vector2 correctedPosition = getTowerPosition(x, y);
            if (placeholderPosition.x == correctedPosition.x && placeholderPosition.y == correctedPosition.y) {
                buyTower(selectedTower);
                Vector2 towerPosition = getTowerPosition(x, y);
                Entity entity = Tower.registerTower(world, selectedTower, towerPosition.x, towerPosition.y);
                clickedTower = entity;
                this.setStatus(Status.NONE);
                selectedTower = null;
                return true;
            }
        }
        removeFakeTower();
        return false;
    }


    private void removeFakeTower() {
        if (placeholderTower != null) {
            this.placeholderTower.deleteFromWorld();
            placeholderTower = null;
        }
    }

    private boolean upgradingTower(TowerType upgrade) {
        if (canAfford(upgrade)) {
            buyTower(upgrade);
            Vector3 towerPosition = clickedTower.getComponent(PositionComponent.class).getPoint();
            Entity entity = Tower.registerTower(world, upgrade, towerPosition.x, towerPosition.y);
            entity.getComponent(UpgradeComponent.class).add(clickedTower.getComponent(UpgradeComponent
                    .class));
            entity.getComponent(UpgradeComponent.class).add(clickedTower.getComponent(TowerTypeComponent
                    .class).getTowerType());
            clickedTower.deleteFromWorld();
            clickedTower = entity;
            this.setStatus(Status.NONE);
            return true;
        }
        return false;
    }

    public boolean sellTower() {
        // DISCUS If I cast entity from blueprint into Entity Entity clickedTower = blueprint.getWalkable(x,
        // y); It won't find a appropriate component.
        if (clickedTower != null) {
            TreasureComponent towerTreasure = clickedTower.getComponent(TreasureComponent.class);
            if (towerTreasure != null) {
                int goldWorthThoseUpgrades =
                        clickedTower.getComponent(UpgradeComponent.class).getGoldWorthThoseUpgrades();
                player.getTreasure().addGold(towerTreasure.getTreasure().getGold() + goldWorthThoseUpgrades);
                clickedTower.deleteFromWorld();
                clickedTower = null;
            }
            return true;
        }
        return false;
    }

    private void buyTower(TowerType towerType) {
        player.getTreasure().subtract(towerType.getCost());
    }

    public List<TowerType> getCurrentTree() {
        // FIXME get hierarchy working
        TowerName towerName =
                clickedTower != null ? clickedTower.getComponent(TowerTypeComponent.class).getTowerType().getName() :
                        null;
        return TowerType.getHierarchy().get(towerName);
    }

    public boolean canAfford(TowerType towerType) {
        return player.getTreasure().includes(towerType.getCost());
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    private Vector2 getTowerPosition(float worldX, float worldY) {
        int blockX = (int) (worldX / App.WORLD_SCALE);
        int blockY = (int) (worldY / App.WORLD_SCALE);
        float towerX = (blockX + .5f) * App.WORLD_SCALE;
        float towerY = (blockY + .5f) * App.WORLD_SCALE;
        return new Vector2(towerX, towerY);
    }

}

