package com.xkings.pokemontd.entity.creep;

import com.xkings.pokemontd.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/14/13.
 */

public class CreepTypeBuilder {

    private final List<CreepTypeSpecification> data = getData();

    public List<CreepTypeSpecification> getData() {
        List<CreepTypeSpecification> list = new ArrayList<CreepTypeSpecification>();
        list.add(new CreepTypeSpecification(list.size(), 10, new Treasure(10), CreepAbilityType.RESURRECT));
        return list;
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

    public static void build() {

    }
}
