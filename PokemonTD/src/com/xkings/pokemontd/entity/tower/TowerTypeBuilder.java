package com.xkings.pokemontd.entity.tower;

import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;
import com.xkings.pokemontd.component.attack.projectile.HitAbility;
import com.xkings.pokemontd.component.attack.projectile.SunbeamAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class TowerTypeBuilder {

    public static final float SIZE = 1.5f;
    public static final float XS_RANGE = 1.25f;
    public static final float S_RANGE = 1.75f;
    public static final float N_RANGE = 2.00f;
    public static final float L_RANGE = 2.75f;
    public static final float XL_RANGE = 3.25f;
    public static final float XXL_RANGE = 4.25f;
    public static final float XXXL_RANGE = 5.25f;
    public static final float BUFF_RANGE = 6f;
    public static final float XL_SPEED = 0.25f;
    public static final float L_SPEED = 0.5f;
    public static final float N_SPEED = 1;
    public static final float S_SPEED = 2f;
    public static final float XS_SPEED = 4f;
    public static final float XXS_SPEED = 8f;
    public static final int INITIAL_BASE_DAMAGE = 15;
    public static final int INITIAL_GOLD = 10;
    private static final float koefficient = (float) (scaleByRange(XS_RANGE) - scaleByRange(XXXL_RANGE)) * 2f;

    private List<TowerType> getData(float scale) {
        List<Specs> specs = new ArrayList<Specs>();
        // Shop
        specs.add(new Specs(TowerName.Shop, 0, 0, 0, null, Treasure.fromNone()));
        /// ######################  PURES
        // Basic
        specs.add(new Specs(TowerName.Needle, 1, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Pinch, 2, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Sting, 3, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Scratch, 1, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Bite, 2, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Smash, 3, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        // blue
        specs.add(new Specs(TowerName.Splash, 4, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromWater(1)));
        specs.add(new Specs(TowerName.Wave, 5, N_SPEED, N_RANGE, HitAbility.getBubble(scale), Treasure.fromWater(2)));
        specs.add(new Specs(TowerName.Tsunami, 10, N_SPEED, N_RANGE, HitAbility.getBubbleGrowing(scale), Treasure.fromWater(3)));
        //red
        specs.add(new Specs(TowerName.Sparkle, 4, N_SPEED, S_RANGE, HitAbility.getFire(scale), Treasure.fromFire(1)));
        specs.add(new Specs(TowerName.Burning, 5, L_SPEED, S_RANGE, HitAbility.getFire(scale), Treasure.fromFire(2)));
        specs.add(new Specs(TowerName.Inferno, 10, XL_SPEED, S_RANGE, HitAbility.getFireDot(scale), Treasure.fromFire(3)));
        //green
        specs.add(new Specs(TowerName.Flower, 4, XS_SPEED, S_RANGE, HitAbility.getNature(scale, 0.2f, 1, 0.2f), Treasure.fromNature(1)));
        specs.add(new Specs(TowerName.Forest, 5, XS_SPEED, S_RANGE, HitAbility.getNature(scale, 0.3f, 1.2f, 0.3f), Treasure.fromNature(2)));
        specs.add(new Specs(TowerName.Nature, 10, XS_SPEED, S_RANGE, HitAbility.getNature(scale, 0.7f, 2f, 0.5f), Treasure.fromNature(3)));
        //yellow
        specs.add(new Specs(TowerName.Chicken, 4, L_SPEED, N_RANGE, HitAbility.getClaw(scale, 0f, 0), Treasure.fromLight(1)));
        specs.add(new Specs(TowerName.Screech, 5, L_SPEED, N_RANGE, HitAbility.getClaw(scale, 0.1f, 1), Treasure.fromLight(2)));
        specs.add(new Specs(TowerName.Claw, 10, L_SPEED, N_RANGE, HitAbility.getClaw(scale, 0.3f, 2), Treasure.fromLight(3)));
        //purple
        specs.add(new Specs(TowerName.Spooky, 4, N_SPEED, XL_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(1)));
        specs.add(new Specs(TowerName.Haunted, 5, N_SPEED, XL_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(2)));
        specs.add(new Specs(TowerName.Nightmare, 10, N_SPEED, XL_RANGE, HitAbility.getTemLifeSteal(scale, 0.3f, 5f), Treasure.fromDarkness(3)));
        ;
        /// ######################  TWO ELEMENT
        specs.add(new Specs(TowerName.Noble, 5, 45, BUFF_RANGE, BuffAbility.getSpeed(1.25f, 45), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Majestic, 7, 45, BUFF_RANGE, BuffAbility.getSpeed(1.5f, 45), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magnificent, 9, 45, BUFF_RANGE, BuffAbility.getSpeed(2f, 45), Treasure.fromWater(3).addFire(3)));

        specs.add(new Specs(TowerName.Sunny, 5, XS_SPEED, L_RANGE, new SunbeamAbility(0.2f, L_RANGE), Treasure.fromWater(1).addNature(1)));
        specs.add(new Specs(TowerName.Solar, 7, XS_SPEED, L_RANGE, new SunbeamAbility(0.7f, L_RANGE * 1.5f), Treasure.fromWater(2).addNature(2)));
        specs.add(new Specs(TowerName.Photonic, 9, XS_SPEED, L_RANGE, new SunbeamAbility(1.7f, L_RANGE * 2), Treasure.fromWater(3).addNature(3)));

        specs.add(new Specs(TowerName.Poison, 5, N_SPEED, N_RANGE, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(1).addLight(1)));
        specs.add(new Specs(TowerName.Toxic, 7, N_SPEED, N_RANGE, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(2).addLight(2)));
        specs.add(new Specs(TowerName.Venom, 9, N_SPEED, N_RANGE, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(3).addLight(3)));

        specs.add(new Specs(TowerName.Ice, 5, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 0f, 0.2f, 1, 1f), Treasure.fromWater(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Freezing, 7, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 1f, 0.3f, 1.5f, 1f), Treasure.fromWater(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Polar, 9, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 2f, 0.7f, 2, 1f), Treasure.fromWater(3).addDarkness(3)));

        // FIXME MISSING TOWERS (FLAMTHROWER...)
        specs.add(new Specs(TowerName.Punch, 5, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.1f, 1, 1f), Treasure.fromFire(1).addLight(1)));
        specs.add(new Specs(TowerName.Takedown, 7, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.2f, 2, 1f), Treasure.fromFire(2).addLight(2)));
        specs.add(new Specs(TowerName.Knockout, 9, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.3f, 3, 1f), Treasure.fromFire(3).addLight(3)));

        specs.add(new Specs(TowerName.Sneaky, 5, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 1.5f), Treasure.fromFire(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Stealth, 7, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 1.75f), Treasure.fromFire(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Thief, 9, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 2f), Treasure.fromFire(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Pebble, 5, S_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 2), Treasure.fromNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Rocky, 7, S_SPEED, S_RANGE, HitAbility.getSplash("rock", scale, 3), Treasure.fromNature(2).addLight(2)));
        specs.add(new Specs(TowerName.Massive, 9, XS_SPEED, XS_RANGE, HitAbility.getSplash("rock", scale, 5), Treasure.fromNature(3).addLight(3)));

        specs.add(new Specs(TowerName.Dizzy, 5, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.5f, 1, 0.5f), Treasure.fromNature(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Paralyze, 7, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.7f, 2, 0.75f), Treasure.fromNature(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Crippling, 9, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.9f, 4, 0.85f), Treasure.fromNature(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Spell, 5, 45, BUFF_RANGE, BuffAbility.getDamage(1.25f, 45), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Enchanted, 7, 45, BUFF_RANGE, BuffAbility.getDamage(1.5f, 45), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magic, 9, 45, BUFF_RANGE, BuffAbility.getDamage(2f, 45), Treasure.fromWater(3).addFire(3)));


        /// ######################  TREE ELEMENT
        specs.add(new Specs(TowerName.Grind, 6, N_SPEED, XS_SPEED, HitAbility.getSplash("rock",scale, 3f), Treasure.fromWater(1).addFire(1).addNature(1)));
        specs.add(new Specs(TowerName.Pulverize, 9, N_SPEED, XXS_SPEED, HitAbility.getSplash("rock",scale, 6f), Treasure.fromWater(1).addFire(1).addNature(1)));

        specs.add(new Specs(TowerName.Vampire, 6, N_SPEED, N_RANGE, HitAbility.getLife(scale, 1f, 0.5f), Treasure.fromWater(1).addNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Dracula, 9, N_SPEED, N_RANGE, HitAbility.getLife(scale, 1f, 1f), Treasure.fromWater(2).addNature(2).addLight(2)));

        specs.add(new Specs(TowerName.Disease, 6, XS_SPEED, N_RANGE, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Epidemic, 9, XL_SPEED, L_RANGE, HitAbility.getAuraDmg(scale, L_RANGE), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Confused, 6, L_SPEED, N_RANGE, HitAbility.getDumbClaw(scale, 0f, 0), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Supersonic, 9, L_SPEED, N_RANGE, HitAbility.getBubble(scale), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Charged, 6, S_SPEED, XL_RANGE, HitAbility.getCharge(scale), Treasure.fromFire(1).addNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Supercharged, 9, XS_SPEED, XXXL_RANGE, HitAbility.getCharge(scale * 2), Treasure.fromFire(2).addNature(2).addLight(2)));

        specs.add(new Specs(TowerName.Stomp, 6, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "", scale, 0, 1f, 1, 1), Treasure.fromFire(1).addNature(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Earthquake, 9, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "", scale, 0, 1f, 3, 1), Treasure.fromFire(2).addNature(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Erruption, 6, XS_SPEED, N_RANGE, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromFire(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Volcanic, 9, XXS_SPEED, S_RANGE, HitAbility.getVolcano(scale, S_RANGE), Treasure.fromFire(2).addLight(2).addDarkness(2)));


        specs.add(new Specs(TowerName.Hypnotic, 6, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale, 1,0.1f), Treasure.fromNature(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Illusion,9, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale,5,0.1f), Treasure.fromNature(2).addLight(2).addDarkness(2)));

        List<TowerType> data = new ArrayList<TowerType>();
        for (Specs specification : specs) {
            data.add(createTowerType(scale, specification));
        }
        return data;
    }

    private TowerType createTowerType(float scale, Specs specs) {
        float damage = getDamage(getBaseDamage(INITIAL_BASE_DAMAGE, specs.level), specs.range, specs.speed);
        Treasure treasure = new Treasure(getGold(INITIAL_GOLD, specs.level));
        treasure.add(specs.treasure);
        return new TowerType(specs.name, SIZE * scale, specs.speed, damage, specs.range * scale, specs.attackComponent, treasure);
    }

    private float getDamage(float baseDamage, float range, float speed) {
        return Math.max((baseDamage + baseDamage * (float) scaleByRange(range) / koefficient) * speed, 0);
    }

    private static double scaleByRange(float range) {
        return (PI * pow(N_RANGE, 2) - (PI * pow(range, 2)));
    }

    public float getBaseDamage(float initialBaseDamage, int levels) {
        return (float) (initialBaseDamage * pow(3, levels - 1));
    }

    public int getGold(int initialGold, int levels) {
        return (int) (initialGold * pow(2, levels - 1));
    }


    private static class Specs {
        private final TowerName name;
        private final int level;
        private final float speed;
        private final float range;
        private final AbilityComponent attackComponent;
        private final Treasure treasure;

        private Specs(TowerName name, int level, float speed, float range, AbilityComponent attackComponent, Treasure treasure) {
            this.name = name;
            this.level = level;
            this.speed = speed;
            this.range = range;
            this.attackComponent = attackComponent;
            this.treasure = treasure;
        }
    }


    public Map<TowerName, TowerType> build(float scale) {
        Map<TowerName, TowerType> map = new HashMap<TowerName, TowerType>();
        for (TowerType towerType : getData(scale)) {
            map.put(towerType.getName(), towerType);
        }
        return map;
    }
}
