package com.xkings.pokemontd.entity.datatypes;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class CreepType implements CommonDataType {

    public static class DataStore {
        private final Map<CreepName, CreepType> data;

        private DataStore(String fileName) {
            this.data = getData(fileName);
        }

        private Map<CreepName, CreepType> getData(String fileName) {
            Type type = new TypeToken<List<CreepType>>() {
            }.getType();
            List<CreepType> creepList = new Gson().fromJson(new FileHandle(fileName).readString(), type);

            Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
            for (CreepType creepType : creepList) {
                map.put((CreepName) creepType.getName(), creepType);
            }
            return map;
        }

        public CreepType getType(CreepName creepName) {
            return data.get(creepName);
        }
    }

    private static final DataStore dataStore = new DataStore("creeps.json");

    public static CreepType getType(CreepName next) {
        return dataStore.getType(next);
    }

    public enum CreepName implements EntityName {
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

    public EntityName getName() {
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
