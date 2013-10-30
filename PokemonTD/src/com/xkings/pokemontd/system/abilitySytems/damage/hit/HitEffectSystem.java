package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.attack.effects.AbstractEffect;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;
import com.xkings.pokemontd.entity.TextInfo;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class HitEffectSystem<T extends EffectData, V extends AbstractEffect> extends HitSystem<T> {

    private final Class<V> effectClass;
    private ComponentMapper<V> effectMapper;

    public HitEffectSystem(Class<T> effectDataClass, Class<V> effectClass) {
        super(effectDataClass);
        this.effectClass = effectClass;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectMapper = world.getMapper(effectClass);
    }

    protected void hit(T effectData, Entity e, Entity target) {
        V effect = effectMapper.get(target);
        if (App.CHANCE.happens(effectData.getChance())) {
            V newEffect = createEffect(e, effectData);
            if (effect == null) {
                target.addComponent(newEffect);
                world.changedEntity(target);
            } else if (newEffect.compareTo(effect) >= 0) {
                effect.reset();
                resetEffect(e, effect, effectData);
            }
        } else {
            Vector3 position = positionMapper.get(target).getPoint();
            TextInfo.registerTextInfo(world, "MISSED", Color.RED, position.x, position.y);
        }
    }

    protected abstract V resetEffect(Entity e, V effect, T effectData);

    protected abstract V createEffect(Entity e, T effectData);

}
