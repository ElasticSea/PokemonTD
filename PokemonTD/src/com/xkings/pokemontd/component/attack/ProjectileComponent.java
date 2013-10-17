package com.xkings.pokemontd.component.attack;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/13/13.
 */
public class ProjectileComponent extends AbilityComponent {
    public static final float DEFAULT_SPEED = 20.0f;
    public static final float DEFAULT_SIZE = 0.1f;
    private float range;
    private float splashRange;
    private TextureAtlas.AtlasRegion texture;
    private Type type;
    private float speed;
    private float size;

    public static AbilityComponent getNormal() {
        return getSplash(0);
    }

    public static AbilityComponent getSplash(float range) {
        return new ProjectileComponent("bullet", Type.FOLLOW_TARGET, DEFAULT_SIZE, DEFAULT_SPEED, range);
    }

    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET,
    }

    public ProjectileComponent(String texture, Type type, float size, float speed, float splashRange) {
        this.texture = Assets.getTexture(texture);
        this.type = type;
        this.speed = speed;
        this.size = size;
        this.splashRange = splashRange;
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

    public float getAoE() {
        return splashRange;
    }

    public void setSplashRange(float splashRange) {
        this.splashRange = splashRange;
    }
}
