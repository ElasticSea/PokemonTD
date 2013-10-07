package com.xkings.pokemontd.manager;

import com.artemis.World;
import com.badlogic.gdx.math.Vector2;
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
     * @param clock    internal update timer
     * @param interval time in seconds between wave calls
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
        System.out.println("Fire Wave: " + next);
        Vector3 point = path.getPath().get(0);
        Creep.registerCreep(world, path,new WaveComponent(), next, point.x, point.y);
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
