package com.pixelthieves.elementtd.entity.tower;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.elementtd.App;

import java.util.*;

import static com.pixelthieves.elementtd.entity.tower.TowerName.*;

/**
 * Created by Tomas on 12/14/13.
 */
public class TowerTypeResolver {

    public TowerTypeResolver(Assets assets) {
        towerTypeMap= new TowerTypeBuilder(assets).build(App.WORLD_SCALE);
    }

    private  Map<TowerName, List<TowerType>> map;

    public  List<TowerType> getHierarchy(TowerName towerName) {
        if (map == null) {
            // Pures
            map = new HashMap<TowerName, List<TowerType>>();
            map.put(null, getTypes(Needle, Bombard, Shop, Splash, Sparkle, Flower, Chicken, Spooky));
            map.put(Needle, getTypes(Arrow));
            map.put(Arrow, getTypes(Spear));
            map.put(Spear, getTypes());

            map.put(Bombard, getTypes(Mortar));
            map.put(Mortar, getTypes(Cannon));
            map.put(Cannon, getTypes());

            map.put(Splash, getTypes(Ripple, Noble, Sunny, Poison, Ice));
            map.put(Ripple, getTypes(Tsunami));

            map.put(Sparkle, getTypes(Burning, Noble, Burst, Blow, Money));
            map.put(Burning, getTypes(Inferno));

            map.put(Flower, getTypes(Forest, Sunny, Burst, Pebble, Dizzy));
            map.put(Forest, getTypes(Nature));

            map.put(Chicken, getTypes(Screech, Poison, Blow, Pebble, Spell));
            map.put(Screech, getTypes(Claw));

            map.put(Spooky, getTypes(Haunted, Ice, Money, Dizzy, Spell));
            map.put(Haunted, getTypes(Nightmare));

            // 2 Element
            map.put(Noble, getTypes(Majestic, Grind, Meteor, Slime));
            map.put(Majestic, getTypes(Magnificent));

            map.put(Sunny, getTypes(Solar, Grind, Vampire, Disease));
            map.put(Solar, getTypes(Photonic));

            map.put(Poison, getTypes(Toxic, Meteor, Vampire, Confused));
            map.put(Toxic, getTypes(Venom));

            map.put(Ice, getTypes(Freezing, Slime, Disease, Confused));
            map.put(Freezing, getTypes(Polar));

            map.put(Burst, getTypes(Pyro, Grind, Charged, Stomp));
            map.put(Pyro, getTypes(Flamethrower));

            map.put(Blow, getTypes(Chop, Meteor, Charged, Erruption));
            map.put(Chop, getTypes(Carving));

            map.put(Money, getTypes(Gold, Slime, Stomp, Erruption));
            map.put(Gold, getTypes(Treasure));

            map.put(Pebble, getTypes(Rocky, Vampire, Charged, Hypnotic));
            map.put(Rocky, getTypes(Massive));

            map.put(Dizzy, getTypes(Paralyze, Disease, Stomp, Hypnotic));
            map.put(Paralyze, getTypes(Crippling));

            map.put(Spell, getTypes(Enchanted, Confused, Erruption, Hypnotic));
            map.put(Enchanted, getTypes(Magic));

            // 3 Element
            map.put(Grind, getTypes(Pulverize));
            map.put(Meteor, getTypes(Asteroid));
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

    private  Map<TowerName, TowerType> towerTypeMap;

    private  List<TowerType> getTypes(TowerName... names) {
        List<TowerType> list = new ArrayList<TowerType>();
        for (TowerName towerName : names) {
            list.add(towerTypeMap.get(towerName));
        }
        return Collections.unmodifiableList(list);
    }

    public  TowerType getType(TowerName towerName) {
        return towerTypeMap.get(towerName);
    }


}
