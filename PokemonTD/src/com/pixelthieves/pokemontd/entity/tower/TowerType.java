package com.pixelthieves.pokemontd.entity.tower;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.attack.AbilityComponent;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.entity.datatypes.CommonDataType;

import java.util.*;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.pixelthieves.pokemontd.entity.tower.TowerName.*;

/**
 * Created by Seda on 10/5/13.
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

    public TowerType(TowerName name, EffectName effectName, float size, float speed, float damage, float range,
                     AbilityComponent attack, Treasure cost) {
        this.name = name;
        this.effectName = effectName;
        this.texture = Assets.getTexture("towers/" + name.toString().toLowerCase());
        this.blockedTexture = Assets.getTexture("blocked");
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

    public static List<TowerType> getHierarchy(TowerName towerName) {
        if (map == null) {
            // Pures
            map = new HashMap<TowerName, List<TowerType>>();
            map.put(null, getTypes(Needle, Scratch, Shop, Splash, Sparkle, Flower, Chicken, Spooky));
            map.put(Needle, getTypes(Pinch));
            map.put(Pinch, getTypes(Sting));
            map.put(Sting, getTypes());

            map.put(Scratch, getTypes(Bite));
            map.put(Bite, getTypes(Smash));
            map.put(Smash, getTypes());

            map.put(Splash, getTypes(Ripple, Noble, Sunny, Poison, Ice));
            map.put(Ripple, getTypes(Tsunami));

            map.put(Sparkle, getTypes(Burning, Noble, Burst, Punch, Sneaky));
            map.put(Burning, getTypes(Inferno));

            map.put(Flower, getTypes(Forest, Sunny, Burst, Pebble, Dizzy));
            map.put(Forest, getTypes(Nature));

            map.put(Chicken, getTypes(Screech, Poison, Punch, Pebble, Spell));
            map.put(Screech, getTypes(Claw));

            map.put(Spooky, getTypes(Haunted, Ice, Sneaky, Dizzy, Spell));
            map.put(Haunted, getTypes(Nightmare));

            // 2 Element
            map.put(Noble, getTypes(Majestic, Grind, Throttling, Slime));
            map.put(Majestic, getTypes(Magnificent));

            map.put(Sunny, getTypes(Solar, Grind, Vampire, Disease));
            map.put(Solar, getTypes(Photonic));

            map.put(Poison, getTypes(Toxic, Throttling, Vampire, Confused));
            map.put(Toxic, getTypes(Venom));

            map.put(Ice, getTypes(Freezing, Slime, Disease, Confused));
            map.put(Freezing, getTypes(Polar));

            map.put(Burst, getTypes(Pyro, Grind, Charged, Stomp));
            map.put(Pyro, getTypes(Flamethrower));

            map.put(Punch, getTypes(Takedown, Throttling, Charged, Erruption));
            map.put(Takedown, getTypes(Knockout));

            map.put(Sneaky, getTypes(Stealth, Slime, Stomp, Erruption));
            map.put(Stealth, getTypes(Thief));

            map.put(Pebble, getTypes(Rocky, Vampire, Charged, Hypnotic));
            map.put(Rocky, getTypes(Massive));

            map.put(Dizzy, getTypes(Paralyze, Disease, Stomp, Hypnotic));
            map.put(Paralyze, getTypes(Crippling));

            map.put(Spell, getTypes(Enchanted, Confused, Erruption, Hypnotic));
            map.put(Enchanted, getTypes(Magic));

            // 3 Element
            map.put(Grind, getTypes(Pulverize));
            map.put(Throttling, getTypes(Galloping));
            map.put(Slime, getTypes(Acid));
            map.put(Vampire, getTypes(Dracula));
            map.put(Disease, getTypes(Epidemic));
            map.put(Confused, getTypes(Supersonic));
            map.put(Charged, getTypes(Supercharged));
            map.put(Stomp, getTypes(Earthquake));
            map.put(Erruption, getTypes(Volcanic));
            map.put(Hypnotic, getTypes(Illusion));


            map = Collections.unmodifiableMap(map);
        }
        List<TowerType> result = map.get(towerName);
        return result != null ? result : getTypes();
    }

    private static Map<TowerName, TowerType> towerTypeMap = new TowerTypeBuilder().build(App.WORLD_SCALE);

    private static List<TowerType> getTypes(TowerName... names) {
        List<TowerType> list = new ArrayList<TowerType>();
        for (TowerName towerName : names) {
            list.add(towerTypeMap.get(towerName));
        }
        return Collections.unmodifiableList(list);
    }

    public static TowerType getType(TowerName towerName) {
        return towerTypeMap.get(towerName);
    }
}
