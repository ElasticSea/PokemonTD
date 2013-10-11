package com.xkings.pokemontd.entity;

import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum CreepType implements CommonDataType {

    DefaultCreep("default", 1f, 0.5f, 1, new Treasure(5), 5),
    DefaultCreep2("default", 1f, 0.5f, 2, new Treasure(5), 15);
    private final AtlasRegion texture;
    private final float speed;
    private final float size;
    private final int health;
    private final Treasure treasure;
    private final int creepsInWave;

    private CreepType(String texture, float speed, float size, int health, Treasure treasure,int creepsInWave) {
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

    public float getSize() {
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
