package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.*;
import com.pixelthieves.pokemontd.component.PathComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;
import com.pixelthieves.pokemontd.map.Path;

/**
 * Created by Tomas on 10/4/13.
 */
public class MovementSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<BuffableSpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;


    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, BuffableSpeedComponent.class,
                TimeComponent.class, PathComponent.class));
    }


    @Override
    protected void process(Entity e) {
        Path path = pathMapper.get(e).getPath();
        Vector3 position = positionMapper.get(e).getPoint();
        TimeComponent timeComponent = timeMapper.get(e);
        float speed = speedMapper.get(e).getSpeed();

        Time time = timeComponent.getTime(this.getClass());
        time.increase(world.getDelta());

        while (time.getAvailableTime() > 0) {
            if (!path.isFinished()) {
                Vector3 goal = path.get();
                RotationComponent rotationComponent = rotationMapper.getSafe(e);
                if (moveTowards(position, goal, rotationComponent != null ? rotationComponent.getPoint() : null, speed,
                        path, time)) {
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

    private boolean moveTowards(Vector3 from, Vector3 to, Vector3 rotation, float speed, Path path, Time time) {
        float distance = getDistance(from, to);
        float travelAbility = speed * time.getAvailableTime();
        float travelTime = time.getAvailableTime();
        if (travelAbility >= distance) {
            from.set(to);
            time.decrease(distance / speed);
            return true;
        } else {
            double ang = getRotation(from, to);
            if (rotation != null) {
                rotation.x = (float) (ang / Math.PI * 180);
            }
            from.x = from.x + (float) (Math.cos(ang) * travelAbility);
            from.y = from.y + (float) (Math.sin(ang) * travelAbility);
            time.decrease(travelTime);
            path.setToTravel(distance - travelAbility);
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
