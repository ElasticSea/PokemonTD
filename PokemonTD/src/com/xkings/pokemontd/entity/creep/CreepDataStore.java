package com.xkings.pokemontd.entity.creep;

import com.badlogic.gdx.files.FileHandle;
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

    public CreepDataStore(Map<CreepName, CreepType> data) {
        this.data = data;
    }

    public CreepType getType(CreepName creepName) {
        return data.get(creepName);
    }

    public static CreepDataStore createInstanceFromJSON(String fileName) {
        Type type = new TypeToken<List<CreepType>>() {
        }.getType();
        List<CreepType> creepList = new Gson().fromJson(new FileHandle(fileName).readString(), type);

        Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
        for (CreepType creepType : creepList) {
            map.put(creepType.getName(), creepType);
        }
        return new CreepDataStore(map);
    }
}

