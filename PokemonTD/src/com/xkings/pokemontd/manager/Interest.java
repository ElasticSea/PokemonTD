package com.xkings.pokemontd.manager;

import com.xkings.core.logic.Clock;
import com.xkings.core.logic.UpdateFilter;
import com.xkings.core.logic.Updateable;
import com.xkings.pokemontd.Treasure;


/**
 * User: Seda
 * Date: 17.10.13
 * Time: 15:52
 */

public class Interest implements Updateable {
    private final float INTEREST_KOEFICIENT;
    private final Treasure playerTreasure;
    private final UpdateFilter filter;

    public Interest(Clock clock, Treasure playerTreasure, int interest, float interval) {
        this.INTEREST_KOEFICIENT = interest / 100;
        this.playerTreasure = playerTreasure;
        this.filter =(new UpdateFilter(this, interval));
        clock.addService(filter);
    }

    @Override
    public void update(float delta) {
        playerTreasure.addGold((int) (playerTreasure.getGold() * INTEREST_KOEFICIENT));
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {
    }

    public int getRemainingTime(){
        return (int) filter.getRemainingTime();
    }
}
