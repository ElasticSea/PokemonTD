package com.pixelthieves.pokemontd.entity.creep;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.entity.datatypes.CommonDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class CreepType implements CommonDataType {


    public static final CreepTypeBuilder creepTypeBuilder = new CreepTypeBuilder();
    private static final Map<CreepName, CreepType> waveStore =
            creepTypeBuilder.build(App.WORLD_SCALE, CreepTypeBuilder.normal);

    public static CreepType getWave(CreepName next) {
        return waveStore.get(next);
    }

    public static CreepType getWave(Element element, int count) {
        return elementWaves.get(element).get(count);
    }

    private static Map<Element, List<CreepType>> elementWaves = getElementWave();

    public static Map<Element, List<CreepType>> getElementWave() {
        Map<CreepName, CreepType> waves = creepTypeBuilder.build(App.WORLD_SCALE, CreepTypeBuilder.element);
        Map<Element, List<CreepType>> map = new HashMap<Element, List<CreepType>>();
        for (Map.Entry<CreepName, CreepType> creep : waves.entrySet()) {
            CreepType creepValue = creep.getValue();
            Treasure treasure = creepValue.getTreasure();
            for (Element element : Element.values()) {
                if (treasure.hasElement(element, 1)) {
                    List<CreepType> list = map.get(element);
                    if (list == null) {
                        list = new ArrayList<CreepType>();
                        map.put(element, list);
                    }
                    list.add(creepValue);
                    break;
                }
            }
        }
        return map;
    }

    private final CreepName name;

    private final float speed;
    private final float size;
    private final int health;
    private final Treasure treasure;
    private final int creepsInWave;
    private final float distanceBetweenCreeps;
    private final CreepAbilityType abilityType;
    private final int id;

    public CreepType(int id, CreepName name, float speed, float size, int health, Treasure treasure, int creepsInWave,
                     float distanceBetweenCreeps, CreepAbilityType abilityType) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.size = size;
        this.health = health;
        this.treasure = treasure;
        this.creepsInWave = creepsInWave;
        this.distanceBetweenCreeps = distanceBetweenCreeps;
        this.abilityType = abilityType;
    }

    public AtlasRegion getTexture() {
        return Assets.getTexture("creeps/" + name.toString().toLowerCase());
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

}
