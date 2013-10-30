package com.xkings.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.map.Path;

import java.util.*;

public abstract class PickEntitySystem extends EntitySystem implements PickEntity {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;

    private final boolean sort;

    public PickEntitySystem(Aspect aspect, boolean sort) {
        super(aspect);
        this.sort = sort;
    }

    /**
     * Process a entity this system is interested in.
     *
     * @param e the entity to process.
     */
    protected abstract void process(Entity e);

    @Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
        if (sort) {
            List<Path> paths = new ArrayList<Path>(entities.size());
            Map<Path, Entity> map = new HashMap<Path, Entity>();
            for (int i = 0; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                PathComponent pathComponent = pathMapper.get(entity);
                paths.add(pathComponent.getPath());
                map.put(pathComponent.getPath(), entity);
            }
            Collections.sort(paths,Collections.reverseOrder());
            for (Path path : paths) {
                process(map.get(path));
            }
        } else {
            for (int i = 0, s = entities.size(); s > i; i++) {
                process(entities.get(i));
            }
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

}
