package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.TreasureComponent;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player extends ConcreteEntity {
    public Player(World world, int health, int gold) {
        super(world);
        addComponent(new TreasureComponent(new Treasure(gold)));
        addComponent(new HealthComponent(health));
    }

    public static void registerPlayer(World world, int health, int gold) {
        Player player = new Player(world, health, gold);
        player.register();
    }

}
