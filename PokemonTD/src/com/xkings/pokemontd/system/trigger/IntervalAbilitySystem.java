package com.xkings.pokemontd.system.trigger;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.SpeedComponent;
import com.xkings.core.component.Time;
import com.xkings.core.component.TimeComponent;
import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class IntervalAbilitySystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;

    public IntervalAbilitySystem(Class<? extends AbilityComponent> filter) {
        super(Aspect.getAspectForAll(TimeComponent.class, SpeedComponent.class, filter));
    }

    @Override
    protected void process(Entity entity) {
        Time time = timeMapper.get(entity).getTime(this.getClass());
        time.increase(world.getDelta());
        float speed = speedMapper.get(entity).getSpeed();
        while (time.getAvailableTime() >= speed) {
            time.decrease(speed);
            run(entity);
        }
    }

    protected void run(Entity entity) {
    }

}
