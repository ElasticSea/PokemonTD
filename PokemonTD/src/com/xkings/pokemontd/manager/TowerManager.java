package com.xkings.pokemontd.manager;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.Bag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.component.UpgradeComponent;
import com.xkings.pokemontd.component.attack.effects.AbstractEffect;
import com.xkings.pokemontd.entity.StaticObject;
import com.xkings.pokemontd.entity.tower.Tower;
import com.xkings.pokemontd.entity.tower.TowerName;
import com.xkings.pokemontd.entity.tower.TowerType;
import com.xkings.pokemontd.graphics.ui.Clickable;
import com.xkings.pokemontd.system.resolve.FindShop;
import com.xkings.pokemontd.system.resolve.ui.GetTower;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager implements Clickable {

    public static final Color TINT = new Color(1, 1, 1, 0.5f);
    private final Blueprint blueprint;
    private Entity placeholderTower;
    private Entity clickedTower;


    public void setPickedTower(TowerType towerType) {
        removePlaceholderTower();
        if (towerType != null) {
            if (clickedTower == null) {
                status = Status.PLACING_TOWER;
            }
            selectedTower = towerType;
        }
    }


    @Override
    public boolean hit(float x, float y) {
        clickedTower = world.getSystem(GetTower.class).getEntity(x, y);
        if (clickedTower == null) {
            switch (status) {
                case NONE:
                    rollBack();
                case PLACING_TOWER:
                    placeTower((int) x, (int) y);
                    break;
                case MOVE_PLACEHOLDER:
                    movePlaceholder((int) x, (int) y);
                    break;
            }
        } else {
            rollBack();
            status = Status.NONE;
        }
        return clickedTower != null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    private void rollBack() {
        removePlaceholderTower();
        selectedTower = null;
    }

    public Entity getClicked() {
        return clickedTower;
    }

    public TowerType getSelectedTowerType() {
        return selectedTower;
    }

    public void buyNewOrUpgrade() {
        if (canAfford(selectedTower)) {
            if (clickedTower == null && placeholderTower != null) {
                buyNewTower();
            } else if (clickedTower != null) {
                upgradeTower();
            }
            this.setStatus(Status.NONE);
            selectedTower = null;
        }
    }

    private void upgradeTower() {
        purchaseTower(selectedTower);
        Vector3 towerPosition = clickedTower.getComponent(PositionComponent.class).getPoint();
        Entity entity = Tower.registerTower(world, selectedTower, towerPosition.x, towerPosition.y);
        entity.getComponent(UpgradeComponent.class).add(clickedTower.getComponent(UpgradeComponent
                .class));
        entity.getComponent(UpgradeComponent.class).add(clickedTower.getComponent(TowerTypeComponent
                .class).getTowerType());
        transferEffects(clickedTower, entity);
        clickedTower.deleteFromWorld();
        clickedTower = entity;
    }

    private void transferEffects(Entity from, Entity to) {
        Bag<Component> fillBag = new Bag<Component>();
        from.getComponents(fillBag);
        for (int i = 0; i < fillBag.size(); i++) {
            Component component = fillBag.get(i);
            if (component instanceof AbstractEffect) {
                to.addComponent(component);
                System.out.println("added component: "+component);
            }
        }
        to.changedInWorld();
    }

    private void buyNewTower() {
        Vector3 placeholderPosition = placeholderTower.getComponent(PositionComponent.class).getPoint();
        purchaseTower(selectedTower);
        clickedTower = Tower.registerTower(world, selectedTower, placeholderPosition.x, placeholderPosition.y);
        removePlaceholderTower();
    }

    public Entity getShop() {
        FindShop findShop = world.getSystem(FindShop.class);
        findShop.process();
        return findShop.getShop();
    }

    public Entity getPlaceholderTower() {
        return placeholderTower;
    }

    public enum Status {
        NONE, PLACING_TOWER, MOVE_PLACEHOLDER;
    }

    private Status status = Status.NONE;

    private final World world;
    private final Player player;
    private TowerType selectedTower = null;


    public TowerManager(World world, Blueprint blueprint, Player player) {
        this.world = world;
        this.blueprint = blueprint;
        this.player = player;
    }


    private boolean placeTower(int x, int y) {
        if (selectedTower != null && status != Status.MOVE_PLACEHOLDER) {
            Vector3 block = getBlockPosition(x, y);
            if (canAfford(selectedTower) && blueprint.isWalkable((int) block.x, (int) block.y)) {
                status = Status.MOVE_PLACEHOLDER;
                Vector3 towerPosition = getTowerPosition(x, y);
                this.placeholderTower =
                        StaticObject.registerFakeTower(this.world, selectedTower, towerPosition.x, towerPosition.y,
                                TINT);
                return true;
            }
        }
        return false;
    }

    private void movePlaceholder(int x, int y) {
        Vector3 block = getBlockPosition(x, y);
        if (blueprint.isWalkable((int) block.x, (int) block.y)) {
            Vector3 desiredPosition = getTowerPosition(x, y);
            placeholderTower.getComponent(PositionComponent.class).getPoint().set(desiredPosition);
        }
    }

    private void removePlaceholderTower() {
        if (placeholderTower != null) {
            this.placeholderTower.deleteFromWorld();
            placeholderTower = null;
        }
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

    private void purchaseTower(TowerType towerType) {
        player.getTreasure().purchase(towerType.getCost());
    }

    public TowerName getCurrentTowerName() {
        // FIXME get hierarchy working
        TowerName towerName =
                clickedTower != null ? clickedTower.getComponent(TowerTypeComponent.class).getTowerType().getName() :
                        null;
        return towerName;
    }

    public boolean canAfford(TowerType towerType) {
        return player.getTreasure().includes(towerType.getCost());
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    private Vector3 getTowerPosition(float worldX, float worldY) {
        int blockX = (int) (worldX / App.WORLD_SCALE);
        int blockY = (int) (worldY / App.WORLD_SCALE);
        float towerX = (blockX + .5f) * App.WORLD_SCALE;
        float towerY = (blockY + .5f) * App.WORLD_SCALE;
        return new Vector3(towerX, towerY, 0);
    }

    private Vector3 getBlockPosition(float worldX, float worldY) {
        int blockX = (int) (worldX / App.WORLD_SCALE);
        int blockY = (int) (worldY / App.WORLD_SCALE);
        return new Vector3(blockX, blockY, 0);
    }

}

