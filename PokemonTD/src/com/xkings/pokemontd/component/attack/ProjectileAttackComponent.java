package com.xkings.pokemontd.component.attack;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/13/13.
 */
public class ProjectileAttackComponent extends AttackComponent {
    private TextureAtlas.AtlasRegion texture;
    private Type type;
    private float speed;
    private float size;
    private float damage;


    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET,
    }

    public ProjectileAttackComponent(String texture, Type type, float speed, float size, float damage) {
        this.texture = Assets.getTexture(texture);
        this.type = type;
        this.speed = speed;
        this.size = size;
        this.damage = damage;
    }

    public TextureAtlas.AtlasRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
