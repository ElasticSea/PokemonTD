package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Animation;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.attack.effects.AbstractEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public class EffectSystem extends EntityProcessingSystem {

    private ComponentMapper<? extends AbstractEffect> effectMapper;
    private final Class<? extends AbstractEffect> effect;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

    public EffectSystem(Class<? extends AbstractEffect> effect) {
        super(Aspect.getAspectForAll(HealthComponent.class, effect));
        this.effect = effect;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectMapper = world.getMapper(effect);
    }

    @Override
    protected void process(Entity e) {
        AbstractEffect effect = effectMapper.get(e);
        if (!effect.isStarted()) {
            spriteMapper.get(e).add(SpriteComponent.Type.EFFECT,
                    new Animation(Assets.getTextureArray(effect.getEffect())));
            started(e);
        }
        effect.update(world.delta);
        while (effect.isReady()) {
            processEffect(e);
        }
        if (effect.isFinished()) {
            finished(e);
            spriteMapper.get(e).remove(SpriteComponent.Type.EFFECT);
            e.removeComponent(effect.getClass());
            e.changedInWorld();
        }
    }

    protected void finished(Entity e) {

    }

    protected void started(Entity e) {

    }

    protected void processEffect(Entity e) {

    }
}
