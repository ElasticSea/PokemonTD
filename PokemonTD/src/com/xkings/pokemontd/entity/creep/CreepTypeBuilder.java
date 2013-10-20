package com.xkings.pokemontd.entity.creep;

import com.xkings.pokemontd.App;
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

    private final List<CreepTypeSpecification> data = getData();

    private List<CreepTypeSpecification> getData() {
        List<CreepTypeSpecification> list = new ArrayList<CreepTypeSpecification>();
        list.add(new CreepTypeSpecification(CreepName.Hoothoot, 75,Treasure.fromGold(1), CreepAbilityType.SPAWN));
        list.add(new CreepTypeSpecification(CreepName.Ledian, 88,Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Crobat, 103,Treasure.fromGold(1), CreepAbilityType.SWARM));
        list.add(new CreepTypeSpecification(CreepName.Lanturn, 120,Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Quagsire, 141,Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Espeon, 164,Treasure.fromGold(2), CreepAbilityType.RESURRECT));
        list.add(new CreepTypeSpecification(CreepName.Forretress, 192,Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Snubbull, 225,Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Corsola, 263,Treasure.fromGold(2), CreepAbilityType.HEALING));
        list.add(new CreepTypeSpecification(CreepName.Miltank, 308,Treasure.fromGold(3), CreepAbilityType.SPAWN));
        list.add(new CreepTypeSpecification(CreepName.Entei, 361,Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Blaziken, 422,Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Wurmple, 494,Treasure.fromGold(3), CreepAbilityType.RESURRECT));
        list.add(new CreepTypeSpecification(CreepName.Beautifly, 577,Treasure.fromGold(4), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Nuzleaf, 676,Treasure.fromGold(4), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Pelipper, 790,Treasure.fromGold(5), CreepAbilityType.HEALING));
        list.add(new CreepTypeSpecification(CreepName.Kirlia, 925,Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Breloom, 1082,Treasure.fromGold(6), CreepAbilityType.SPAWN));
        list.add(new CreepTypeSpecification(CreepName.Shedinja, 1266,Treasure.fromGold(6), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Whismur, 1481,Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Loudred, 1737,Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Exploud, 2036,Treasure.fromGold(8), CreepAbilityType.FAST));
        list.add(new CreepTypeSpecification(CreepName.Delcatty, 2387,Treasure.fromGold(9), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Sableye, 2799,Treasure.fromGold(10), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Mawile, 3282,Treasure.fromGold(11), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Lairon, 3848,Treasure.fromGold(12), CreepAbilityType.HEALING));
        list.add(new CreepTypeSpecification(CreepName.Flygon, 4512,Treasure.fromGold(13), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Whiscash, 5290,Treasure.fromGold(14), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Claydol, 6203,Treasure.fromGold(16), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Lileep, 72273,Treasure.fromGold(17), CreepAbilityType.RESURRECT));
        list.add(new CreepTypeSpecification(CreepName.Feebas, 8546,Treasure.fromGold(19), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Kecleon, 10041,Treasure.fromGold(21), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Banette, 11799,Treasure.fromGold(23), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Duskull, 13863,Treasure.fromGold(26), CreepAbilityType.FAST));
        list.add(new CreepTypeSpecification(CreepName.Tropius, 16290,Treasure.fromGold(28), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Huntail, 19140,Treasure.fromGold(31), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Kyogre, 22490,Treasure.fromGold(34), CreepAbilityType.SWARM));
        list.add(new CreepTypeSpecification(CreepName.Prinplup, 26426,Treasure.fromGold(37), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Wormadam, 31050,Treasure.fromGold(41), CreepAbilityType.INVISIBLE));
        list.add(new CreepTypeSpecification(CreepName.Bronzong, 36484,Treasure.fromGold(45), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Yanmega, 43051,Treasure.fromGold(50), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Glaceon, 50800,Treasure.fromGold(55), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Mamoswine, 59944,Treasure.fromGold(60), CreepAbilityType.RESURRECT));
        list.add(new CreepTypeSpecification(CreepName.Palkia, 70734,Treasure.fromGold(66), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Regigigas, 83466,Treasure.fromGold(73), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Giratina, 98490,Treasure.fromGold(80), CreepAbilityType.SWARM));
        list.add(new CreepTypeSpecification(CreepName.Manaphy, 116218,Treasure.fromGold(88), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Darkrai, 137137,Treasure.fromGold(97), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Snivy, 161822,Treasure.fromGold(107), CreepAbilityType.HEALING));
        list.add(new CreepTypeSpecification(CreepName.Tepig, 190950,Treasure.fromGold(117), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Emboar, 229140,Treasure.fromGold(129), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Watchog, 274968,Treasure.fromGold(142), CreepAbilityType.SPAWN));
        list.add(new CreepTypeSpecification(CreepName.Herdier, 329962,Treasure.fromGold(156), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Liepard, 395954,Treasure.fromGold(172), CreepAbilityType.INVISIBLE));
        list.add(new CreepTypeSpecification(CreepName.Panpour, 475145,Treasure.fromGold(189), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Tirtouga, 570174,Treasure.fromGold(208), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Zorua, 684209,Treasure.fromGold(229), CreepAbilityType.RESURRECT));
        list.add(new CreepTypeSpecification(CreepName.Klinklang, 821050,Treasure.fromGold(252), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Lampent, 985261,Treasure.fromGold(277), CreepAbilityType.FAST));
        list.add(new CreepTypeSpecification(CreepName.Druddigon, 1182313,Treasure.fromGold(304), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Hydreigon, 2364626,Treasure.fromGold(0), CreepAbilityType.BOSS));

        list.add(new CreepTypeSpecification(CreepName.Xatu, 3484, Treasure.fromPure(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Mareep, 26426,Treasure.fromPure(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Jumpluff, 570174,Treasure.fromPure(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Flaaffy, 3848,Treasure.fromFire(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Ampharos,26426 , Treasure.fromFire(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Yanma, 570174,Treasure.fromFire(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Bellossom, 3484,Treasure.fromLight(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Politoed, 26426,Treasure.fromLight(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Skiploom, 570174, Treasure.fromLight(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Marill, 3484,Treasure.fromWater(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Azumarill, 26426,Treasure.fromWater(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Wooper, 570174,Treasure.fromWater(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Sudowoodo, 3484, Treasure.fromNature(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Sunkern, 26426,Treasure.fromNature(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Sunflora, 570174,Treasure.fromNature(3), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Umbreon, 3848,Treasure.fromDarkness(1), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Aipom, 26426, Treasure.fromDarkness(2), CreepAbilityType.NORMAL));
        list.add(new CreepTypeSpecification(CreepName.Hoppip, 570174,Treasure.fromDarkness(3), CreepAbilityType.NORMAL));

        list.add(new CreepTypeSpecification(CreepName.Flaaffy,1204626 ,Treasure.fromSoul(3), CreepAbilityType.NORMAL));
        return list;
    }


    public Map<CreepName, CreepType> build(float scale) {
        List<CreepType> list = new ArrayList<CreepType>();
        for (CreepTypeSpecification specification : data) {
            CreepAbilityType ability = specification.getAbilityType();
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
                    distanceBetweenCreeps = size/3;
                    break;
                case RESURRECT:
                    break;
                case INVISIBLE:
                    break;
                case SPAWN:
                    health *= 4f;
                    size *= 2f;
                    creepsInWay/= 4;
                    break;
                case HEALING:
                    break;
            }

            list.add(new CreepType(specification.getName(), speed, size, health,
                    specification.getTreasure(), creepsInWay, distanceBetweenCreeps, ability));
        }

        Map<CreepName, CreepType> map = new HashMap<CreepName, CreepType>();
        for (CreepType creepType : list) {
            map.put(creepType.getName(), creepType);
        }

        return map;

    }

    private static class CreepTypeSpecification {


        private final CreepName name;
        private final int health;
        private final Treasure treasure;
        private final CreepAbilityType abilityType;

        private CreepTypeSpecification(CreepName name, int health, Treasure treasure, CreepAbilityType abilityType) {
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
