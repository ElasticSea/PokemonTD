package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.pokemontd.component.PathComponent;

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
    ComponentMapper<CreepStateComponent> creepStateMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;

    private PathComponent pathComponent;
    private Vector3 position;
    private CreepStateComponent creepStateComponent;
    private float speed;
    private RotationComponent rotation;

    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, SpeedComponent.class,
                RotationComponent.class, CreepStateComponent.class));
    }


    @Override
    protected void process(Entity entity) {
        pathComponent = pathMapper.get(entity);
        position = positionMapper.get(entity).getPoint();
        creepStateComponent = creepStateMapper.get(entity);
        speed = speedMapper.get(entity).getSpeed();
        rotation = rotationMapper.get(entity);

        TimeHolder timeHolder = creepStateComponent.getTimeHolder();
        timeHolder.increase(world.getDelta());

        while (timeHolder.getAvailableTime() > 0) {
            if (!pathComponent.isFinished()) {
                Vector3 goal = pathComponent.get();
                rotation.getPoint().x =
                        (float) (Math.atan2(goal.y - position.y, goal.x - position.x) * 180 / Math.PI + 90);
                if (moveTowards(position, goal, speed, timeHolder)) {
                    pathComponent.next();
                    if (pathComponent.isFinished()) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    private boolean moveTowards(Vector3 from, Vector3 to, float speed, TimeHolder timeHolder) {
        float distance = getDistance(from, to);
        float travelAbility = speed * timeHolder.getAvailableTime();
        float travelTime = timeHolder.getAvailableTime();
        if (travelAbility >= distance) {
            from.set(to);
            timeHolder.decrease((travelAbility - distance) / speed);
            return true;
        } else {
            double ang = getRotation(from, to);
            from.x = from.x + (float) (Math.cos(ang) * travelAbility);
            from.y = from.y + (float) (Math.sin(ang) * travelAbility);
            timeHolder.decrease(travelTime);
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
