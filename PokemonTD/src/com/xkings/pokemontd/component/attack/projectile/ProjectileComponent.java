package com.xkings.pokemontd.component.attack.projectile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.projectile.data.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomas on 10/13/13.
 */
public class ProjectileComponent extends AbilityComponent {
    public static final float SLOW_SPEED = 2.0f;
    public static final float NORMAL_SPEED = 4.0f;
    public static final float FAST_SPEED = 8.0f;
    public static final float DEFAULT_SIZE = 0.1f;
    public static final float BIG_SIZE = 0.25f;
    private TextureAtlas.AtlasRegion texture;
    private Type type;
    private float speed;
    private float size;
    private final List<AbilityComponent> ability;
    private static AbilityComponent none;

    public static AbilityComponent getNormal(float scale) {
        return new ProjectileComponent("bullet", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale, NORMAL_SPEED * scale,
                new NormalData());
    }

    public static AbilityComponent getSplash(float scale, float range) {
        return new ProjectileComponent("bullet", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale, NORMAL_SPEED * scale,
                new AoeComponent(range * scale));
    }

    public static AbilityComponent getTemLifeSteal(float scale, float ratio, float duration) {
        return new ProjectileComponent("bullet", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale, NORMAL_SPEED * scale,
                new LifeStealData(ratio, duration));
    }

    public static AbilityComponent getBubble(float scale) {
        return new ProjectileComponent("bubble", Type.PASS_THROUGH, BIG_SIZE * scale, NORMAL_SPEED * scale,
                new BubbleData(0.25f));
    }

    public static AbilityComponent getFire(float scale) {
        return new ProjectileComponent("fire", Type.FOLLOW_TARGET, BIG_SIZE * scale, FAST_SPEED * scale,
                new NormalData());
    }

    public static AbilityComponent getFireDot(float scale) {
        return new ProjectileComponent("fire", Type.FOLLOW_TARGET, BIG_SIZE * scale, FAST_SPEED * scale,
                new DotData(8, 0.25f), new NormalData());
    }

    public static AbilityComponent getNature(float scale, float slowRatio, float duration, float chance) {
        return new ProjectileComponent("leaf", Type.FOLLOW_TARGET, BIG_SIZE * scale, SLOW_SPEED * scale,
                new NormalData(), new SlowData(slowRatio, duration, chance));
    }

    public static AbilityComponent getClaw(float scale, float chance, int iterations) {
        return new ProjectileComponent("leaf", Type.IMMEDIATE_ATTACK, BIG_SIZE * scale, FAST_SPEED * scale,
                new NormalData(), new BonusAttack(chance, iterations));
    }

    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET, PASS_THROUGH, IMMEDIATE_ATTACK;
    }

    public ProjectileComponent(String texture, Type type, float size, float speed, AbilityComponent... abilities) {
        this.ability = Arrays.asList(abilities);
        this.texture = Assets.getTexture(texture);
        this.type = type;
        this.speed = speed;
        this.size = size;
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

    public void setSize(float size) {
        this.size = size;
    }

    public List<AbilityComponent> getAbility() {
        return ability;
    }
}
