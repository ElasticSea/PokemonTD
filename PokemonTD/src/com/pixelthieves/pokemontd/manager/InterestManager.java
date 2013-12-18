package com.pixelthieves.pokemontd.manager;

import com.badlogic.gdx.graphics.Color;
import com.pixelthieves.core.logic.UpdateFilter;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.pokemontd.App;
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
    private final App app;

    public InterestManager(App app, Treasure playerTreasure, int interest, float interval) {
        this.app = app;
        this.INTEREST_KOEFICIENT = interest / 100f;
        this.playerTreasure = playerTreasure;
        this.filter = (new UpdateFilter(this, interval));
    }

    @Override
    public void update(float delta) {
        int earnGold = (int) (playerTreasure.getGold() * INTEREST_KOEFICIENT);
        playerTreasure.addGold(earnGold);
        app.getWorld().getSystem(FireTextInfo.class).fireText("+" + earnGold, Color.YELLOW);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {
    }

    public int getRemainingTime() {
        return (int) filter.getRemainingTime() + 1;
    }

    public UpdateFilter getFilter() {
        return filter;
    }
}
