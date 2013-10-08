package com.xkings.pokemontd.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/8/13.
 */
public enum ProjectileType {
    DefaultProjectile("default", Type.FOLLOW_TARGET, 10, 0.2f, 1);

    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET,
    }

    private final TextureAtlas.AtlasRegion texture;
    private final Type type;
    private final float speed;
    private final float size;
    private final float damage;

    private ProjectileType(String texture, Type type, float speed, float size, float damage) {
        this.texture = Assets.getTexture(texture);
        this.type = type;
        this.speed = speed;
        this.size = size;
        this.damage = damage;
    }

    public TextureAtlas.AtlasRegion getTexture() {
        return texture;
    }

    public Type getType() {
        return type;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSize() {
        return size;
    }

    public float getDamage() {
        return damage;
    }
}
