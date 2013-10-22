package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.TintComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class DotSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<DotEffect> dotMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public DotSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, DotEffect.class, TintComponent.class));
    }

    @Override
    protected void process(Entity e) {
        DotEffect dot = dotMapper.get(e);
        dot.update(world.delta);
        if (dot.isReady()) {
            tintMapper.get(e).getTint().set(dot.getTint());
            healthMapper.get(e).getHealth().decrees(dot.getDamage());
        }
    }
}
