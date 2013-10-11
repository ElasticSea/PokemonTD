package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.entity.Player;

/**
 * Created by Tomas on 10/4/13.
 */
public class DeathSystem extends EntityProcessingSystem {

    private final Player player;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TreasureComponent> treasureMapper;

    public DeathSystem(Player player) {
        super(Aspect.getAspectForAll(HealthComponent.class, TreasureComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity entity) {
        Health health = healthMapper.get(entity).getHealth();
        if(!isAlive(health)){
            treasureMapper.get(entity).getTreasure().transferTo(player.getTreasure());
            entity.deleteFromWorld();
        }
    }

    private boolean isAlive(Health health) {
        return health.getLives() > 0;
    }

}
