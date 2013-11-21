package com.pixelthieves.pokemontd.manager;

import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.pixelthieves.core.logic.Clock;
import com.pixelthieves.core.logic.UpdateFilter;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.system.resolve.FireTextInfo;


/**
 * User: Seda
 * Date: 17.10.13
 * Time: 15:52
 */

public class InterestManager implements Updateable {
    private final float INTEREST_KOEFICIENT;
    private final Treasure playerTreasure;
    private final UpdateFilter filter;
    private final TowerManager towerManager;
    private final World world;

    public InterestManager(World world, Treasure playerTreasure, TowerManager towerManager, int interest,
                           float interval) {
        this.INTEREST_KOEFICIENT = interest / 100f;
        this.world = world;
        this.playerTreasure = playerTreasure;
        this.filter = (new UpdateFilter(this, interval));
        this.towerManager = towerManager;
    }

    @Override
    public void update(float delta) {
        int earnGold = (int) (playerTreasure.getGold() * INTEREST_KOEFICIENT);
        playerTreasure.addGold(earnGold);
        world.getSystem(FireTextInfo.class).fireText("+" + earnGold, Color.YELLOW);
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

    public UpdateFilter getFilter() {
        return filter;
    }
}
