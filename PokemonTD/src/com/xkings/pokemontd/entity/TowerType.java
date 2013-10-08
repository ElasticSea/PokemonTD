package com.xkings.pokemontd.entity;

import com.xkings.core.main.Assets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum TowerType {

    DefaultTower("default", 1, 1, ProjectileType.DefaultProjectile, 3);
    private final AtlasRegion texture;
    private final float speed;
    private final int size;
    private final ProjectileType projectile;
    private final float range;

    private TowerType(String texture, float speed, int size, ProjectileType projectile, float range) {
        this.texture = Assets.getTexture(texture);
        this.speed = speed;
        this.size = size;
        this.projectile = projectile;
        this.range = range;
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

    public ProjectileType getProjectile() {
        return projectile;
    }

    public float getRange() {
        return range;
    }
}
