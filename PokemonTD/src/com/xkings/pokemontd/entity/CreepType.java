package com.xkings.pokemontd.entity;

import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum CreepType {

    DefaultCreep("default", 1f, 1, 1, new Treasure(5), 5),
    DefaultCreep2("default", 1f, 1, 2, new Treasure(5), 15);
    private final AtlasRegion texture;
    private final float speed;
    private final int size;
    private final int health;
    private final Treasure treasure;
    private final int creepsInWave;

    private CreepType(String texture, float speed, int size, int health, Treasure treasure,int creepsInWave) {
        this.texture = Assets.getTexture(texture);
        this.speed = speed;
        this.size = size;
        this.health = health;
        this.treasure = treasure;
        this.creepsInWave = creepsInWave;
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

    public Treasure getTreasure() {
        return treasure;
    }

    public int getCreepsInWave() {
        return creepsInWave;
    }
}
