package com.pixelthieves.pokemontd.entity.tower;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.attack.AbilityComponent;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.entity.datatypes.CommonDataType;

import java.util.*;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.pixelthieves.pokemontd.entity.tower.TowerName.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class TowerType implements CommonDataType {

    private static Map<TowerName, List<TowerType>> map;

    private final TowerName name;
    private final AtlasRegion texture;
    private final AtlasRegion blockedTexture;
    private final float size;
    private final float speed;
    private final float damage;
    private final float range;
    private final AbilityComponent attack;
    private final Treasure cost;
    private final EffectName effectName;

    public TowerType(Assets assets,TowerName name, EffectName effectName, float size, float speed, float damage, float range,
                     AbilityComponent attack, Treasure cost) {
        this.name = name;
        this.effectName = effectName;
        this.texture = assets.getTexture("towers/" + name.toString().toLowerCase());
        this.blockedTexture = assets.getTexture("blocked");
        this.size = size;
        this.speed = speed;
        this.damage = damage;
        this.range = range;
        this.attack = attack;
        this.cost = cost;
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

    public AbilityComponent getAttack() {
        return attack;
    }

    public float getRange() {
        return range;
    }

    public Treasure getCost() {
        return cost;
    }

    public AtlasRegion getBlockedTexture() {
        return blockedTexture;
    }

    public TowerName getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public EffectName getEffectName() {
        return effectName;
    }


}
