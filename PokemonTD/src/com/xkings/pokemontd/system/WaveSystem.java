package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.component.AttackComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.entity.Player;

/**
 * Created by Tomas on 10/4/13.
 */
public class WaveSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<AttackComponent> attackMapper;

    private final Player player;

    public WaveSystem(Player player) {
        super(Aspect.getAspectForAll(WaveComponent.class, AttackComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity entity) {
        PathComponent pathComponent = pathMapper.get(entity);
        int attack = attackMapper.get(entity).getAttack();
        PositionComponent positionComponent = positionMapper.get(entity);
        if (pathComponent.isFinished()) {
            player.getHealth().decrees(attack);
            pathComponent.reset();
            positionComponent.getPoint().set(pathComponent.get());
        }
    }

}
