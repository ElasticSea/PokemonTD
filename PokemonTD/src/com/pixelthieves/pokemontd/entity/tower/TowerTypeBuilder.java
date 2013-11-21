package com.pixelthieves.pokemontd.entity.tower;

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
    public static final int INITIAL_BASE_DAMAGE = 15;
    public static final int INITIAL_GOLD = 10;

    private List<TowerType> getData(float scale) {
        List<Specs> specs = new ArrayList<Specs>();
        // Shop
        specs.add(new Specs(TowerName.Shop, 0, null, 0, 0, 0, 0,null, Treasure.fromNone()));
        /// ######################  PURES
        // Basic
        specs.add(Specs.getSpecsFromDamage(TowerName.Needle, 1, Normal, N_SPEED, N_RANGE, 18, HitAbility.getNormal("bullet", scale), Treasure.fromNone().addGold(7)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Pinch, 2, Normal, N_SPEED, N_RANGE, 54, HitAbility.getNormal("bullet", scale), Treasure.fromNone().addGold(13)));
        specs.add(Specs.getSpecsFromDamage(TowerName.Sting, 3, Normal, N_SPEED, N_RANGE, 162, HitAbility.getNormal("bullet", scale), Treasure.fromNone().addGold(37)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Scratch, 1, Splash, S_SPEED, N_RANGE, 1, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone().addGold(7)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Bite, 2, Splash, S_SPEED, N_RANGE, 1, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone().addGold(13)));
        specs.add(Specs.getSpecsFromMultiplier(TowerName.Smash, 3, Splash, S_SPEED, N_RANGE, 1, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone().addGold(37)));
        // blue
        specs.add(new Specs(TowerName.Splash, 1, Normal, N_SPEED, N_RANGE, 280, 1, HitAbility.getNormal("bullet", scale), Treasure.fromWater(1).addGold(125)));
        specs.add(new Specs(TowerName.Ripple, 2, Normal, N_SPEED, N_RANGE, 2124, 1, HitAbility.getNormal("bullet", scale), Treasure.fromWater(2).addGold(375)));
        specs.add(new Specs(TowerName.Tsunami, 6, Wave, N_SPEED, N_RANGE, 106614, 1, HitAbility.getBubbleGrowing(scale), Treasure.fromWater(3).addGold(10000)));
        //red
       /* public static Specs getSpecsFromMultiplier(TowerName name, int level, EffectName effectName, float speed, float range, float multiplier,
        AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, computeDamage(level, speed, range,
                    attackComponent instanceof HitAbility ? ((HitAbility) attackComponent).getAoe() : 1), multiplier,
                    attackComponent, treasure);
        }                                        */
        specs.add(new Specs(TowerName.Sparkle, 1, Normal, N_SPEED, S_RANGE, 252, 1, HitAbility.getNormal("bullet", scale), Treasure.fromFire(1).addGold(125)));
        specs.add(new Specs(TowerName.Burning, 2, Normal, L_SPEED, S_RANGE, 1153, 1, HitAbility.getNormal("bullet", scale), Treasure.fromFire(2).addGold(375)));
        specs.add(new Specs(TowerName.Inferno, 6, Burn, XL_SPEED, S_RANGE, 78346, 1, HitAbility.getFireDot(scale), Treasure.fromFire(3).addGold(10000)));
        //green
        specs.add(new Specs(TowerName.Flower, 1, Normal, XS_SPEED, S_RANGE, 514, 1, HitAbility.getNormal("bullet", scale), Treasure.fromNature(1).addGold(125)));
        specs.add(new Specs(TowerName.Forest, 2, Normal, XS_SPEED, S_RANGE, 7890, 1, HitAbility.getNormal("bullet", scale), Treasure.fromNature(2).addGold(375)));
        specs.add(new Specs(TowerName.Nature, 6, Entangle, XS_SPEED, S_RANGE, 181472, 1, HitAbility.getNature(scale, 0.7f, 2f, 0.5f), Treasure.fromNature(3).addGold(375)));
        //yellow
        specs.add(new Specs(TowerName.Chicken, 1, Normal, L_SPEED, N_RANGE, 220, 1, HitAbility.getNormal("bullet", scale), Treasure.fromLight(1).addGold(125)));
        specs.add(new Specs(TowerName.Screech, 2, Normal, L_SPEED, N_RANGE, 1914, 1, HitAbility.getNormal("bullet", scale), Treasure.fromLight(2).addGold(375)));
        specs.add(new Specs(TowerName.Claw, 6, Peck, L_SPEED, N_RANGE, 84148, 1, HitAbility.getClaw(scale, 0.3f, 2), Treasure.fromLight(3).addGold(10000)));
        //purple
        specs.add(new Specs(TowerName.Spooky, 1, Normal, N_SPEED, XL_RANGE, 239, 1, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(1).addGold(125)));
        specs.add(new Specs(TowerName.Haunted, 2, Normal, N_SPEED, XL_RANGE, 1744, 1, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(2).addGold(375)));
        specs.add(new Specs(TowerName.Nightmare, 6, Terrify, N_SPEED, XL_RANGE, 0.3f, 1, HitAbility.getTemLifeSteal(scale, 5f), Treasure.fromDarkness(3).addGold(10000)));
        /// ######################  TWO ELEMENT
        specs.add(new Specs(TowerName.Noble, 2, Haste, 45, BUFF_RANGE, 1.25f, 1, BuffAbility.getSpeed(20), Treasure.fromWater(1).addFire(1).addGold(375)));
        specs.add(new Specs(TowerName.Majestic, 4, Haste, 45, BUFF_RANGE, 1.5f, 1, BuffAbility.getSpeed(20), Treasure.fromWater(2).addFire(2).addGold(3500)));
        specs.add(new Specs(TowerName.Magnificent, 5, Haste, 45, BUFF_RANGE, 2f, 1, BuffAbility.getSpeed(20), Treasure.fromWater(3).addFire(3).addGold(10000)));

        specs.add(new Specs(TowerName.Sunny, 2, Sunbeam, XS_SPEED, L_RANGE, 574, 1, new SunbeamAbility(0.2f, L_RANGE), Treasure.fromWater(1).addNature(1).addGold(375)));
        specs.add(new Specs(TowerName.Solar, 4, Sunbeam, XS_SPEED, L_RANGE, 2817, 1, new SunbeamAbility(0.7f, L_RANGE * 1.5f), Treasure.fromWater(2).addNature(2).addGold(3500)));
        specs.add(new Specs(TowerName.Photonic, 5, Sunbeam, XS_SPEED, L_RANGE, 18, 1, new SunbeamAbility(1.7f, L_RANGE * 2), Treasure.fromWater(3).addNature(3).addGold(10000)));

        specs.add(new Specs(TowerName.Poison, 2, Weaken, N_SPEED, N_RANGE, 411, 1, HitAbility.getPoison(scale, 0.2f, 1, 0.1f), Treasure.fromWater(1).addLight(1).addGold(375)));
        specs.add(new Specs(TowerName.Toxic, 4, Weaken, N_SPEED, N_RANGE, 2414, 1, HitAbility.getPoison(scale, 0.5f, 1, 0.15f), Treasure.fromWater(2).addLight(2).addGold(3500)));
        specs.add(new Specs(TowerName.Venom, 5, Weaken, N_SPEED, N_RANGE, 24917, 1, HitAbility.getPoison(scale, 0.8f, 1, 0.2f), Treasure.fromWater(3).addLight(3).addGold(10000)));

        specs.add(new Specs(TowerName.Ice, 2, Freeze, N_SPEED, N_RANGE, 378, 1, HitAbility.getSlow("bullet", "ice", scale, 0f, 0.2f, 1, 1f), Treasure.fromWater(1).addDarkness(1).addGold(375)));
        specs.add(new Specs(TowerName.Freezing, 4, Freeze, N_SPEED, N_RANGE, 1978, 1, HitAbility.getSlow("bullet", "ice", scale, 1f, 0.3f, 1.5f, 1f), Treasure.fromWater(2).addDarkness(2).addGold(3500)));
        specs.add(new Specs(TowerName.Polar, 5, Freeze, N_SPEED, N_RANGE, 25787, 1, HitAbility.getSlow("bullet", "ice", scale, 2f, 0.7f, 2, 1f), Treasure.fromWater(3).addDarkness(3).addGold(10000)));

        specs.add(new Specs(TowerName.Burst, 2, Incinerate, XXL_SPEED, N_RANGE, 277, 1, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(1).addNature(1).addGold(375)));
        specs.add(new Specs(TowerName.Pyro, 4, Incinerate, XXL_SPEED, N_RANGE, 1978, 1, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(2).addNature(2).addGold(3500)));
        specs.add(new Specs(TowerName.Flamethrower, 5, Incinerate, XXL_SPEED, N_RANGE, 1, 21402, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(3).addNature(3).addGold(10000)));

        specs.add(new Specs(TowerName.Punch, 2, Stomp, N_SPEED, N_RANGE, 414, 1, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.1f, 1, 1f), Treasure.fromFire(1).addLight(1).addGold(375)));
        specs.add(new Specs(TowerName.Takedown, 4, Stomp, N_SPEED, N_RANGE, 2013, 1, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.2f, 2, 1f), Treasure.fromFire(2).addLight(2).addGold(3500)));
        specs.add(new Specs(TowerName.Knockout, 5, Stomp, N_SPEED, N_RANGE, 25811, 1, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.3f, 3, 1f), Treasure.fromFire(3).addLight(3).addGold(10000)));

        specs.add(new Specs(TowerName.Sneaky, 2, Steal, N_SPEED, N_RANGE, 402, 1, HitAbility.getMoney(scale, 1.5f), Treasure.fromFire(1).addDarkness(1).addGold(375)));
        specs.add(new Specs(TowerName.Stealth, 4, Steal, N_SPEED, N_RANGE, 2147, 1, HitAbility.getMoney(scale, 1.75f), Treasure.fromFire(2).addDarkness(2).addGold(3500)));
        specs.add(new Specs(TowerName.Thief, 5, Steal, N_SPEED, N_RANGE, 26784, 1, HitAbility.getMoney(scale, 2f), Treasure.fromFire(3).addDarkness(3).addGold(10000)));

        specs.add(new Specs(TowerName.Pebble, 2, Boulder, S_SPEED, N_RANGE, 398, 1, HitAbility.getSplash("rock", scale, 2), Treasure.fromNature(1).addLight(1).addGold(375)));
        specs.add(new Specs(TowerName.Rocky, 4, Boulder, S_SPEED, N_RANGE, 2487, 1, HitAbility.getSplash("rock", scale, 3), Treasure.fromNature(2).addLight(2).addGold(3500)));
        specs.add(new Specs(TowerName.Massive, 5, Boulder, XS_SPEED, S_RANGE, 31205, 1, HitAbility.getSplash("rock", scale, 5), Treasure.fromNature(3).addLight(3).addGold(10000)));

        specs.add(new Specs(TowerName.Dizzy, 2, Impair, N_SPEED, N_RANGE, 473, 1, HitAbility.getSlow("bullet", "orb", scale, 0, 0.5f, 1, 0.5f), Treasure.fromNature(1).addDarkness(1).addGold(375)));
        specs.add(new Specs(TowerName.Paralyze, 4, Impair, N_SPEED, N_RANGE, 2044, 1, HitAbility.getSlow("bullet", "orb", scale, 0, 0.7f, 2, 0.75f), Treasure.fromNature(2).addDarkness(2).addGold(3500)));
        specs.add(new Specs(TowerName.Crippling, 5, Impair, N_SPEED, N_RANGE, 26781, 1, HitAbility.getSlow("bullet", "orb", scale, 0, 0.9f, 4, 0.85f), Treasure.fromNature(3).addDarkness(3).addGold(10000)));

        specs.add(new Specs(TowerName.Spell, 2, Charm, 20, BUFF_RANGE, 1.25f, 1, BuffAbility.getDamage(20), Treasure.fromWater(1).addFire(1).addGold(375)));
        specs.add(new Specs(TowerName.Enchanted, 4, Charm, 20, BUFF_RANGE, 1.5f, 1, BuffAbility.getDamage(20), Treasure.fromWater(2).addFire(2).addGold(3500)));
        specs.add(new Specs(TowerName.Magic, 5, Charm, 20, BUFF_RANGE, 2f, 1, BuffAbility.getDamage(20), Treasure.fromWater(3).addFire(3).addGold(10000)));


        /// ######################  TREE ELEMENT
        specs.add(new Specs(TowerName.Grind, 3, Shatter, XS_SPEED, N_RANGE, 2178, 1, HitAbility.getSplash("rock", scale, 3f), Treasure.fromWater(1).addFire(1).addNature(1).addGold(1125)));
        specs.add(new Specs(TowerName.Pulverize, 5, Shatter, XXS_SPEED, S_RANGE, 8178, 1, HitAbility.getSplash("rock", scale, 6f), Treasure.fromWater(1).addFire(1).addNature(1).addGold(5000)));

        specs.add(new Specs(TowerName.Throttling, 3, Blaze, XXL_SPEED, S_RANGE, 1744, 1, HitAbility.getBubble("fire", scale), Treasure.fromWater(1).addFire(1).addLight(1).addGold(1125)));
        specs.add(new Specs(TowerName.Galloping, 5, Blaze, XXL_SPEED, S_RANGE, 7847, 1, HitAbility.getBubble("fire", scale), Treasure.fromWater(2).addFire(2).addLight(2).addGold(5000)));

        specs.add(new Specs(TowerName.Vampire, 3, LifeSteal, N_SPEED, N_RANGE, 2445, 1, HitAbility.getLife(scale, 1f, 0.5f), Treasure.fromWater(1).addNature(1).addLight(1).addGold(1125)));
        specs.add(new Specs(TowerName.Dracula, 5, LifeSteal, N_SPEED, N_RANGE, 9136, 1, HitAbility.getLife(scale, 1f, 1f), Treasure.fromWater(2).addNature(2).addLight(2).addGold(5000)));

        specs.add(new Specs(TowerName.Disease, 3, Infect, XS_SPEED, N_RANGE, 2141, 1, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromWater(1).addLight(1).addDarkness(1).addGold(1125)));
        specs.add(new Specs(TowerName.Epidemic, 5, Infect, XL_SPEED, L_RANGE, 7145, 1, HitAbility.getAuraDmg(scale, L_RANGE), Treasure.fromWater(2).addLight(2).addDarkness(2).addGold(5000)));

        specs.add(new Specs(TowerName.Confused, 3, Normal, L_SPEED, N_RANGE, 1887, 1, HitAbility.getDumbClaw(scale, 0f, 0), Treasure.fromWater(1).addLight(1).addDarkness(1).addGold(1125)));
        specs.add(new Specs(TowerName.Supersonic, 5, SoundWave, L_SPEED, N_RANGE, 7332, 1, HitAbility.getBubble("bubble", scale), Treasure.fromWater(2).addLight(2).addDarkness(2).addGold(5000)));

        specs.add(new Specs(TowerName.Charged, 3, Thunderbolt, S_SPEED, XL_RANGE, 2487, 1, HitAbility.getCharge(scale), Treasure.fromFire(1).addNature(1).addLight(1).addGold(1125)));
        specs.add(new Specs(TowerName.Supercharged, 5, Thunderbolt, XS_SPEED, XXL_RANGE, 9475, 1, HitAbility.getCharge(scale * 2), Treasure.fromFire(2).addNature(2).addLight(2).addGold(5000)));

        specs.add(new Specs(TowerName.Stomp, 3, Quake, N_SPEED, N_RANGE, 2017, 1, HitAbility.getSlow("rock", "", scale, 0, 1f, 0.5f, 0.8f), Treasure.fromFire(1).addNature(1).addDarkness(1).addGold(1125)));
        specs.add(new Specs(TowerName.Earthquake, 5, Quake, S_SPEED, N_RANGE, 8744, 1, HitAbility.getSlow("rock", "", scale, 0, 1f, 1.5f, 1), Treasure.fromFire(2).addNature(2).addDarkness(2).addGold(5000)));

        specs.add(new Specs(TowerName.Erruption, 3, Magma, XS_SPEED, N_RANGE, 3046, 1, HitAbility.getVolcano(scale, N_RANGE, 4, 0.25f), Treasure.fromFire(1).addLight(1).addDarkness(1).addGold(1125)));
        specs.add(new Specs(TowerName.Volcanic, 5, Magma, XXS_SPEED, S_RANGE, 7143, 1, HitAbility.getVolcano(scale, S_RANGE, 16, 0.25f), Treasure.fromFire(2).addLight(2).addDarkness(2).addGold(5000)));

        specs.add(new Specs(TowerName.Hypnotic, 3, Puzzle, N_SPEED, N_RANGE, 1987, 1, HitAbility.getChangeDirection(scale, 1, 0.02f), Treasure.fromNature(1).addLight(1).addDarkness(1).addGold(1125)));
        specs.add(new Specs(TowerName.Illusion, 5, Puzzle, N_SPEED, N_RANGE, 26281, 1, HitAbility.getChangeDirection(scale, 5, 0.03f), Treasure.fromNature(2).addLight(2).addDarkness(2).addGold(5000)));

        List<TowerType> data = new ArrayList<TowerType>();
        for (Specs specification : specs) {
            data.add(createTowerType(scale, specification));
        }
        return data;
    }

    private TowerType createTowerType(float scale, Specs specs) {
        // float damage = getDamage(getBaseDamage(INITIAL_BASE_DAMAGE, specs.level), specs.range, specs.speed);
        return new TowerType(specs.name, specs.effectName, SIZE * scale, specs.speed, specs.damage, specs.range * scale, specs.attackComponent, specs.treasure);
    }
  /*
    private float getDamage(float baseDamage, float range, float speed) {
        return Math.max((baseDamage + baseDamage * (float) scaleByRange(range) / koefficient) * speed, 0);
    }     */

    private static double scaleByRange(float range) {
        return (PI * pow(N_RANGE, 2) - (PI * pow(range, 2)));
    }
  /*
    public float getBaseDamage(float initialBaseDamage, int levels) {
        return (float) (initialBaseDamage * pow(3, levels - 1));
    }  */

    public int getGold(int initialGold, int levels) {
        return (int) (initialGold * pow(2, levels - 1));
    }


    private static class Specs {
        private final TowerName name;
        private final float damage;
        private final float speed;
        private final float range;
        private final AbilityComponent attackComponent;
        private final Treasure treasure;
        private final EffectName effectName;

        public static Specs getSpecsFromDamage(TowerName name, int level, EffectName effectName, float speed, float range, float damage,
                                               AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, damage, 1, attackComponent, treasure);
        }

        public static Specs getSpecsFromMultiplier(TowerName name, int level, EffectName effectName, float speed, float range, float multiplier,
                                                   AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, computeDamage(level, speed, range,
                    attackComponent instanceof HitAbility ? ((HitAbility) attackComponent).getAoe() : 1), multiplier,
                    attackComponent, treasure);
        }

        public static Specs getSpecs(TowerName name, int level, EffectName effectName, float speed, float range,
                                     AbilityComponent attackComponent, Treasure treasure) {
            return new Specs(name, level, effectName, speed, range, computeDamage(level, speed, range,
                    attackComponent instanceof HitAbility ? ((HitAbility) attackComponent).getAoe() : 1), 1,
                    attackComponent, treasure);
        }

        private Specs(TowerName name, int level, EffectName effectName, float speed, float range, float damage, float multiplier,
                      AbilityComponent attackComponent, Treasure treasure) {
            this.name = name;
            this.effectName = effectName;
            this.damage = damage * multiplier;
            this.speed = speed;
            this.range = range;
            this.attackComponent = attackComponent;
            this.treasure = treasure;
        }

        private static float computeDamage(int level, float speed, float range, float splash) {
            float aFloat = tier.get(level);
            float aFloat1 = rangeMap.get(range);
            float aFloat2 = splashMap.get(splash);
            float damage = aFloat * speed * aFloat1 / aFloat2;
            return damage;
        }

    }

    public Map<TowerName, TowerType> build(float scale) {
        Map<TowerName, TowerType> map = new HashMap<TowerName, TowerType>();
        for (TowerType towerType : getData(scale)) {
            map.put(towerType.getName(), towerType);
        }
        return map;
    }


    static Map<Float, Float> rangeMap = new HashMap<Float, Float>();{
        rangeMap.put(0f,0f);
        rangeMap.put(XXL_RANGE, 0.25f);
        rangeMap.put(XL_RANGE, 0.5f);
        rangeMap.put(L_RANGE, 0.5f);
        rangeMap.put(N_RANGE, 1f);
        rangeMap.put(S_RANGE, 1.5f);
    }

    static Map<Float, Float> splashMap = new HashMap<Float, Float>();{
        //splashMap.put(null, null);
        splashMap.put(2f, 7f);
        splashMap.put(1f, 1f);
        splashMap.put(200f,1f);
    }

    static Map<Integer, Float> tier = new HashMap<Integer, Float>();{
        tier.put(0,0f);
        tier.put(1, 2.5f);
        tier.put(2, 2.8f);
        tier.put(3, 3.1f);
        tier.put(4, 3.4f);
        tier.put(5, 3.6f);
        tier.put(6, 5f);
    }
}
