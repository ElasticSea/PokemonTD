package com.xkings.pokemontd.entity;

import com.xkings.core.main.AbstractAssets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum TowerType {

    DefaultTower("default", 1, 1);
    private final AtlasRegion texture;
    private final float speed;
    private final int size;

    private TowerType(String texture, float speed, int size) {
        this.texture = AbstractAssets.getTexture(texture);
        this.speed = speed;
        this.size = size;
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
}
