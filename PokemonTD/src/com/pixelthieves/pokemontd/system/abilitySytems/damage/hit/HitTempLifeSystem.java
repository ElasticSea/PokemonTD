package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.pixelthieves.pokemontd.component.attack.effects.TempLifeEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.data.TempLifeData;

/**
 * Created by Seda on 10/4/13.
 */
public class HitTempLifeSystem extends HitEffectSystem<TempLifeData, TempLifeEffect> {

    public HitTempLifeSystem() {
        super(TempLifeData.class, TempLifeEffect.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected TempLifeEffect resetEffect(Entity e, Entity target, TempLifeEffect effect, TempLifeData effectData) {
        effect.set(effectData.getDuration(), damageMapper.get(e).getDamage());
        return effect;
    }

    @Override
    protected TempLifeEffect createEffect(Entity e, Entity target, TempLifeData effectData) {
        return resetEffect(e, target, new TempLifeEffect(), effectData);
    }
}
