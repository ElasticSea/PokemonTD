package com.pixelthieves.elementtd.system.trigger;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.core.component.Time;
import com.pixelthieves.core.component.TimeComponent;
import com.pixelthieves.elementtd.component.attack.AbilityComponent;
import com.pixelthieves.elementtd.component.attack.effects.buff.BuffableSpeedComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class IntervalAbilitySystem<T extends AbilityComponent> extends EntityProcessingSystem {

    private final Class<T> ability;
    @Mapper
    ComponentMapper<BuffableSpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;

    ComponentMapper<T> abilityMapper;

    public IntervalAbilitySystem(Class<T> ability) {
        super(Aspect.getAspectForAll(TimeComponent.class, BuffableSpeedComponent.class, ability));
        this.ability = ability;
    }

    @Override
    protected void initialize() {
        super.initialize();
        abilityMapper = world.getMapper(ability);
    }

    @Override
    protected void process(Entity entity) {
        Time time = timeMapper.get(entity).getTime(this.getClass());
        time.increase(world.getDelta());
        float speed = speedMapper.get(entity).getSpeed();
        while (time.getAvailableTime() >= speed) {
            time.decrease(speed);
            run(abilityMapper.get(entity), entity);
        }
    }

    protected abstract void run(T ability, Entity entity);

}
