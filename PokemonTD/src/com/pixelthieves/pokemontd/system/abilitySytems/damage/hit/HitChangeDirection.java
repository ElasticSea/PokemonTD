package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.pixelthieves.pokemontd.component.attack.effects.ChangeDirectionEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.data.ChangeDirectionData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitChangeDirection extends HitEffectSystem<ChangeDirectionData, ChangeDirectionEffect> {

    public HitChangeDirection() {
        super(ChangeDirectionData.class, ChangeDirectionEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected ChangeDirectionEffect resetEffect(Entity e, Entity target, ChangeDirectionEffect effect,
                                                ChangeDirectionData effectData) {
        effect.set(effectData.getEffect(), effectData.getDuration());
        return effect;
    }

    @Override
    protected ChangeDirectionEffect createEffect(Entity e, Entity target, ChangeDirectionData effectData) {
        return resetEffect(e, target, new ChangeDirectionEffect(), effectData);
    }
}
