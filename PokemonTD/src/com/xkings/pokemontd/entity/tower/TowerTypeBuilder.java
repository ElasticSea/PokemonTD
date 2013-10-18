package com.xkings.pokemontd.entity.tower;

import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.attack.ProjectileComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class TowerTypeBuilder {

    public static final float SPEED = 1;
    public static final float SIZE = 1;
    public static final int RANGE = 3;

    private List<TowerType> getData(float scale) {
        List<TowerType> list = new ArrayList<TowerType>();
        list.add(new TowerType(TowerName.Needle, SIZE * scale, SPEED * scale, 15, RANGE * scale,
                ProjectileComponent.getNormal(scale), new Treasure(10)));
        list.add(new TowerType(TowerName.Pinch, SIZE * scale, SPEED * scale, 45, RANGE * scale,
                ProjectileComponent.getNormal(scale), new Treasure(20)));
        list.add(new TowerType(TowerName.Sting, SIZE * scale, SPEED * scale, 135, RANGE * scale,
                ProjectileComponent.getNormal(scale), new Treasure(40)));
        list.add(new TowerType(TowerName.Scratch, SIZE * scale, SPEED * scale, 5.625f, RANGE * scale,
                ProjectileComponent.getSplash(scale, 2), new Treasure(10)));
        list.add(new TowerType(TowerName.Bite, SIZE * scale, SPEED * scale, 16.875f, RANGE * scale,
                ProjectileComponent.getSplash(scale, 2), new Treasure(20)));
        list.add(new TowerType(TowerName.Smash, SIZE * scale, SPEED * scale, 50.625f, RANGE * scale,
                ProjectileComponent.getSplash(scale, 2), new Treasure(40)));
        return list;
    }


    public Map<TowerName, TowerType> build(float scale) {
        Map<TowerName, TowerType> map = new HashMap<TowerName, TowerType>();
        for (TowerType towerType : getData(scale)) {
            map.put(towerType.getName(), towerType);
        }
        return map;
    }
}
