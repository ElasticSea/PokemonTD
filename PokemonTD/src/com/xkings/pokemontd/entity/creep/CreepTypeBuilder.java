package com.xkings.pokemontd.entity.creep;

import com.xkings.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class CreepTypeBuilder {

    public static final List<Specs> normal = getNormal();
    public static final List<Specs> element = getElement();

    public static List<Specs> getNormal() {
        List<Specs> list = new ArrayList<Specs>();
        list.add(new Specs(CreepName.Hoothoot, 75, Treasure.fromGold(1), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Ledian, 88, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Crobat, 103, Treasure.fromGold(1), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Lanturn, 120, Treasure.fromGold(1), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Quagsire, 141, Treasure.fromGold(2), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Espeon, 164, Treasure.fromGold(2), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Forretress, 192, Treasure.fromGold(2), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Snubbull, 225, Treasure.fromGold(2), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Corsola, 263, Treasure.fromGold(2), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Miltank, 308, Treasure.fromGold(3), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Entei, 361, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Blaziken, 422, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Wurmple, 494, Treasure.fromGold(3), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Beautifly, 577, Treasure.fromGold(4), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Nuzleaf, 676, Treasure.fromGold(4), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Pelipper, 790, Treasure.fromGold(5), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Kirlia, 925, Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Breloom, 1082, Treasure.fromGold(6), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Shedinja, 1266, Treasure.fromGold(6), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Whismur, 1481, Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Loudred, 1737, Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Exploud, 2036, Treasure.fromGold(8), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Delcatty, 2387, Treasure.fromGold(9), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Sableye, 2799, Treasure.fromGold(10), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Mawile, 3282, Treasure.fromGold(11), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Lairon, 3848, Treasure.fromGold(12), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Flygon, 4512, Treasure.fromGold(13), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Whiscash, 5290, Treasure.fromGold(14), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Claydol, 6203, Treasure.fromGold(16), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Lileep, 72273, Treasure.fromGold(17), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Feebas, 8546, Treasure.fromGold(19), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Kecleon, 10041, Treasure.fromGold(21), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Banette, 11799, Treasure.fromGold(23), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Duskull, 13863, Treasure.fromGold(26), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Tropius, 16290, Treasure.fromGold(28), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Huntail, 19140, Treasure.fromGold(31), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Kyogre, 22490, Treasure.fromGold(34), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Prinplup, 26426, Treasure.fromGold(37), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Wormadam, 31050, Treasure.fromGold(41), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Bronzong, 36484, Treasure.fromGold(45), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Yanmega, 43051, Treasure.fromGold(50), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Glaceon, 50800, Treasure.fromGold(55), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Mamoswine, 59944, Treasure.fromGold(60), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Palkia, 70734, Treasure.fromGold(66), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Regigigas, 83466, Treasure.fromGold(73), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Giratina, 98490, Treasure.fromGold(80), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Manaphy, 116218, Treasure.fromGold(88), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Darkrai, 137137, Treasure.fromGold(97), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Snivy, 161822, Treasure.fromGold(107), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Tepig, 190950, Treasure.fromGold(117), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Emboar, 229140, Treasure.fromGold(129), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Watchog, 274968, Treasure.fromGold(142), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Herdier, 329962, Treasure.fromGold(156), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Liepard, 395954, Treasure.fromGold(172), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Panpour, 475145, Treasure.fromGold(189), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Tirtouga, 570174, Treasure.fromGold(208), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Zorua, 684209, Treasure.fromGold(229), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Klinklang, 821050, Treasure.fromGold(252), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Lampent, 985261, Treasure.fromGold(277), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Druddigon, 1182313, Treasure.fromGold(304), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Hydreigon, 295578, Treasure.fromGold(0), CreepAbilityType.BOSS));
        return list;
    }

    public static List<Specs> getElement() {
        List<Specs> list = new ArrayList<Specs>();
        list.add(new Specs(CreepName.Xatu, 100, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Mareep, 800, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Jumpluff, 10000, Treasure.fromPure(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Flaaffy, 100, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Ampharos, 800, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Yanma, 10000, Treasure.fromFire(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Bellossom, 100, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Politoed, 800, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Skiploom, 10000, Treasure.fromLight(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Marill, 100, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Azumarill, 800, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Wooper, 10000, Treasure.fromWater(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Sudowoodo, 100, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Sunkern, 800, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Sunflora, 10000, Treasure.fromNature(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Umbreon, 100, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Aipom, 800, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Hoppip, 10000, Treasure.fromDarkness(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Electivire, 295578, Treasure.fromSoul(1), CreepAbilityType.BOSS));
        return list;
    }

    /**
     * Creates predefined Map of {@link CreepType} templates.
     *
     * @param scale scale of templates size, speed, etc..
     * @param data
     * @return map of CreepType templates
     */
    public Map<CreepName, CreepType> build(float scale, List<Specs> data) {
        List<CreepType> list = new ArrayList<CreepType>();
        for (int id = 0; id < data.size(); id++) {
            Specs specification = data.get(id);
            CreepAbilityType ability =  CreepAbilityType.SPAWN;
            int health = specification.getHealth();
            float speed = scale;
            float size = scale;
            int creepsInWay = 8;
            float distanceBetweenCreeps = size;

            switch (ability) {
                case FAST:
                    speed *= 2;
                    health /= 2;
                    break;
                case SWARM:
                    health /= 2;
                    size /= 1.5f;
                    creepsInWay *= 4;
                    distanceBetweenCreeps = size / 3;
                    break;
                case RESURRECT:
                    break;
                case INVISIBLE:
                    break;
                case SPAWN:
                    health *= 4f;
                    size *= 2f;
                    creepsInWay /= 4;
                    break;
                case HEALING:
                    break;
                case BOSS:
                    health *= creepsInWay;
                    size *= 3f;
                    creepsInWay = 1;
                    break;
            }

            list.add(new CreepType(id, specification.getName(), speed, size, health, specification.getTreasure(),
                    creepsInWay, distanceBetweenCreeps, ability));
        }

        Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
        for (CreepType creepType : list) {
            map.put(creepType.getName(), creepType);
        }

        return map;

    }

    private static class Specs {


        private final CreepName name;
        private final int health;
        private final Treasure treasure;
        private final CreepAbilityType abilityType;

        private Specs(CreepName name, int health, Treasure treasure, CreepAbilityType abilityType) {
            this.name = name;
            this.health = health;
            this.treasure = treasure;
            this.abilityType = abilityType;
        }

        public CreepName getName() {
            return name;
        }

        public Treasure getTreasure() {
            return treasure;
        }

        public CreepAbilityType getAbilityType() {
            return abilityType;
        }

        public int getHealth() {
            return health;
        }
    }

}
