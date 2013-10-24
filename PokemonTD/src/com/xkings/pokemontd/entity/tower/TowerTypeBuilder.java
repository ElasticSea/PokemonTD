package com.xkings.pokemontd.entity.tower;

import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.projectile.ProjectileComponent;

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
    public static final float N_RANGE = 2.25f;
    public static final float L_RANGE = 2.75f;
    public static final float XL_RANGE = 3.25f;
    public static final float SUPER_FAST_SPEED = 0.25f;
    public static final float FAST_SPEED = 0.5f;
    public static final float NORMAL_SPEED = 1;
    public static final float SLOW_SPEED = 2f;
    public static final float SUPER_SLOW_SPEED = 4f;
    public static final int INITIAL_BASE_DAMAGE = 15;
    public static final int INITIAL_GOLD = 10;
    private static final float koefficient = (float) (scaleByRange(XS_RANGE) - scaleByRange(XL_RANGE)) * 2f;

    private List<TowerType> getData(float scale) {
        List<Specs> specs = new ArrayList<Specs>();
        // Shop
        specs.add(new Specs(TowerName.Shop, 1, 0, 0, null));
        // Basic
        specs.add(new Specs(TowerName.Needle, 1, NORMAL_SPEED, N_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Pinch, 2, NORMAL_SPEED, N_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Sting, 3, NORMAL_SPEED, N_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Scratch, 1, SLOW_SPEED, N_RANGE, ProjectileComponent.getSplash(scale, 2)));
        specs.add(new Specs(TowerName.Bite, 2, SLOW_SPEED, N_RANGE, ProjectileComponent.getSplash(scale, 2)));
        specs.add(new Specs(TowerName.Smash, 3, SLOW_SPEED, N_RANGE, ProjectileComponent.getSplash(scale, 2)));
        // blue
        specs.add(new Specs(TowerName.Splash, 4, NORMAL_SPEED, N_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Wave, 5, NORMAL_SPEED, N_RANGE, ProjectileComponent.getBubble(scale)));
        specs.add(new Specs(TowerName.Tsunami, 10, NORMAL_SPEED, N_RANGE, ProjectileComponent.getBubble(scale)));
        //red
        specs.add(new Specs(TowerName.Sparkle, 4, NORMAL_SPEED, S_RANGE, ProjectileComponent.getFire(scale)));
        specs.add(new Specs(TowerName.Burning, 5, FAST_SPEED, S_RANGE, ProjectileComponent.getFire(scale)));
        specs.add(new Specs(TowerName.Inferno, 10, SUPER_FAST_SPEED, S_RANGE, ProjectileComponent.getFireDot(scale)));
        //green
        specs.add(new Specs(TowerName.Flower, 4, SUPER_SLOW_SPEED, S_RANGE,
                ProjectileComponent.getNature(scale, 0.2f, 1, 0.2f)));
        specs.add(new Specs(TowerName.Forest, 5, SUPER_SLOW_SPEED, S_RANGE,
                ProjectileComponent.getNature(scale, 0.3f, 1.2f, 0.3f)));
        specs.add(new Specs(TowerName.Nature, 10, SUPER_SLOW_SPEED, S_RANGE,
                ProjectileComponent.getNature(scale, 0.7f, 2f, 0.5f)));
        //yellow
        specs.add(new Specs(TowerName.Chicken, 4, FAST_SPEED, N_RANGE, ProjectileComponent.getClaw(scale, 0f, 0)));
        specs.add(new Specs(TowerName.Screech, 5, FAST_SPEED, N_RANGE, ProjectileComponent.getClaw(scale, 0.1f, 1)));
        specs.add(new Specs(TowerName.Claw, 10, FAST_SPEED, N_RANGE, ProjectileComponent.getClaw(scale, 0.3f, 2)));
        //purple
        specs.add(new Specs(TowerName.Spooky, 4, NORMAL_SPEED, XL_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Haunted, 5, NORMAL_SPEED, XL_RANGE, ProjectileComponent.getNormal(scale)));
        specs.add(new Specs(TowerName.Nightmare, 10, NORMAL_SPEED, XL_RANGE,
                ProjectileComponent.getTemLifeSteal(scale, 0.3f, 5f)));

        List<TowerType> data = new ArrayList<TowerType>();
        for (Specs specification : specs) {
            data.add(createTowerType(scale, specification));
        }
        return data;
    }

    private TowerType createTowerType(float scale, Specs specs) {
        float damage = getDamage(getBaseDamage(INITIAL_BASE_DAMAGE, specs.level), specs.range, specs.speed);
        return new TowerType(specs.name, SIZE * scale, specs.speed, damage, specs.range * scale, specs.attackComponent,
                new Treasure(getGold(INITIAL_GOLD, specs.level)));
    }

    private float getDamage(float baseDamage, float range, float speed) {
        System.out.println(baseDamage * (float) scaleByRange(range) / koefficient);
        return (baseDamage + baseDamage * (float) scaleByRange(range) / koefficient) * speed;
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

        private Specs(TowerName name, int level, float speed, float range, AbilityComponent attackComponent) {
            this.name = name;
            this.level = level;
            this.speed = speed;
            this.range = range;
            this.attackComponent = attackComponent;
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
