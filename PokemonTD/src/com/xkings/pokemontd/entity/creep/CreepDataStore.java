package com.xkings.pokemontd.entity.creep;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 10/14/13.
 */
public class CreepDataStore {
    private final Map<CreepName, CreepType> data;

    public CreepDataStore(List<CreepType> creepList) {
        Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
        for (CreepType creepType : creepList) {
            map.put(creepType.getName(), creepType);
        }
        this.data = map;
    }

    public CreepType getType(CreepName creepName) {
        return data.get(creepName);
    }

    public static CreepDataStore createInstanceFromJSON(String fileName) {
        Type type = new TypeToken<List<CreepType>>() {
        }.getType();
        return new Gson().fromJson(Gdx.files.internal("data/" + fileName).readString(), type);
    }
}

