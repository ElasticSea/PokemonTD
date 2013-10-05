package com.xkings.pokemontd.entity;

import com.xkings.core.main.AbstractAssets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum CreepType {

    DefaultCreep("default", 1, 1, 100);
    private final AtlasRegion texture;
    private final float speed;
    private final int size;
    private final int health;

    private CreepType(String texture, float speed, int size, int health) {
        this.texture = AbstractAssets.getTexture(texture);
        this.speed = speed;
        this.size = size;
        this.health = health;
    }

    public AtlasRegion getTexture() {
        return texture;
    }

    public float getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }
}
