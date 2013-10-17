package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.Player;

/**
 * Created by Tomas on 10/4/13.
 */
public class WaveSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    private final Player player;

    public WaveSystem(Player player) {
        super(Aspect.getAspectForAll(WaveComponent.class, DamageComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity entity) {
        PathComponent pathComponent = pathMapper.get(entity);
        float damage = damageMapper.get(entity).getDamage();
        PositionComponent positionComponent = positionMapper.get(entity);
        if (pathComponent.isFinished()) {
            player.getHealth().decrees((int) damage);
            pathComponent.reset();
            positionComponent.getPoint().set(pathComponent.get());
        }
    }

}
