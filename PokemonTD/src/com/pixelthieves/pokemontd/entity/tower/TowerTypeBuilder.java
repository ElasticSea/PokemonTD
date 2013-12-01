package com.pixelthieves.pokemontd.entity.tower;

import com.artemis.ComponentMapper;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.attack.AbilityComponent;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.component.attack.projectile.BuffAbility;
import com.pixelthieves.pokemontd.component.attack.projectile.HitAbility;
import com.pixelthieves.pokemontd.component.attack.projectile.SunbeamAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pixelthieves.pokemontd.component.attack.EffectName.*;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class TowerTypeBuilder {

    public static final float SIZE = 1.5f;
    public static final float S_RANGE = 2.5f;
    public static final float N_RANGE = 3f;
    public static final float L_RANGE = 4f;
    public static final float XL_RANGE = 6f;
    public static final float XXL_RANGE = 0.25f;
    public static final float BUFF_RANGE = XL_RANGE;
    public static final float XXL_SPEED = 0.25f;
    public static final float XL_SPEED = 0.5f;
    public static final float L_SPEED = 1f;
    public static final float N_SPEED = 2;
    public static final float S_SPEED = 4f;
    public static final float XS_SPEED = 8f;
    public static final float XXS_SPEED = 16f;
    public static final int L_SPLASH = 2;

    private List<TowerType> getData(float scale) {
        List<Specs> specs = new ArrayList<Specs>();
        // Shop
        specs.add(new Specs(TowerName.Shop, 0, null, 0, 0, 0, 0, null, Treasure.fromNone()));
        /// ######################  PURES
        // Basic
        specs.add(Specs.getSpecs(TowerName.Needle, -3, Normal, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale,0.1f), Treasure.fromNone()));
        specs.add(Specs.getSpecs(TowerName.Pinch, -2, Normal, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale,0.1f), Treasure.fromNone()));
        specs.add(Specs.getSpecs(TowerName.Sting, -1, Normal, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale,0.1f), Treasure.fromNone()));
        specs.add(Specs.getSpecs(TowerName.Scratch, -3, Splash, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, L_SPLASH,0.1f), Treasure.fromNone()));
        specs.add(Specs.getSpecs(TowerName.Bite, -2, Splash, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, L_SPLASH,0.1f), Treasure.fromNone()));
        specs.add(Specs.getSpecs(TowerName.Smash, -1, Splash, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, L_SPLASH,0.1f), Treasure.fromNone()));
        // blue
        specs.add(Specs.getSpecs(TowerName.Splash, 1, Normal, L_SPEED, N_RANGE, HitAbility.getSplash("waterdrop", scale, 2,0.2f), Treasure.fromWater(1)));
        specs.add(Specs.getSpecs(TowerName.Ripple, 2, Normal, L_SPEED, N_RANGE, HitAbility.getSplash("waterdrop", scale, 2,0.2f), Treasure.fromWater(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Tsunami, 6, Wave, L_SPEED, N_RANGE, .5f, HitAbility.getBubbleGrowing(scale), Treasure.fromWater(3).addSoul(1)));
        //red
        specs.add(Specs.getSpecs(TowerName.Sparkle, 1, Normal, XL_SPEED, N_RANGE, HitAbility.getNormal("fire", scale,0.2f), Treasure.fromFire(1)));
        specs.add(Specs.getSpecs(TowerName.Burning, 2, Normal, XL_SPEED, N_RANGE, HitAbility.getNormal("fire", scale,0.2f), Treasure.fromFire(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Inferno, 6, Burn, XL_SPEED, N_RANGE, .5f, HitAbility.getFireDot(scale), Treasure.fromFire(3).addSoul(1)));
        //green
        specs.add(Specs.getSpecs(TowerName.Flower, 1, Normal, S_SPEED, S_RANGE, HitAbility.getSplash("leaf", scale, 3,0.2f), Treasure.fromNature(1)));
        specs.add(Specs.getSpecs(TowerName.Forest, 2, Normal, S_SPEED, S_RANGE, HitAbility.getSplash("leaf", scale, 3,0.2f), Treasure.fromNature(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Nature, 6, Entangle, S_SPEED, S_RANGE, .5f, HitAbility.getNature(scale, 0.7f, 2f, 0.5f), Treasure.fromNature(3).addSoul(1)));
        //yellow
        specs.add(Specs.getSpecs(TowerName.Chicken, 1, Normal, L_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale,0.2f), Treasure.fromLight(1)));
        specs.add(Specs.getSpecs(TowerName.Screech, 2, Normal, L_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale,0.2f), Treasure.fromLight(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Claw, 6, Peck, L_SPEED, S_RANGE, 0.75f, HitAbility.getClaw(scale, 0.3f, 2), Treasure.fromLight(3).addSoul(1)));
        //purple
        specs.add(Specs.getSpecs(TowerName.Spooky, 1, Normal, N_SPEED, XL_RANGE, HitAbility.getNormal("slime", scale,0.2f), Treasure.fromDarkness(1)));
        specs.add(Specs.getSpecs(TowerName.Haunted, 2, Normal, N_SPEED, XL_RANGE, HitAbility.getNormal("slime", scale,0.2f), Treasure.fromDarkness(2)));
        specs.add(Specs.getSpecsFromNone(TowerName.Nightmare, 6, Terrify, N_SPEED, XL_RANGE, HitAbility.getTemLifeSteal(scale, 5f), Treasure.fromDarkness(3).addSoul(1)));

        /// ######################  TWO ELEMENT
        specs.add(Specs.getSpecsFromDamage(TowerName.Noble, 2, Haste, 45, BUFF_RANGE, 1.25f, BuffAbility.getSpeed(20), Treasure.fromWater(1).addFire(1)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Majestic, 4, Haste, 45, BUFF_RANGE, 1.5f, BuffAbility.getSpeed(20), Treasure.fromWater(2).addFire(2)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Magnificent, 5, Haste, 45, BUFF_RANGE, 2f, BuffAbility.getSpeed(20), Treasure.fromWater(3).addFire(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Sunny, 2, Sunbeam, S_SPEED, L_RANGE, .33f, new SunbeamAbility(0.2f, L_RANGE), Treasure.fromWater(1).addNature(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Solar, 4, Sunbeam, S_SPEED, L_RANGE, .33f, new SunbeamAbility(0.7f, L_RANGE * 1.5f), Treasure.fromWater(2).addNature(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Photonic, 5, Sunbeam, S_SPEED, L_RANGE, .33f, new SunbeamAbility(1.7f, L_RANGE * 2), Treasure.fromWater(3).addNature(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Poison, 2, Weaken, N_SPEED, N_RANGE, .33f, HitAbility.getPoison(scale, 0.2f, 1, 0.1f), Treasure.fromWater(1).addLight(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Toxic, 4, Weaken, N_SPEED, N_RANGE, .33f, HitAbility.getPoison(scale, 0.5f, 1, 0.15f), Treasure.fromWater(2).addLight(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Venom, 5, Weaken, N_SPEED, N_RANGE, .33f, HitAbility.getPoison(scale, 0.8f, 1, 0.2f), Treasure.fromWater(3).addLight(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Ice, 2, Freeze, N_SPEED, N_RANGE, .2f, HitAbility.getSlow("bullet", "ice", scale, 0f, 0.2f, 1, 1f), Treasure.fromWater(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Freezing, 4, Freeze, N_SPEED, N_RANGE, .2f, HitAbility.getSlow("bullet", "ice", scale, 1f, 0.3f, 1.5f, 1f), Treasure.fromWater(2).addDarkness(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Polar, 5, Freeze, N_SPEED, N_RANGE, .2f, HitAbility.getSlow("bullet", "ice", scale, 2f, 0.7f, 2, 1f), Treasure.fromWater(3).addDarkness(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Burst, 2, Incinerate, XXL_SPEED, N_RANGE, .5f, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(1).addNature(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Pyro, 4, Incinerate, XXL_SPEED, N_RANGE, .5f, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(2).addNature(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Flamethrower, 5, Incinerate, XXL_SPEED, N_RANGE, .5f, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(3).addNature(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Punch, 2, Stomp, N_SPEED, N_RANGE, .5f, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.1f, 1, 1f), Treasure.fromFire(1).addLight(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Takedown, 4, Stomp, N_SPEED, N_RANGE, .5f, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.2f, 2, 1f), Treasure.fromFire(2).addLight(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Knockout, 5, Stomp, N_SPEED, N_RANGE, .5f, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.3f, 3, 1f), Treasure.fromFire(3).addLight(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Sneaky, 2, Steal, N_SPEED, N_RANGE, .66f, HitAbility.getMoney(scale, 1.5f), Treasure.fromFire(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Stealth, 4, Steal, N_SPEED, N_RANGE, .66f, HitAbility.getMoney(scale, 1.75f), Treasure.fromFire(2).addDarkness(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Thief, 5, Steal, N_SPEED, N_RANGE, .66f, HitAbility.getMoney(scale, 2f), Treasure.fromFire(3).addDarkness(3)));

        specs.add(Specs.getSpecs(TowerName.Pebble, 2, Boulder, S_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 2,0.25f), Treasure.fromNature(1).addLight(1)));
        specs.add(Specs.getSpecs(TowerName.Rocky, 4, Boulder, S_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 3,0.25f), Treasure.fromNature(2).addLight(2)));
        specs.add(Specs.getSpecs(TowerName.Massive, 5, Boulder, XS_SPEED, S_RANGE, HitAbility.getSplash("rock", scale, 5,0.25f), Treasure.fromNature(3).addLight(3)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Dizzy, 2, Impair, N_SPEED, N_RANGE, .33f, HitAbility.getSlow("bullet", "orb", scale, 0, 0.5f, 1, 0.5f), Treasure.fromNature(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Paralyze, 4, Impair, N_SPEED, N_RANGE, .33f, HitAbility.getSlow("bullet", "orb", scale, 0, 0.7f, 2, 0.75f), Treasure.fromNature(2).addDarkness(2)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Crippling, 5, Impair, N_SPEED, N_RANGE, .33f, HitAbility.getSlow("bullet", "orb", scale, 0, 0.9f, 4, 0.85f), Treasure.fromNature(3).addDarkness(3)));

        specs.add(Specs.getSpecsFromDamage(TowerName.Spell, 2, Charm, 20, BUFF_RANGE, 1.25f, BuffAbility.getDamage(20), Treasure.fromWater(1).addFire(1)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Enchanted, 4, Charm, 20, BUFF_RANGE, 1.5f, BuffAbility.getDamage(20), Treasure.fromWater(2).addFire(2)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Magic, 5, Charm, 20, BUFF_RANGE, 2f, BuffAbility.getDamage(20), Treasure.fromWater(3).addFire(3)));


        /// ######################  TREE ELEMENT
        specs.add(Specs.getSpecs(TowerName.Grind, 3, Shatter, XS_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 3f,0.25f), Treasure.fromWater(1).addFire(1).addNature(1)));
        specs.add(Specs.getSpecs(TowerName.Pulverize, 5, Shatter, XXS_SPEED, S_RANGE, HitAbility.getSplash("rock", scale, 6f,0.25f), Treasure.fromWater(1).addFire(1).addNature(1)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Throttling, 3, Blaze, XXL_SPEED, S_RANGE, .5f, HitAbility.getBubble("fire", scale), Treasure.fromWater(1).addFire(1).addLight(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Galloping, 5, Blaze, XXL_SPEED, S_RANGE, .5f, HitAbility.getBubble("fire", scale), Treasure.fromWater(2).addFire(2).addLight(2)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Vampire, 3, LifeSteal, N_SPEED, N_RANGE, .66f, HitAbility.getLife(scale, 1f, 0.5f), Treasure.fromWater(1).addNature(1).addLight(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Dracula, 5, LifeSteal, N_SPEED, N_RANGE, .66f, HitAbility.getLife(scale, 1f, 1f), Treasure.fromWater(2).addNature(2).addLight(2)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Disease, 3, Infect, XS_SPEED, N_RANGE, .33f, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Epidemic, 5, Infect, XL_SPEED, L_RANGE, .33f, HitAbility.getAuraDmg(scale, L_RANGE), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(Specs.getSpecs(TowerName.Confused, 3, Normal, L_SPEED, N_RANGE, HitAbility.getDumbClaw(scale, 0f, 0), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Supersonic, 5, SoundWave, L_SPEED, N_RANGE, 0.5f, HitAbility.getBubble("bubble", scale), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(Specs.getSpecs(TowerName.Charged, 3, Thunderbolt, S_SPEED, XL_RANGE, HitAbility.getCharge(scale), Treasure.fromFire(1).addNature(1).addLight(1)));
        specs.add(Specs.getSpecs(TowerName.Supercharged, 5, Thunderbolt, XS_SPEED, XXL_RANGE, HitAbility.getCharge(scale * 2), Treasure.fromFire(2).addNature(2).addLight(2)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Stomp, 3, Quake, N_SPEED, N_RANGE, .33f, HitAbility.getSlow("rock", "", scale, 0, 1f, 0.5f, 0.8f), Treasure.fromFire(1).addNature(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Earthquake, 5, Quake, S_SPEED, N_RANGE, .33f, HitAbility.getSlow("rock", "", scale, 0, 1f, 1.5f, 1), Treasure.fromFire(2).addNature(2).addDarkness(2)));

        specs.add(Specs.getSpecsFromMultiplier(TowerName.Erruption, 3, Magma, XS_SPEED, N_RANGE, .33f, HitAbility.getVolcano(scale, N_RANGE, 4, 0.25f), Treasure.fromFire(1).addLight(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Volcanic, 5, Magma, XXS_SPEED, S_RANGE, .33f, HitAbility.getVolcano(scale, S_RANGE, 16, 0.25f), Treasure.fromFire(2).addLight(2).addDarkness(2)));

        specs.add(Specs.getSpecsFromNone(TowerName.Hypnotic, 3, Puzzle, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale, 1, 0.02f), Treasure.fromNature(1).addLight(1).addDarkness(1)));
        specs.add(Specs.getSpecsFromNone(TowerName.Illusion, 5, Puzzle, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale, 5, 0.03f), Treasure.fromNature(2).addLight(2).addDarkness(2)));

        List<TowerType> data = new ArrayList<TowerType>();
        for (Specs specification : specs) {
            data.add(createTowerType(scale, specification));
        }
        return data;
    }

    private TowerType createTowerType(float scale, Specs specs) {
        return new TowerType(specs.name, specs.effectName, SIZE * scale, specs.speed, specs.damage, specs.range * scale, specs.attackComponent, specs.treasure);
    }


    private static class Specs {
        private final TowerName name;
        private final float damage;
        private final float speed;
        private final float range;
        private final AbilityComponent attackComponent;
        private final Treasure treasure;
        private final EffectName effectName;

        public static Specs getSpecsFromDamage(TowerName name, int level, EffectName effectName, float speed, float range, float damage, AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, damage, 1, attackComponent, treasure);
        }

        public static Specs getSpecsFromNone(TowerName name, int level, EffectName effectName, float speed, float range, AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, 0, 1, attackComponent, treasure);
        }

        public static Specs getSpecsFromMultiplier(TowerName name, int level, EffectName effectName, float speed, float range, float multiplier, AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, computeDamage(getTotalPrice(level), level, speed, range, attackComponent instanceof HitAbility ? ((HitAbility) attackComponent).getAoe() : 0), multiplier, attackComponent, treasure);
        }

        private static int getTotalPrice(int level) {
            int totalPrice = 0;
            Integer price;
            while ((price = goldMap.get(level--)) != null) {
                totalPrice += price;
            }
            return totalPrice;
        }

        public static Specs getSpecs(TowerName name, int level, EffectName effectName, float speed, float range, AbilityComponent attackComponent, Treasure treasure) {
            return getSpecsFromMultiplier(name, level, effectName, speed, range, 1, attackComponent, treasure);
        }

        private Specs(TowerName name, int level, EffectName effectName, float speed, float range, float damage, float multiplier, AbilityComponent attackComponent, Treasure treasure) {
            this.name = name;
            this.effectName = effectName;
            this.damage = damage * multiplier;
            this.speed = speed;
            this.range = range;
            this.attackComponent = attackComponent;
            this.treasure = treasure;
            this.treasure.setGold(goldMap.get(level));
        }

        private static int computeDamage(int price, int level, float speed, float range, float splash) {
            float preciseDamage = price * damageMultiplierMap.get(level) * speedMap.get(speed) * rangeMap.get(range) / splashMap.get(splash);
            float decimalPart = preciseDamage - (int) preciseDamage;
            return decimalPart != 0f ? (int) (preciseDamage) + 1 : (int) preciseDamage;
        }

    }

    public Map<TowerName, TowerType> build(float scale) {
        Map<TowerName, TowerType> map = new HashMap<TowerName, TowerType>();
        for (TowerType towerType : getData(scale)) {
            map.put(towerType.getName(), towerType);
        }
        return map;
    }


    static Map<Float, Float> rangeMap = new HashMap<Float, Float>();

    {
        rangeMap.put(0f, 0f);
        rangeMap.put(XXL_RANGE, 0.25f);
        rangeMap.put(XL_RANGE, 0.5f);
        rangeMap.put(L_RANGE, 0.5f);
        rangeMap.put(N_RANGE, 1f);
        rangeMap.put(S_RANGE, 1.5f);
    }

    static Map<Float, Float> speedMap = new HashMap<Float, Float>();

    {
        speedMap.put(0f, 0f);
        speedMap.put(XXS_SPEED, 8f);
        speedMap.put(XS_SPEED, 4f);
        speedMap.put(S_SPEED, 2f);
        speedMap.put(N_SPEED, 1f);
        speedMap.put(L_SPEED, 0.5f);
        speedMap.put(XL_SPEED, 0.25f);
        speedMap.put(XXL_SPEED, 0.125f);
    }

    static Map<Float, Float> splashMap = new HashMap<Float, Float>();

    {
        splashMap.put(0f, 1f);
        splashMap.put(100f, 2.5f);
        splashMap.put(200f, 7f);
        splashMap.put(250f, 8f);
        splashMap.put(300f, 10f);
        splashMap.put(400f, 16f);
        splashMap.put(500f, 20f);
        splashMap.put(600f, 25f);
    }

    static Map<Integer, Float> damageMultiplierMap = new HashMap<Integer, Float>();

    {
        damageMultiplierMap.put(-3, 2.5f);
        damageMultiplierMap.put(-2, 2.7f);
        damageMultiplierMap.put(-1, 2.8421f);
        damageMultiplierMap.put(0, 0f);
        damageMultiplierMap.put(1, 2.5f);
        damageMultiplierMap.put(2, 2.8f);
        damageMultiplierMap.put(3, 3.1f);
        damageMultiplierMap.put(4, 3.4f);
        damageMultiplierMap.put(5, 3.6f);
        damageMultiplierMap.put(6, 5f);
    }

    static Map<Integer, Integer> goldMap = new HashMap<Integer, Integer>();

    {
        goldMap.put(-3, 7);
        goldMap.put(-2, 13);
        goldMap.put(-1, 37);
        goldMap.put(0, 0);
        goldMap.put(1, 125);
        goldMap.put(2, 375);
        goldMap.put(3, 1125);
        goldMap.put(4, 2500);
        goldMap.put(5, 5000);
        goldMap.put(6, 10000);
    }
}
