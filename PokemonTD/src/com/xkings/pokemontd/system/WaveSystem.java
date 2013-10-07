package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.component.AttackComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.entity.Player;

/**
 * Created by Tomas on 10/4/13.
 */
public class WaveSystem extends EntityProcessingSystem {

    private final Player player;
    @Mapper
    ComponentMapper<WaveComponent> waveMapper;
    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<AttackComponent> attackMapper;

    private WaveComponent waveComponent;
    private PathComponent pathComponent;
    private AttackComponent attackComponent;

    public WaveSystem(Player player) {
        super(Aspect.getAspectForAll(WaveComponent.class, AttackComponent.class));
        this.player = player;
    }


    @Override
    protected void process(Entity entity) {
        waveComponent = waveMapper.get(entity);
        pathComponent = pathMapper.get(entity);
        attackComponent = attackMapper.get(entity);
        if (pathComponent.isFinished()) {
            if (waveComponent.removeCreep(entity)) {
                player.getHealth().decrees(attackComponent.getAttack());
            }
        }
    }

}
