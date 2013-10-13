package com.xkings.pokemontd.entity;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.xkings.pokemontd.entity.CreepType.CreepName.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class CreepType implements CommonDataType {


    public enum CreepName {
        Chansey, Ditto, Jynx, Kabuto, Lickitung, Porygon, Mew;
    }

    private enum CreepAbilityType {
        NORMAL, FAST, SWARM, RESURRECT, INVISIBLE, SPAWN, HEALING;
    }

    private final CreepName name;
    private final float speed;
    private final float size;
    private final int health;
    private final Treasure treasure;
    private final int creepsInWave;
    private final CreepAbilityType abilityType;

    private CreepType(CreepName name, float speed, float size, int health, Treasure treasure, int creepsInWave,
                      CreepAbilityType abilityType) {
        this.name = name;
        this.speed = speed;
        this.size = size;
        this.health = health;
        this.treasure = treasure;
        this.creepsInWave = creepsInWave;
        this.abilityType = abilityType;
    }

    public AtlasRegion getTexture() {
        return Assets.getTexture(name.toString().toLowerCase());
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


    private static final Map<CreepName, CreepType> data = getData();

    private static Map<CreepName, CreepType> getData() {
        Type type = new TypeToken<List<CreepType>>() {
        }.getType();
        List<CreepType> creepList = new Gson().fromJson(new FileHandle("creeps.json").readString(), type);

        Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
        for (CreepType creepType : creepList) {
            map.put(creepType.name, creepType);
        }
        return map;
    }

    public static CreepType getType(CreepName creepName) {
        return data.get(creepName);
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
}
