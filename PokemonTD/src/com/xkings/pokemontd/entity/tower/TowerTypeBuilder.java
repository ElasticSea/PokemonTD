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
    public static final float XSR = 1.25f;
    public static final float SR = 1.75f;
    public static final float NR = 2.00f;
    public static final float L_RANGE = 2.75f;
    public static final float XL_RANGE = 3.25f;
    public static final float BUFF_RANGE = 6f;
    public static final float XFS = 0.25f;
    public static final float FS = 0.5f;
    public static final float NS = 1;
    public static final float SS = 2f;
    public static final float XSS = 4f;
    public static final int INITIAL_BASE_DAMAGE = 15;
    public static final int INITIAL_GOLD = 10;
    private static final float koefficient = (float) (scaleByRange(XSR) - scaleByRange(XL_RANGE)) * 2f;

    private List<TowerType> getData(float scale) {
        List<Specs> specs = new ArrayList<Specs>();
        // Shop
        specs.add(new Specs(TowerName.Shop, 0, 0, 0, null, Treasure.fromNone()));
        /// ######################  PURES
        // Basic
        specs.add(new Specs(TowerName.Needle, 1, NS, NR, HitAbility.getNormal(scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Pinch, 2, NS, NR, HitAbility.getNormal(scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Sting, 3, NS, NR, HitAbility.getNormal(scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Scratch, 1, SS, NR, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Bite, 2, SS, NR, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Smash, 3, SS, NR, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        // blue
        specs.add(new Specs(TowerName.Splash, 4, NS, NR, HitAbility.getNormal(scale), Treasure.fromWater(1)));
        specs.add(new Specs(TowerName.Wave, 5, NS, NR, HitAbility.getBubble(scale), Treasure.fromWater(2)));
        specs.add(new Specs(TowerName.Tsunami, 10, NS, NR, HitAbility.getBubble(scale), Treasure.fromWater(3)));
        //red
        specs.add(new Specs(TowerName.Sparkle, 4, NS, SR, HitAbility.getFire(scale), Treasure.fromFire(1)));
        specs.add(new Specs(TowerName.Burning, 5, FS, SR, HitAbility.getFire(scale), Treasure.fromFire(2)));
        specs.add(new Specs(TowerName.Inferno, 10, XFS, SR, HitAbility.getFireDot(scale), Treasure.fromFire(3)));
        //green
        specs.add(new Specs(TowerName.Flower, 4, XSS, SR, HitAbility.getNature(scale, 0.2f, 1, 0.2f), Treasure.fromNature(1)));
        specs.add(new Specs(TowerName.Forest, 5, XSS, SR, HitAbility.getNature(scale, 0.3f, 1.2f, 0.3f), Treasure.fromNature(2)));
        specs.add(new Specs(TowerName.Nature, 10, XSS, SR, HitAbility.getNature(scale, 0.7f, 2f, 0.5f), Treasure.fromNature(3)));
        //yellow
        specs.add(new Specs(TowerName.Chicken, 4, FS, NR, HitAbility.getClaw(scale, 0f, 0), Treasure.fromLight(1)));
        specs.add(new Specs(TowerName.Screech, 5, FS, NR, HitAbility.getClaw(scale, 0.1f, 1), Treasure.fromLight(2)));
        specs.add(new Specs(TowerName.Claw, 10, FS, NR, HitAbility.getClaw(scale, 0.3f, 2), Treasure.fromLight(3)));
        //purple
        specs.add(new Specs(TowerName.Spooky, 4, NS, XL_RANGE, HitAbility.getNormal(scale), Treasure.fromDarkness(1)));
        specs.add(new Specs(TowerName.Haunted, 5, NS, XL_RANGE, HitAbility.getNormal(scale), Treasure.fromDarkness(2)));
        specs.add(new Specs(TowerName.Nightmare, 10, NS, XL_RANGE, HitAbility.getTemLifeSteal(scale, 0.3f, 5f), Treasure.fromDarkness(3)));
        ;
        /// ######################  TWO ELEMENT
        specs.add(new Specs(TowerName.Noble, 5, 45, BUFF_RANGE, BuffAbility.getSpeed(1.25f, 45), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Majestic, 7, 45, BUFF_RANGE, BuffAbility.getSpeed(1.5f, 45), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magnificent, 9, 45, BUFF_RANGE, BuffAbility.getSpeed(2f, 45), Treasure.fromWater(3).addFire(3)));

        specs.add(new Specs(TowerName.Sunny, 5, XSS, L_RANGE, new SunbeamAbility(0.2f, L_RANGE), Treasure.fromWater(1).addNature(1)));
        specs.add(new Specs(TowerName.Solar, 7, XSS, L_RANGE, new SunbeamAbility(0.7f, L_RANGE * 1.5f), Treasure.fromWater(2).addNature(2)));
        specs.add(new Specs(TowerName.Photonic, 9, XSS, L_RANGE, new SunbeamAbility(1.7f, L_RANGE * 2), Treasure.fromWater(3).addNature(3)));

        specs.add(new Specs(TowerName.Poison, 5, NS, NR, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(1).addLight(1)));
        specs.add(new Specs(TowerName.Toxic, 7, NS, NR, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(2).addLight(2)));
        specs.add(new Specs(TowerName.Venom, 9, NS, NR, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(3).addLight(3)));

        specs.add(new Specs(TowerName.Ice, 5, NS, NR, HitAbility.getSlow("ice", scale, 0f, 0.2f, 1, 1f), Treasure.fromWater(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Freezing, 7, NS, NR, HitAbility.getSlow("ice", scale, 1f, 0.3f, 1.5f, 1f), Treasure.fromWater(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Polar, 9, NS, NR, HitAbility.getSlow("ice", scale, 2f, 0.7f, 2, 1f), Treasure.fromWater(3).addDarkness(3)));

        // FIXME MISSING TOWERS (FLAMTHROWER...)
        specs.add(new Specs(TowerName.Sneaky, 5, NS, NR, HitAbility.getMoney(scale, 1.5f), Treasure.fromFire(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Stealth, 7, NS, NR, HitAbility.getMoney(scale, 1.75f), Treasure.fromFire(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Thief, 9, NS, NR, HitAbility.getMoney(scale, 2f), Treasure.fromFire(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Pebble, 5, SS, NR, HitAbility.getSplash("rock", scale, 2), Treasure.fromNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Rocky, 7, SS, SR, HitAbility.getSplash("rock", scale, 3), Treasure.fromNature(2).addLight(2)));
        specs.add(new Specs(TowerName.Massive, 9, XSS, XSR, HitAbility.getSplash("rock", scale, 5), Treasure.fromNature(3).addLight(3)));

        specs.add(new Specs(TowerName.Dizzy, 5, NS, NR, HitAbility.getSlow("orb",  scale, 0, 0.5f, 1, 0.5f), Treasure.fromNature(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Paralyze, 7, NS, NR, HitAbility.getSlow("orb",  scale, 0, 0.7f, 2, 0.75f), Treasure.fromNature(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Crippling, 9, NS, NR, HitAbility.getSlow("orb",  scale, 0, 0.9f, 4, 0.85f), Treasure.fromNature(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Spell, 5, 45, BUFF_RANGE, BuffAbility.getDamage(1.25f, 45), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Enchanted, 7, 45, BUFF_RANGE, BuffAbility.getDamage(1.5f, 45), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magic, 9, 45, BUFF_RANGE, BuffAbility.getDamage(2f, 45), Treasure.fromWater(3).addFire(3)));

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
        //  System.out.println(baseDamage * (float) scaleByRange(range) / koefficient);
        return (baseDamage + baseDamage * (float) scaleByRange(range) / koefficient) * speed;
    }

    private static double scaleByRange(float range) {
        return (PI * pow(NR, 2) - (PI * pow(range, 2)));
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
