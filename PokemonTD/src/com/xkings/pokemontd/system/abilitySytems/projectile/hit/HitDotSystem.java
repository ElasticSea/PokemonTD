package com.xkings.pokemontd.system.abilitySytems.projectile.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Animation;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.attack.effects.DotEffect;
import com.xkings.pokemontd.component.attack.projectile.data.DotData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitDotSystem extends HitSystem {

    @Mapper
    ComponentMapper<DotData> dotDataMapper;
    @Mapper
    ComponentMapper<DotEffect> dotMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

    public HitDotSystem() {
        super(DotData.class);
    }

    @Override
    public void onHit(Entity e, Entity target) {
        DotData data = dotDataMapper.get(e);
        DotEffect effect = dotMapper.get(target);
        if (effect == null) {
            target.addComponent(new DotEffect(data.getInterval(), data.getIterations(),
                    damageMapper.get(e).getDps() * data.getDamageMultiplier() / data.getIterations()));
            world.changedEntity(target);
        } else {
            effect.reset();
        }
    }

}
