package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.UpdateFilter;
import com.xkings.core.logic.Updateable;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.entity.TextInfo;


/**
 * User: Seda
 * Date: 17.10.13
 * Time: 15:52
 */

public class Interest implements Updateable {
    private final float INTEREST_KOEFICIENT;
    private final Treasure playerTreasure;
    private final UpdateFilter filter;
    private final TowerManager towerManager;
    private final World world;

    public Interest(Clock clock, World world, Treasure playerTreasure, TowerManager towerManager, int interest,
                    float interval) {
        this.INTEREST_KOEFICIENT = interest / 100f;
        this.world = world;
        this.playerTreasure = playerTreasure;
        this.filter = (new UpdateFilter(this, interval));
        this.towerManager = towerManager;
        clock.addService(filter);
    }

    @Override
    public void update(float delta) {
        int earnGold = (int) (playerTreasure.getGold() * INTEREST_KOEFICIENT);
        playerTreasure.addGold(earnGold);
        Entity shop = towerManager.getShop();
        if (shop != null) {
            Vector3 position = shop.getComponent(PositionComponent.class).getPoint();
            TextInfo.registerMoneyInfo(world, earnGold, position.x, position.y);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {
    }

    public int getRemainingTime() {
        return (int) filter.getRemainingTime();
    }
}
