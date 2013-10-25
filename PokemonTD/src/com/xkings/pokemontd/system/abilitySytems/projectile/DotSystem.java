package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class DotSystem extends EffectSystem {

    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<DotEffect> dotMapper;

    public DotSystem() {
        super(DotEffect.class);
    }

    @Override
    protected void processEffect(Entity e) {
        DotEffect dot = dotMapper.get(e);
        if (dot.isReady()) {
            healthMapper.get(e).getHealth().decrees(dot.getDamage());
        }
    }
}
