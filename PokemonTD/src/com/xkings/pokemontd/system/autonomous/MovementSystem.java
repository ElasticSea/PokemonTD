package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/4/13.
 */
public class MovementSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;


    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, SpeedComponent.class,
                RotationComponent.class, TimeComponent.class));
    }


    @Override
    protected void process(Entity entity) {
        Path path = pathMapper.get(entity).getPath();
        Vector3 position = positionMapper.get(entity).getPoint();
        TimeComponent timeComponent = timeMapper.get(entity);
        float speed = speedMapper.get(entity).getSpeed();
        RotationComponent rotation = rotationMapper.get(entity);

        Time time = timeComponent.getTime(this.getClass());
        time.increase(world.getDelta());

        while (time.getAvailableTime() > 0) {
            if (!path.isFinished()) {
                Vector3 goal = path.get();
                rotation.getPoint().x = (float) (Math.atan2(goal.y - position.y, goal.x - position.x) * 180 / Math.PI);
                if (moveTowards(position, goal, speed, time)) {
                    path.next();
                    if (path.isFinished()) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private boolean moveTowards(Vector3 from, Vector3 to, float speed, Time time) {
        float distance = getDistance(from, to);
        float travelAbility = speed * time.getAvailableTime();
        float travelTime = time.getAvailableTime();
        if (travelAbility >= distance) {
            from.set(to);
            time.decrease(distance / speed);
            return true;
        } else {
            double ang = getRotation(from, to);
            from.x = from.x + (float) (Math.cos(ang) * travelAbility);
            from.y = from.y + (float) (Math.sin(ang) * travelAbility);
            time.decrease(travelTime);
            return false;
        }
    }

    private double getRotation(Vector3 from, Vector3 to) {
        float tx = to.x - from.x;
        float ty = to.y - from.y;
        return Math.atan2(ty, tx);
    }

    private float getDistance(Vector3 from, Vector3 to) {
        float tx = to.x - from.x;
        float ty = to.y - from.y;
        return (float) Math.sqrt(tx * tx + ty * ty);
    }

}
