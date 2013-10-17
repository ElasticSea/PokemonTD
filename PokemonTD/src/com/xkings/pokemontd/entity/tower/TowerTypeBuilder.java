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

    public static final float SPEED = 1f;
    public static final float SIZE = 1f;

    private final List<TowerType> data = getData();

    private List<TowerType> getData() {
        List<TowerType> list = new ArrayList<TowerType>();
        list.add(
                new TowerType(TowerName.Needle, SIZE, SPEED, 15, 3, ProjectileComponent.getNormal(), new Treasure(10)));
        list.add(new TowerType(TowerName.Pinch, SIZE, SPEED, 45, 3, ProjectileComponent.getNormal(), new Treasure(20)));
        list.add(
                new TowerType(TowerName.Sting, SIZE, SPEED, 135, 3, ProjectileComponent.getNormal(), new Treasure(40)));
        list.add(new TowerType(TowerName.Scratch, SIZE, SPEED, 5.625f, 3, ProjectileComponent.getSplash(2),
                new Treasure(10)));
        list.add(new TowerType(TowerName.Bite, SIZE, SPEED, 16.875f, 3, ProjectileComponent.getSplash(2),
                new Treasure(20)));
        list.add(new TowerType(TowerName.Smash, SIZE, SPEED, 50.625f, 3, ProjectileComponent.getSplash(2),
                new Treasure(40)));
        return list;
    }


    public Map<TowerName, TowerType> build() {
        Map<TowerName, TowerType> map = new HashMap<TowerName, TowerType>();
        for (TowerType towerType : data) {
            map.put(towerType.getName(), towerType);
        }
        return map;
    }
}
