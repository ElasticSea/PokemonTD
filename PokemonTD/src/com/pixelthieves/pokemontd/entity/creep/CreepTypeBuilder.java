package com.pixelthieves.pokemontd.entity.creep;

import com.pixelthieves.pokemontd.Difficulty;
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
    private static final int ELEMENT_LVL1_MULTIPLIER = 4;
    private static final int ELEMENT_LVL2_MULTIPLIER = 8;
    private static final int ELEMENT_LVL3_MULTIPLIER = 12;

    public static List<Specs> getNormal() {
        List<Specs> list = new ArrayList<Specs>();
        list.add(new Specs(CreepName.Acid, 38, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Toxic, 44, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Bite, 52, Treasure.fromGold(1), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Kakuna, 60, Treasure.fromGold(1), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Magnemite, 71, Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Burst, 82, Treasure.fromGold(2), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Crippling, 96, Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Confused, 113, Treasure.fromGold(2), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Dodrio, 132, Treasure.fromGold(2), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Doduo, 154, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Dracula, 181, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Earthquake, 211, Treasure.fromGold(3), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Enchanted, 253, Treasure.fromGold(3), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Epidemic, 284, Treasure.fromGold(4), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Erruption, 338, Treasure.fromGold(4), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Jigglypuff, 395, Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Wigglytpuff, 463, Treasure.fromGold(5), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Flamethrower, 541, Treasure.fromGold(6), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Galloping, 633, Treasure.fromGold(6), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Grind, 741, Treasure.fromGold(7), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Volcanic, 869, Treasure.fromGold(7), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Hypnotic, 1018, Treasure.fromGold(8), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Charged, 1194, Treasure.fromGold(9), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Chicken, 1194, Treasure.fromGold(10), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Ice, 1400, Treasure.fromGold(11), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Illusion, 1641, Treasure.fromGold(12), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Vampire, 1924, Treasure.fromGold(13), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Knockout, 2256, Treasure.fromGold(14), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Magic, 2645, Treasure.fromGold(16), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Magnificent, 3102, Treasure.fromGold(17), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Majestic, 3637, Treasure.fromGold(19), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Massive, 4273, Treasure.fromGold(21), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Metapod, 5021, Treasure.fromGold(23), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Needle, 5900, Treasure.fromGold(26), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Paras, 6932, Treasure.fromGold(28), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Noble, 8145, Treasure.fromGold(31), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Paralyze, 9570, Treasure.fromGold(34), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Pebble, 11245, Treasure.fromGold(37), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Photonic, 13213, Treasure.fromGold(41), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Pinch, 15525, Treasure.fromGold(45), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Poison, 18242, Treasure.fromGold(50), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Polar, 21526, Treasure.fromGold(55), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Pulverize, 25381, Treasure.fromGold(60), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Punch, 29972, Treasure.fromGold(66), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Pyro, 35367, Treasure.fromGold(73), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Venom, 41733, Treasure.fromGold(80), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Rocky, 54904, Treasure.fromGold(88), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Scratch, 49004, Treasure.fromGold(97), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Throttling, 68569, Treasure.fromGold(107), CreepAbilityType.SWARM));
        list.add(new Specs(CreepName.Slime, 80911, Treasure.fromGold(117), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Smash, 95475, Treasure.fromGold(129), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Sneaky, 106876, Treasure.fromGold(142), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Solar, 119320, Treasure.fromGold(156), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Beedrill, 131764, Treasure.fromGold(172), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Spell, 144208, Treasure.fromGold(189), CreepAbilityType.INVISIBLE));
        list.add(new Specs(CreepName.Thief, 156052, Treasure.fromGold(208), CreepAbilityType.HEALING));
        list.add(new Specs(CreepName.Hitmonchan, 169096, Treasure.fromGold(229), CreepAbilityType.RESURRECT));
        list.add(new Specs(CreepName.Stealth, 118540, Treasure.fromGold(252), CreepAbilityType.SPAWN));
        list.add(new Specs(CreepName.Sting, 193984, Treasure.fromGold(277), CreepAbilityType.FAST));
        list.add(new Specs(CreepName.Stomp, 206428, Treasure.fromGold(304), CreepAbilityType.NORMAL));
        list.add(new Specs(CreepName.Sunny, 218872, Treasure.fromGold(0), CreepAbilityType.BOSS));
        return list;
    }

    public static List<Specs> getElement() {
        List<Specs> list = new ArrayList<Specs>();
       /* list.add(new Specs(CreepName.Xatu, 10, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Mareep, 36, Treasure.fromPure(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Jumpluff, 75, Treasure.fromPure(1), CreepAbilityType.BOSS));
                                                                                                    */
        list.add(new Specs(CreepName.Sparkle, ELEMENT_LVL1_MULTIPLIER, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Burning, ELEMENT_LVL2_MULTIPLIER, Treasure.fromFire(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Inferno, ELEMENT_LVL3_MULTIPLIER, Treasure.fromFire(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Claw, ELEMENT_LVL1_MULTIPLIER, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Screech, ELEMENT_LVL2_MULTIPLIER, Treasure.fromLight(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Pidgeot, ELEMENT_LVL3_MULTIPLIER, Treasure.fromLight(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Splash, ELEMENT_LVL1_MULTIPLIER, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Ripple, ELEMENT_LVL2_MULTIPLIER, Treasure.fromWater(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Tsunami, ELEMENT_LVL3_MULTIPLIER, Treasure.fromWater(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Flower, ELEMENT_LVL1_MULTIPLIER, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Forest, ELEMENT_LVL2_MULTIPLIER, Treasure.fromNature(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Nature, ELEMENT_LVL3_MULTIPLIER, Treasure.fromNature(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Spooky, ELEMENT_LVL1_MULTIPLIER, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Haunted, ELEMENT_LVL2_MULTIPLIER, Treasure.fromDarkness(1), CreepAbilityType.BOSS));
        list.add(new Specs(CreepName.Nightmare, ELEMENT_LVL3_MULTIPLIER, Treasure.fromDarkness(1), CreepAbilityType.BOSS));

        list.add(new Specs(CreepName.Seel, 2955780, Treasure.fromSoul(1), CreepAbilityType.BOSS));
        return list;
    }

    /**
     * Creates predefined Map of {@link CreepType} templates.
     *
     * @param scale      scale of templates size, speed, etc..
     * @param data
     * @param difficulty
     * @return map of CreepType templates
     */
    public Map<CreepName, CreepType> build(float scale, List<Specs> data, Difficulty difficulty) {
        List<CreepType> list = new ArrayList<CreepType>();
        for (int id = 0; id < data.size(); id++) {
            Specs specification = data.get(id);
            CreepAbilityType ability = specification.getAbilityType();
            int health = (int) (specification.getHealth() * difficulty.getMultiplyer());
            float speed = scale;
            float size = scale;
            int creepsInWay = 32;
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
                    health *= 0.75f;
                    break;
                case SPAWN:
                    health *= 4f;
                    size *= 2f;
                    creepsInWay /= 8;
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
