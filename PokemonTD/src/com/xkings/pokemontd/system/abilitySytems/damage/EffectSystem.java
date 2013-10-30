package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.attack.effects.AbstractEffect;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class EffectSystem<T extends AbstractEffect> extends EntityProcessingSystem {

    private ComponentMapper<T> effectMapper;
    private final Class<T> effect;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

    public EffectSystem(Class<T> effect) {
        super(Aspect.getAspectForAll(effect));
        this.effect = effect;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectMapper = world.getMapper(effect);
    }

    @Override
    protected void process(Entity e) {
        SpriteComponent spriteComponent = spriteMapper.get(e);
        T effect = effectMapper.get(e);

        if (effect.isReset()) {
            resetEffect(effect, e);
            effect.setReset(false);
        }

        if (effect.isReattach()) {
            reattachEffect(effect, e);
            effect.setReattach(false);
        }

        if (!effect.isStarted()) {
            addEffect(effect, spriteComponent);
            started(effect, e);
        }
        effect.update(world.delta);
        if (spriteComponent.get(effect.getEffect()) == null) {
            // DISCUS Is it a good enough solution to simply check if we have an animation going on or not,
            // this happens because this effect component is transfered to different tower that does not have
            // particular animation component.
            addEffect(effect, spriteComponent);
        }
        while (effect.isReady()) {
            processEffect(effect, e);
        }
        if (effect.isFinished()) {
            finished(effect, e);
            removeEffect(effect, spriteComponent);
            e.removeComponent(effect.getClass());
            e.changedInWorld();
        }
    }

    private void removeEffect(T effect, SpriteComponent spriteComponent) {
        spriteComponent.remove(effect.getEffect());
    }

    private void addEffect(T effect, SpriteComponent spriteComponent) {
        spriteComponent.add(effect.getEffect());
    }

    protected abstract void finished(T effect, Entity e);

    protected abstract void started(T effect, Entity e);

    protected abstract void processEffect(T effect, Entity e);

    protected abstract void resetEffect(T effect, Entity e);

    protected abstract void reattachEffect(T effect, Entity e);
}
