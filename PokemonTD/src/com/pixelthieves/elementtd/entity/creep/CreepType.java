package com.pixelthieves.elementtd.entity.creep;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.elementtd.Treasure;
import com.pixelthieves.elementtd.entity.datatypes.CommonDataType;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class CreepType implements CommonDataType {


    private final CreepName name;

    private final float speed;
    private final float size;
    private final int health;
    private final Treasure treasure;
    private final int creepsInWave;
    private final float distanceBetweenCreeps;
    private final CreepAbilityType abilityType;
    private final int id;
    private final AtlasRegion texture;
    private Assets assets;

    public CreepType(Assets assets, int id, CreepName name, float speed, float size, int health, Treasure treasure,
                     int creepsInWave, float distanceBetweenCreeps, CreepAbilityType abilityType) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.size = size;
        this.health = health;
        this.treasure = treasure;
        this.creepsInWave = creepsInWave;
        this.distanceBetweenCreeps = distanceBetweenCreeps;
        this.abilityType = abilityType;
        this.assets = assets;
        this.texture = assets.getTexture("creeps/" + name.toString().toLowerCase());
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

    public CreepName getName() {
        return name;
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

    public float getDistanceBetweenCreeps() {
        return distanceBetweenCreeps;
    }

    public CreepAbilityType getAbilityType() {
        return abilityType;
    }

    public int getId() {
        return id;
    }

    public static void save() {
      /*  List<CreepType> list = new ArrayList<CreepType>();
        list.add(new CreepType(Chansey, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.NORMAL));
        list.add(new CreepType(Ditto, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.SWARM));
        list.add(new CreepType(Jynx, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.SPAWN));
        list.add(new CreepType(Kabuto, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.INVISIBLE));
        list.add(new CreepType(Lickitung, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.FAST));
        list.add(new CreepType(Porygon, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.RESURRECT));
        list.add(new CreepType(Mew, 1f, 0.5f, 1, new Treasure(5), 5, CreepAbilityType.HEALING));
        new FileHandle("json").writeString(new Gson().toJson(list), false);   */
    }

    public Assets getAssets() {
        return assets;
    }
}
