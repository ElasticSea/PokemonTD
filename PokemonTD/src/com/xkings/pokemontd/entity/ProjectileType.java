package com.xkings.pokemontd.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/8/13.
 */
public class ProjectileType implements  CommonDataType{
    //DefaultProjectile("default", Type.FOLLOW_TARGET, 10, 0.2f, 1);

    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET,
    }

    private  TextureAtlas.AtlasRegion texture;
    private  Type type;
    private  float speed;
    private  float size;
    private  float damage;

    public ProjectileType(String texture, Type type, float speed, float size, float damage) {
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

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
