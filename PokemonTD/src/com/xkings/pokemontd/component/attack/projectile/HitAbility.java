package com.xkings.pokemontd.component.attack.projectile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.EffectName;
import com.xkings.pokemontd.component.attack.projectile.data.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomas on 10/13/13.
 */
public class HitAbility extends EffectData {
    public static final float SLOW_SPEED = 2.0f;
    public static final float NORMAL_SPEED = 4.0f;
    public static final float FAST_SPEED = 8.0f;
    public static final float DEFAULT_SIZE = 0.1f;
    public static final float BIG_SIZE = 0.25f;
    public static final float SUPER_BIG_SIZE = 0.5f;
    private final float aoe;
    private TextureAtlas.AtlasRegion texture;
    private Type type;
    private float speed;
    private float size;
    private final List<EffectData> effectData;

    public static AbilityComponent getNormal(String texture, float scale) {
        return new HitAbility(texture, Type.FOLLOW_TARGET, DEFAULT_SIZE * scale,
                NORMAL_SPEED * scale, new NormalData());
    }

    public static AbilityComponent getSplash(String texture, float scale, float range) {
        return new HitAbility(texture, Type.FOLLOW_TARGET, DEFAULT_SIZE * scale,
                NORMAL_SPEED * scale, range * scale, new NormalData());
    }

    public static AbilityComponent getTemLifeSteal(float scale, float duration) {
        return new HitAbility("bullet", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale,
                NORMAL_SPEED * scale, new TempLifeData(duration));
    }

    public static AbilityComponent getFire(float scale) {
        return new HitAbility( "fire", Type.FOLLOW_TARGET, BIG_SIZE * scale, FAST_SPEED * scale,
                new NormalData());
    }

    public static AbilityComponent getFireDot(float scale) {
        return new HitAbility( "fireAnimation", Type.FOLLOW_TARGET, BIG_SIZE * scale,
                FAST_SPEED * scale, new DotData("fire", 16, 0.25f), new NormalData());
    }

    public static AbilityComponent getNature(float scale, float slowRatio, float duration, float chance) {
        return new HitAbility( "leaf", Type.FOLLOW_TARGET, BIG_SIZE * scale, SLOW_SPEED * scale,
                new NormalData(), new SlowData("entangle", slowRatio, duration, chance));
    }

    public static AbilityComponent getClaw(float scale, float chance, int iterations) {
        return new HitAbility( "leaf", Type.IMMEDIATE_ATTACK, BIG_SIZE * scale, FAST_SPEED * scale,
                new NormalData(), new BonusAttack(chance, iterations));
    }

    public static AbilityComponent getDumbClaw(float scale, float chance, int iterations) {
        return new HitAbility( "leaf", Type.IMMEDIATE_ATTACK, BIG_SIZE * scale, FAST_SPEED * scale,
                new BonusAttack(chance, iterations));
    }

    public static AbilityComponent getPoison(float scale, float slowRatio, float duration, float chance) {
        return new HitAbility( "poison", Type.FOLLOW_TARGET, BIG_SIZE * scale, SLOW_SPEED * scale,
                new DotData("poisonEffect", 16, 0.25f), new SlowData("", slowRatio, duration, chance));
    }

    public static AbilityComponent getSlow(String bulletTexture, String texture, float scale, float range,
                                           float slowRatio, float duration, float chance) {
        return new HitAbility( bulletTexture, Type.FOLLOW_TARGET, DEFAULT_SIZE * scale,
                NORMAL_SPEED * scale, range * scale, new SlowData(texture, slowRatio, duration, chance));
    }

    public static AbilityComponent getMoney(float scale, float ratio) {
        return new HitAbility( "coinAttack", Type.FOLLOW_TARGET, BIG_SIZE * scale,
                NORMAL_SPEED * scale, new MoneyData("", ratio));
    }

    public static AbilityComponent getLife(float scale, float ratio, float chance) {
        return new HitAbility( "hearth", Type.FOLLOW_TARGET, BIG_SIZE * scale, NORMAL_SPEED * scale,
                new LifeData("", ratio, chance));
    }

    public static AbilityComponent getAuraDmg(float scale, float range) {
        return new HitAbility( "bullet", Type.IMMEDIATE_NOCONTACT_DAMAGE, BIG_SIZE * scale,
                NORMAL_SPEED * scale, range * scale, new NormalData());
    }

    public static AbilityComponent getVolcano(float scale, float range, int iterations, float interval) {
        return new HitAbility( "bullet", Type.IMMEDIATE_NOCONTACT_DAMAGE, BIG_SIZE * scale,
                NORMAL_SPEED * scale, range * scale, new NormalData(), new DotData("fire", iterations, interval));
    }

    public static AbilityComponent getCharge(float scale) {
        return new HitAbility( "charge", Type.FOLLOW_TARGET, SUPER_BIG_SIZE * scale,
                FAST_SPEED * scale, new NormalData());
    }

    public static AbilityComponent getChangeDirection(float scale, int duration, float chance) {
        return new HitAbility( "hypno", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale, FAST_SPEED * scale,
                new ChangeDirectionData(duration, chance));
    }


    public static AbilityComponent getBubble(String texture, float scale) {
        return new HitAbility( texture, Type.PASS_THROUGH, BIG_SIZE * scale, NORMAL_SPEED * scale,
                new BubbleData(0.25f, 1f));
    }

    public static AbilityComponent getBubbleGrowing(float scale) {
        return new HitAbility( "bubble", Type.PASS_THROUGH, BIG_SIZE * scale, NORMAL_SPEED * scale,
                new BubbleData(0.25f, 1f));
    }


    public static AbilityComponent getIncreasingDamage(float scale) {
        return new HitAbility( "leaf", Type.FOLLOW_TARGET, DEFAULT_SIZE * scale, FAST_SPEED * scale,
                new IncreasingDamageData("", 1.1f, 0.5f, 4));
    }

    public enum Type {
        FOLLOW_TARGET, LAST_KNOWN_PLACE, AHEAD_TARGET, PASS_THROUGH, IMMEDIATE_ATTACK, IMMEDIATE_NOCONTACT_DAMAGE;
    }

    public HitAbility(String texture, Type type, float size, float speed, EffectData... effectData) {
        this(texture, type, size, speed, 0, effectData);
    }

    public HitAbility( String texture, Type type, float size, float speed, float aoe,
                      EffectData... effectData) {
        this.effectData = Arrays.asList(effectData);
        this.texture = Assets.getTexture(texture);
        this.type = type;
        this.speed = speed;
        this.size = size;
        this.aoe = aoe;
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

    public List<EffectData> getEffectData() {
        return effectData;
    }

    public float getAoe() {
        return aoe;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        throw new IllegalStateException("This method should not be invoked in this class.");
    }
}
