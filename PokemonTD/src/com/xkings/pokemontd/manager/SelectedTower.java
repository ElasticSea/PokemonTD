package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.xkings.core.component.RangeComponent;

public final class SelectedTower {
    private int x;
    private int y;
    private Entity tower;

    SelectedTower(int x, int y, Entity tower) {
        this.x = x;
        this.y = y;
        this.tower = tower;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Entity getTower() {
        return tower;
    }

    public void setTower(Entity tower) {
        if (this.tower != null) {
            this.tower.getComponent(RangeComponent.class).setVisible(false);
        }
        this.tower = tower;
        if (tower != null) {
            tower.getComponent(RangeComponent.class).setVisible(true);
        }
    }


}
