package com.xkings.pokemontd.entity.tower;

import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.EffectName;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;
import com.xkings.pokemontd.component.attack.projectile.HitAbility;
import com.xkings.pokemontd.component.attack.projectile.SunbeamAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static com.xkings.pokemontd.component.attack.EffectName.*;

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
    public static final float XXL_SPEED = 0.125f;
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
        specs.add(new Specs(TowerName.Shop, null, 0, 18, 0, 0, null, Treasure.fromNone()));
        /// ######################  PURES
        // Basic
        specs.add(new Specs(TowerName.Needle, null, 1, 12, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Pinch, null, 2, 60, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Sting, null, 3, 300, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Scratch, null, 1, 5, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Bite, null, 2, 30, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        specs.add(new Specs(TowerName.Smash, null, 3, 140, S_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromNone()));
        // blue
        specs.add(new Specs(TowerName.Splash, null, 4, 280, N_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromWater(1)));
        specs.add(new Specs(TowerName.Ripple, null, 5, 2124, N_SPEED, N_RANGE, HitAbility.getNormal("bubble", scale), Treasure.fromWater(2)));
        specs.add(new Specs(TowerName.Tsunami, Wave, 10, 106614, N_SPEED, N_RANGE, HitAbility.getBubbleGrowing(scale), Treasure.fromWater(3)));
        //red
        specs.add(new Specs(TowerName.Sparkle, null, 4, 252, N_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromFire(1)));
        specs.add(new Specs(TowerName.Burning, null, 5, 1153, L_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromFire(2)));
        specs.add(new Specs(TowerName.Inferno, Burn, 10, 78346, XL_SPEED, S_RANGE, HitAbility.getFireDot(scale), Treasure.fromFire(3)));
        //green
        specs.add(new Specs(TowerName.Flower, null, 4, 514, XS_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNature(1)));
        specs.add(new Specs(TowerName.Forest, null, 5, 7890, XS_SPEED, S_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromNature(2)));
        specs.add(new Specs(TowerName.Nature, Entangle, 10, 181472, XS_SPEED, S_RANGE, HitAbility.getNature(scale, 0.7f, 2f, 0.5f), Treasure.fromNature(3)));
        //yellow
        specs.add(new Specs(TowerName.Chicken, null, 4, 220, L_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromLight(1)));
        specs.add(new Specs(TowerName.Screech, null, 5, 1914, L_SPEED, N_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromLight(2)));
        specs.add(new Specs(TowerName.Claw, Peck, 10, 84148, L_SPEED, N_RANGE, HitAbility.getClaw(scale, 0.3f, 2), Treasure.fromLight(3)));
        //purple
        specs.add(new Specs(TowerName.Spooky, null, 4, 239, N_SPEED, XL_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(1)));
        specs.add(new Specs(TowerName.Haunted, null, 5, 1744, N_SPEED, XL_RANGE, HitAbility.getNormal("bullet", scale), Treasure.fromDarkness(2)));
        specs.add(new Specs(TowerName.Nightmare, Terrify, 10, 0.3f, N_SPEED, XL_RANGE, HitAbility.getTemLifeSteal(scale, 5f), Treasure.fromDarkness(3)));
        /// ######################  TWO ELEMENT
        specs.add(new Specs(TowerName.Noble, Haste, 5, 1.25f, 45, BUFF_RANGE, BuffAbility.getSpeed(20), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Majestic, Haste, 7, 1.5f, 45, BUFF_RANGE, BuffAbility.getSpeed( 20), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magnificent, Haste, 9, 2f, 45, BUFF_RANGE, BuffAbility.getSpeed( 20), Treasure.fromWater(3).addFire(3)));

        specs.add(new Specs(TowerName.Sunny, Sunbeam, 5, 574, XS_SPEED, L_RANGE, new SunbeamAbility(0.2f, L_RANGE), Treasure.fromWater(1).addNature(1)));
        specs.add(new Specs(TowerName.Solar, Sunbeam, 7, 2817, XS_SPEED, L_RANGE, new SunbeamAbility(0.7f, L_RANGE * 1.5f), Treasure.fromWater(2).addNature(2)));
        specs.add(new Specs(TowerName.Photonic, Sunbeam, 27478, 18, XS_SPEED, L_RANGE, new SunbeamAbility(1.7f, L_RANGE * 2), Treasure.fromWater(3).addNature(3)));

        specs.add(new Specs(TowerName.Poison, Weaken, 5, 411, N_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromWater(1).addLight(1)));
        specs.add(new Specs(TowerName.Toxic, Weaken, 7, 2414, N_SPEED, N_RANGE, HitAbility.getSplash("bullet", scale, 2), Treasure.fromWater(2).addLight(2)));
        specs.add(new Specs(TowerName.Venom, Weaken, 9, 24917, N_SPEED, N_RANGE, HitAbility.getPoison(scale, 0.2f, 1, 0.2f), Treasure.fromWater(3).addLight(3)));

        specs.add(new Specs(TowerName.Ice, Freeze, 5, 378, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 0f, 0.2f, 1, 1f), Treasure.fromWater(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Freezing, Freeze, 7, 1978, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 1f, 0.3f, 1.5f, 1f), Treasure.fromWater(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Polar, Freeze, 9, 25787, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "ice", scale, 2f, 0.7f, 2, 1f), Treasure.fromWater(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Burst, Incinerate, 5, 277, XXL_SPEED, N_RANGE, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(1).addNature(1)));
        specs.add(new Specs(TowerName.Pyro, Incinerate, 7, 1978, XXL_SPEED, N_RANGE, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(2).addNature(2)));
        specs.add(new Specs(TowerName.Flamethrower, Incinerate, 9, 21402, XXL_SPEED, N_RANGE, HitAbility.getIncreasingDamage(scale), Treasure.fromFire(3).addNature(3)));

        specs.add(new Specs(TowerName.Punch, Stomp, 5, 414, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.1f, 1, 1f), Treasure.fromFire(1).addLight(1)));
        specs.add(new Specs(TowerName.Takedown, Stomp, 7, 2013, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.2f, 2, 1f), Treasure.fromFire(2).addLight(2)));
        specs.add(new Specs(TowerName.Knockout, Stomp, 9, 25811, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "rockEffect", scale, 2f, 0.3f, 3, 1f), Treasure.fromFire(3).addLight(3)));

        specs.add(new Specs(TowerName.Sneaky, Steal, 5, 402, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 1.5f), Treasure.fromFire(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Stealth, Steal, 7, 2147, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 1.75f), Treasure.fromFire(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Thief, Steal, 9, 26784, N_SPEED, N_RANGE, HitAbility.getMoney(scale, 2f), Treasure.fromFire(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Pebble, Boulder, 5, 398, S_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 2), Treasure.fromNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Rocky, Boulder, 7, 2487, S_SPEED, S_RANGE, HitAbility.getSplash("rock", scale, 3), Treasure.fromNature(2).addLight(2)));
        specs.add(new Specs(TowerName.Massive, Boulder, 9, 31205, XS_SPEED, XS_RANGE, HitAbility.getSplash("rock", scale, 5), Treasure.fromNature(3).addLight(3)));

        specs.add(new Specs(TowerName.Dizzy, Impair, 5, 473, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.5f, 1, 0.5f), Treasure.fromNature(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Paralyze, Impair, 7, 2044, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.7f, 2, 0.75f), Treasure.fromNature(2).addDarkness(2)));
        specs.add(new Specs(TowerName.Crippling, Impair, 9, 26781, N_SPEED, N_RANGE, HitAbility.getSlow("bullet", "orb", scale, 0, 0.9f, 4, 0.85f), Treasure.fromNature(3).addDarkness(3)));

        specs.add(new Specs(TowerName.Spell, Charm, 5, 1.25f, 20, BUFF_RANGE, BuffAbility.getDamage( 20), Treasure.fromWater(1).addFire(1)));
        specs.add(new Specs(TowerName.Enchanted, Charm, 7, 1.5f, 20, BUFF_RANGE, BuffAbility.getDamage( 20), Treasure.fromWater(2).addFire(2)));
        specs.add(new Specs(TowerName.Magic, Charm, 9, 2f, 20, BUFF_RANGE, BuffAbility.getDamage( 20), Treasure.fromWater(3).addFire(3)));


        /// ######################  TREE ELEMENT
        specs.add(new Specs(TowerName.Grind, Shatter, 6, 2178, XS_SPEED, N_RANGE, HitAbility.getSplash("rock", scale, 3f), Treasure.fromWater(1).addFire(1).addNature(1)));
        specs.add(new Specs(TowerName.Pulverize, Shatter, 9, 8178, XXS_SPEED, S_RANGE, HitAbility.getSplash("rock", scale, 6f), Treasure.fromWater(1).addFire(1).addNature(1)));

        specs.add(new Specs(TowerName.Throttling, Blaze, 6, 1744, XXL_SPEED, S_RANGE, HitAbility.getBubble("fire", scale), Treasure.fromWater(1).addFire(1).addLight(1)));
        specs.add(new Specs(TowerName.Galloping, Blaze, 9, 7847, XXL_SPEED, S_RANGE, HitAbility.getBubble("fire", scale), Treasure.fromWater(2).addFire(2).addLight(2)));

        specs.add(new Specs(TowerName.Vampire, LifeSteal, 6, 2445, N_SPEED, N_RANGE, HitAbility.getLife(scale, 1f, 0.5f), Treasure.fromWater(1).addNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Dracula, LifeSteal, 9, 9136, N_SPEED, N_RANGE, HitAbility.getLife(scale, 1f, 1f), Treasure.fromWater(2).addNature(2).addLight(2)));

        specs.add(new Specs(TowerName.Disease, Infect, 6, 2141, XS_SPEED, N_RANGE, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Epidemic, Infect, 9, 7145, XL_SPEED, L_RANGE, HitAbility.getAuraDmg(scale, L_RANGE), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Confused, SoundWave, 6, 1887, L_SPEED, N_RANGE, HitAbility.getDumbClaw(scale, 0f, 0), Treasure.fromWater(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Supersonic, SoundWave, 9, 7332, L_SPEED, N_RANGE, HitAbility.getBubble("bubble", scale), Treasure.fromWater(2).addLight(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Charged, Thunderbolt, 6, 2487, S_SPEED, XL_RANGE, HitAbility.getCharge(scale), Treasure.fromFire(1).addNature(1).addLight(1)));
        specs.add(new Specs(TowerName.Supercharged, Thunderbolt, 9, 9475, XS_SPEED, XXXL_RANGE, HitAbility.getCharge(scale * 2), Treasure.fromFire(2).addNature(2).addLight(2)));

        specs.add(new Specs(TowerName.Stomp, Quake, 6, 2017, N_SPEED, N_RANGE, HitAbility.getSlow("rock", "", scale, 0, 1f, 0.5f, 0.8f), Treasure.fromFire(1).addNature(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Earthquake, Quake, 9, 8744, S_SPEED, N_RANGE, HitAbility.getSlow("rock", "", scale, 0, 1f, 1.5f, 1), Treasure.fromFire(2).addNature(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Erruption, Magma, 6, 3046, XS_SPEED, N_RANGE, HitAbility.getAuraDmg(scale, N_RANGE), Treasure.fromFire(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Volcanic, Magma, 9, 7143, XXS_SPEED, S_RANGE, HitAbility.getVolcano(scale, S_RANGE), Treasure.fromFire(2).addLight(2).addDarkness(2)));

        specs.add(new Specs(TowerName.Hypnotic, Puzzle, 6, 1987, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale, 1, 0.02f), Treasure.fromNature(1).addLight(1).addDarkness(1)));
        specs.add(new Specs(TowerName.Illusion, Puzzle, 9, 26281, N_SPEED, N_RANGE, HitAbility.getChangeDirection(scale, 5, 0.03f), Treasure.fromNature(2).addLight(2).addDarkness(2)));

        List<TowerType> data = new ArrayList<TowerType>();
        for (Specs specification : specs) {
            data.add(createTowerType(scale, specification));
        }
        return data;
    }

    private TowerType createTowerType(float scale, Specs specs) {
        // float damage = getDamage(getBaseDamage(INITIAL_BASE_DAMAGE, specs.level), specs.range, specs.speed);
        Treasure treasure = new Treasure(getGold(INITIAL_GOLD, specs.level));
        treasure.add(specs.treasure);
        return new TowerType(specs.name, specs.effectName, SIZE * scale, specs.speed, specs.damage, specs.range * scale, specs.attackComponent, treasure);
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
        private final int level;
        private final float damage;
        private final float speed;
        private final float range;
        private final AbilityComponent attackComponent;
        private final Treasure treasure;
        private final EffectName effectName;

        private Specs(TowerName name, EffectName effectName, int level, float damage, float speed, float range, AbilityComponent attackComponent, Treasure treasure) {
            this.name = name;
            this.effectName = effectName;
            this.level = level;
            this.damage = damage;
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
