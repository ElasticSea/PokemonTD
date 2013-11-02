package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.xkings.pokemontd.component.attack.effects.ChangeDirectionEffect;
import com.xkings.pokemontd.component.attack.projectile.data.ChangeDirectionData;

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
    protected ChangeDirectionEffect resetEffect(Entity e, ChangeDirectionEffect effect,
                                                ChangeDirectionData effectData) {
        effect.set(effectData.getEffect(), effectData.getDuration());
        System.out.println("RST");
        return effect;
    }

    @Override
    protected ChangeDirectionEffect createEffect(Entity e, ChangeDirectionData effectData) {
        System.out.println("Create");
        return resetEffect(e, new ChangeDirectionEffect(), effectData);
    }
}
