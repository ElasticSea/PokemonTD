package com.xkings.pokemontd.manager;

import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.UpdateFilter;
import com.xkings.core.logic.Updateable;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.entity.Creep;
import com.xkings.pokemontd.entity.CreepType;
import com.xkings.pokemontd.map.Path;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Tomas on 10/5/13.
 */
public class WaveManager implements Updateable {
    private final World world;
    private final Path path;

    private final Iterator<CreepType> creeps;
    private boolean active;

    /**
     * @param clock internal update timer
     */
    private WaveManager(World world, Clock clock, Path path) {
        this.world = world;
        this.path = path;
        this.creeps = Arrays.asList(CreepType.values()).iterator();
        this.active = true;
    }

    public static WaveManager createInstance(World world, Clock clock, Path path, float interval) {
        WaveManager instance = new WaveManager(world, clock, path);
        clock.addService(new UpdateFilter(instance, interval));
        return instance;
    }


    @Override
    public void update(float delta) {
        if (creeps.hasNext()) {
            fireNextWave(creeps.next());
        }
    }

    private void fireNextWave(CreepType next) {
        Vector3 startPoint = path.getPath().get(0);
        Vector3 nextPoint = path.getPath().get(1);
        double angleToNextPoint = Math.atan2(nextPoint.y - startPoint.y, nextPoint.x - startPoint.x);
        for (int i = 0; i < next.getCreepsInWave(); i++) {
            float xOffset = (float) (Math.cos(angleToNextPoint + Math.PI) * next.getSize() * i);
            float yOffset = (float) (Math.sin(angleToNextPoint + Math.PI) * next.getSize() * i);
            Creep.registerCreep(world, path, new WaveComponent(), next, startPoint.x + xOffset, startPoint.y + yOffset);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

}
