package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.core.component.DamageComponent;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.component.PathComponent;
import com.pixelthieves.pokemontd.component.WaveComponent;
import com.pixelthieves.pokemontd.map.Path;

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
        super(Aspect.getAspectForAll(WaveComponent.class, DamageComponent.class, PathComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity entity) {
        Path path = pathMapper.get(entity).getPath();
        float damage = damageMapper.get(entity).getDamage();
        PositionComponent positionComponent = positionMapper.get(entity);
        if (path.isFinished()) {
            player.decreaseHealth((int) damage);
            path.reset();
            positionComponent.getPoint().set(path.get());
        }
    }

}
