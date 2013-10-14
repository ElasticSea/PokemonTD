package com.xkings.pokemontd.entity.creep;

import com.xkings.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/14/13.
 */

public class CreepTypeBuilder {

    private final List<CreepTypeSpecification> data = getData();

    private List<CreepTypeSpecification> getData() {
        List<CreepTypeSpecification> list = new ArrayList<CreepTypeSpecification>();
        list.add(new CreepTypeSpecification(list.size(), 10, new Treasure(10), CreepAbilityType.RESURRECT));
        return list;
    }


    public CreepDataStore build() {
        List<CreepType> list = new ArrayList<CreepType>();
        for (CreepTypeSpecification specification : data) {
            CreepAbilityType ability = specification.getAbilityType();
            int health = specification.getHealth();
            float speed = 1f;
            float size = 1f;
            int creepsInWay = 5;

            switch (ability) {
                case FAST:
                    speed *= 2;
                    health /= 2;
                    break;
                case SWARM:
                    speed /= 2;
                    size /= 2;
                    creepsInWay *= 4;
                    break;
                case RESURRECT:
                    break;
                case INVISIBLE:
                    break;
                case SPAWN:
                    health /= 1.5f;
                    break;
                case HEALING:
                    break;
            }

            list.add(new CreepType(CreepName.values()[specification.getID()], speed, size, health,
                    specification.getTreasure(), creepsInWay, ability));
        }
        return new CreepDataStore(list);
    }

    private static class CreepTypeSpecification {


        private final int id;
        private final int health;
        private final Treasure treasure;
        private final CreepAbilityType abilityType;

        private CreepTypeSpecification(int id, int health, Treasure treasure, CreepAbilityType abilityType) {
            this.id = id;
            this.health = health;
            this.treasure = treasure;
            this.abilityType = abilityType;
        }

        public int getID() {
            return id;
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
