package com.xkings.pokemontd.entity;

import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;

import java.util.*;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.xkings.pokemontd.entity.ProjectileType.Type;

/**
 * Created by Tomas on 10/5/13.
 */
public enum TowerType {

    Needle("weedle", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 15), 3, new Treasure(10)),
    Pinch("kahuna", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 45), 3, new Treasure(20)),
    Sting("beedrill", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 135), 3, new Treasure(40)),
    Scratch("nidoranf", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 15), 3, new Treasure(10)),
    Bite("nidorina", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 45), 3, new Treasure(20)),
    Smash("nidoqueen", 1, 1, new ProjectileType("default", Type.FOLLOW_TARGET, 10, 0.2f, 15), 3, new Treasure(40));
    private static Map<TowerType, List<TowerType>> map;
    private final AtlasRegion texture;
    private final float speed;
    private final int size;
    private final ProjectileType projectile;
    private final float range;
    private final Treasure cost;
    private final AtlasRegion blockedTexture;

    private TowerType(String name, float speed, int size, ProjectileType projectile, float range, Treasure cost) {
        this.texture = Assets.getTexture(name);
        this.blockedTexture = Assets.getTexture("blocked");
        this.speed = speed;
        this.size = size;
        this.projectile = projectile;
        this.range = range;
        this.cost = cost;
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

    public Treasure getCost() {
        return cost;
    }

    public AtlasRegion getBlockedTexture() {
        return blockedTexture;
    }

    public String getName() {
        return this.toString();
    }

    public static Map<TowerType, List<TowerType>> getHierarchy() {
        if (map == null) {
            map = new HashMap<TowerType, List<TowerType>>();
            map.put(null, Arrays.asList(Needle, Scratch));
            map.put(Needle, Collections.unmodifiableList(Arrays.asList(Pinch)));
            map.put(Pinch, Collections.unmodifiableList(Arrays.asList(Sting)));
            map.put(Sting, Collections.<TowerType>unmodifiableList(Arrays.<TowerType>asList()));
            map.put(Scratch, Collections.unmodifiableList(Arrays.asList(Bite)));
            map.put(Bite, Collections.unmodifiableList(Arrays.asList(Smash)));
            map.put(Smash, Collections.<TowerType>unmodifiableList(Arrays.<TowerType>asList()));
            map = Collections.unmodifiableMap(map);
        }
        return map;
    }
}
