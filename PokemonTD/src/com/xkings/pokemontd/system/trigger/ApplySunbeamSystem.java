package com.xkings.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.projectile.SunbeamAbility;
import com.xkings.pokemontd.entity.Sunbeam;
import com.xkings.pokemontd.system.abilitySytems.damage.DamageOverPolySystem;
import com.xkings.pokemontd.system.resolve.FirstCreepSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class ApplySunbeamSystem extends ApplyAbilitySystem<SunbeamAbility> {

    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;

    public ApplySunbeamSystem() {
        super(SunbeamAbility.class, FirstCreepSystem.class);
    }

    private DamageOverPolySystem damageOverPolySystem;

    @Override
    protected void initialize() {
        super.initialize();
        damageOverPolySystem = world.getSystem(DamageOverPolySystem.class);
    }

    @Override
    protected void processTarget(SunbeamAbility ability, Entity entity, Entity target) {
        Vector3 start = positionMapper.get(entity).getPoint();
        Vector3 end = positionMapper.get(target).getPoint();

        float angle = (float) (Math.atan2(end.y - start.y, end.x - start.x));
        float x = (float) (Math.cos(angle) * ability.getHeight());
        float y = (float) (Math.sin(angle) * ability.getHeight());
        Vector2[] rectangle = Collision.getRectOverLine(start.x, start.y, start.x + x, start.y + y, ability.getWidth());
        damageOverPolySystem.start(rectangle, damageMapper.get(entity).getDamage());
        Sunbeam.registerSunbeam(world, start.x, start.y, ability.getHeight(), ability.getWidth(), target);
    }

}
