package com.xkings.pokemontd.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Tomas on 10/9/13.
 */
public class CurrentTowerInfo {
    private TextureAtlas.AtlasRegion atlasRegion;
    private int attack;
    private int range;
    private int speed;
    private String name;

    public TextureAtlas.AtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    public void setAtlasRegion(TextureAtlas.AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }

    public void clear() {
        atlasRegion = null;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
