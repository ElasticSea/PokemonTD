package com.pixelthieves.pokemontd.entity.creep;

import com.pixelthieves.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        list.add(new Specs(CreepName.Hoothoot, 38, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Ledian, 44, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Crobat, 52, Treasure.fromGold(1), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Lanturn, 60, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Quagsire, 71, Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Espeon, 82, Treasure.fromGold(2), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Forretress, 96, Treasure.fromGold(2), CreepAbilityType.NORMAL));   //vymyšlené životy
        list.add(new Specs(CreepName.Snubbull, 113, Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Corsola, 132, Treasure.fromGold(2), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Miltank, 154, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Entei, 181, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Blaziken, 211, Treasure.fromGold(3), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Wurmple, 253, Treasure.fromGold(3), CreepAbilityType.NORMAL));   //vymyšlené životy
        list.add(new Specs(CreepName.Beautifly, 284, Treasure.fromGold(4), CreepAbilityType.NORMAL));    //vymyšlené životy
        list.add(new Specs(CreepName.Nuzleaf, 338, Treasure.fromGold(4), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Pelipper, 395, Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Kirlia, 463, Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Breloom, 541, Treasure.fromGold(6), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Shedinja, 633, Treasure.fromGold(6), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Whismur, 741, Treasure.fromGold(7), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Loudred, 869, Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Exploud, 1018, Treasure.fromGold(8), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Delcatty, 1194, Treasure.fromGold(9), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Sableye, 1194, Treasure.fromGold(10), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Mawile, 1400, Treasure.fromGold(11), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Lairon, 1641, Treasure.fromGold(12), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Flygon, 1924, Treasure.fromGold(13), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Whiscash, 2256, Treasure.fromGold(14), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Claydol, 2645, Treasure.fromGold(16), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Lileep, 3102, Treasure.fromGold(17), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Feebas, 3637, Treasure.fromGold(19), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Kecleon, 4273, Treasure.fromGold(21), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Banette, 5021, Treasure.fromGold(23), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Duskull, 5900, Treasure.fromGold(26), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Tropius, 6932, Treasure.fromGold(28), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Huntail, 8145, Treasure.fromGold(31), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Kyogre, 9570, Treasure.fromGold(34), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Prinplup, 11245, Treasure.fromGold(37), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Wormadam, 13213, Treasure.fromGold(41), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Bronzong, 15525, Treasure.fromGold(45), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Yanmega, 18242, Treasure.fromGold(50), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Glaceon, 21526, Treasure.fromGold(55), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Mamoswine, 25381, Treasure.fromGold(60), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Palkia, 29972, Treasure.fromGold(66), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Regigigas, 35367, Treasure.fromGold(73), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Giratina, 41733, Treasure.fromGold(80), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Manaphy, 54904, Treasure.fromGold(88), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Darkrai, 49004, Treasure.fromGold(97), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Snivy, 68569, Treasure.fromGold(107), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Tepig, 80911, Treasure.fromGold(117), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Emboar, 95475, Treasure.fromGold(129), CreepAbilityType.INVISIBLE));    // a dále též vymyšlené
        list.add(new Specs(CreepName.Watchog, 106876, Treasure.fromGold(142), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Herdier, 119320, Treasure.fromGold(156), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Liepard, 131764, Treasure.fromGold(172), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Panpour, 144208, Treasure.fromGold(189), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Tirtouga, 156052, Treasure.fromGold(208), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Zorua, 169096, Treasure.fromGold(229), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Klinklang, 118540, Treasure.fromGold(252), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Lampent, 193984, Treasure.fromGold(277), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Druddigon, 206428, Treasure.fromGold(304), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Hydreigon, 218872, Treasure.fromGold(0), CreepAbilityType.BOSS));
        return list;


    }

    public static List<Specs> getElement() {
        List<Specs> list = new ArrayList<Specs>();
        list.add(new Specs(CreepName.Xatu, 10, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Mareep, 36, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Jumpluff, 75, Treasure.fromPure(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Flaaffy, 10, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Ampharos, 36, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Yanma, 75, Treasure.fromFire(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Bellossom, 10, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Politoed, 36, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Skiploom, 75, Treasure.fromLight(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Marill, 10, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Azumarill, 36, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Wooper, 75, Treasure.fromWater(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Sudowoodo, 10, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Sunkern, 36, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Sunflora, 75, Treasure.fromNature(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Umbreon, 10, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Aipom, 36, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Hoppip, 75, Treasure.fromDarkness(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Electivire, 2955780, Treasure.fromSoul(1), CreepAbilityType.BOSS));
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
            CreepAbilityType ability = specification.getAbilityType();
            int health = specification.getHealth();
            float speed = scale;
            float size = scale;
            int creepsInWay = 30;
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
                    health *= 0.75f;
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
                    //health *= creepsInWay;
                    size *= 3f;
                    creepsInWay = 1;
                    break;
            }

            list.add(new CreepType(id, specification.getName(), speed, size, health, specification.getTreasure(),
                    creepsInWay, distanceBetweenCreeps, ability));
        }

        Map<CreepName, CreepType> map = new LinkedHashMap<CreepName, CreepType>();
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
