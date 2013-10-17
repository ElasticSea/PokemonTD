package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.TreasureComponent;
import com.xkings.pokemontd.entity.MoneyInfo;

/**
 * Created by Tomas on 10/4/13.
 */
public class DeathSystem extends EntityProcessingSystem {

    private final Player player;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TreasureComponent> treasureMapper;

    public DeathSystem(Player player) {
        super(Aspect.getAspectForAll(HealthComponent.class, TreasureComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity e) {
        Health health = healthMapper.get(e).getHealth();
        if (!isAlive(health)) {
            Vector3 position = positionMapper.get(e).getPoint();
            Treasure treasure = treasureMapper.get(e).getTreasure();
            MoneyInfo.registerMoneyInfo(world, treasure.getGold(), position.x, position.y);
            treasure.transferTo(player.getTreasure());
            e.deleteFromWorld();
        }
    }

    private boolean isAlive(Health health) {
        return health.getLives() > 0;
    }

}
