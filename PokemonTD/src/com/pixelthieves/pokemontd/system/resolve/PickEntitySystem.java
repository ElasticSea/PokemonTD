package com.pixelthieves.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.PathComponent;
import com.pixelthieves.pokemontd.component.VisibleComponent;
import com.pixelthieves.pokemontd.map.Path;

import java.util.*;

public abstract class PickEntitySystem extends EntitySystem implements PickEntity {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<VisibleComponent> visibilityMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;

    private final boolean sort;
    protected Entity entity;
    protected Vector3 entityPosition;
    protected float entityRange;
    protected float closestDistance;
    protected Entity closestEntity;

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
        entities = getReachable(entities);

        if (sort) {
            List<Path> paths = new ArrayList<Path>(entities.size());
            Map<Path, Entity> map = new HashMap<Path, Entity>();
            for (int i = 0; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                PathComponent pathComponent = pathMapper.get(entity);
                paths.add(pathComponent.getPath());
                map.put(pathComponent.getPath(), entity);
            }
            Collections.sort(paths, Collections.reverseOrder());
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


    protected boolean isRequirementMet(Entity e) {
        return true;
    }

    Vector3[] corners = new Vector3[]{new Vector3(), new Vector3(), new Vector3(), new Vector3()};

    protected float calculateDistance(Vector3 originalPosition, Vector3 position, Vector3 size) {
        /*corners[0].x = position.x - size.x / 2;
        corners[0].y = position.y - size.y / 2;
        corners[1].x = corners[0].x + size.x;
        corners[1].y = corners[0].y;
        corners[2].x = corners[0].x;
        corners[2].y = corners[0].y + size.y;
        corners[3].x = corners[0].x + size.x;
        corners[3].y = corners[0].y + size.y;

        float distance = Float.MAX_VALUE;
        for (Vector3 corner : corners) {
            float candidate = calculateDistance(corner, originalPosition);
            if (candidate < distance) {
                distance = candidate;
            }
        }      */
        return calculateDistance(position, originalPosition);
    }

    private float calculateDistance(Vector3 a, Vector3 b) {
        float tx = b.x - a.x;
        float ty = b.y - a.y;
        return (float) Math.sqrt(tx * tx + ty * ty);

    }

    @Override
    public void start(Entity entity, Vector3 position, float range) {
        this.entity = entity;
        this.entityPosition = position;
        this.entityRange = range;
        this.closestDistance = Float.MAX_VALUE;
        this.closestEntity = null;
        this.process();
    }

    @Override
    public Entity getPickedEntity() {
        return closestEntity;
    }

    public ImmutableBag<Entity> getReachable(ImmutableBag<Entity> entities) {
        Bag<Entity> reachable = new Bag<Entity>();
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            Vector3 position = positionMapper.get(e).getPoint();
            //Vector3 size = sizeMapper.get(e).getPoint();
            if (!visibilityMapper.has(e) || visibilityMapper.get(e).isVisible()) {
                if (!healthMapper.has(e) || healthMapper.get(e).getHealth().isDestructible()) {
                    // FIXME: Collision should not be negated, there must be a mistake. 67It does not work at all.
                 //  if (Collision.intersectpointInRect(position, entityPosition, entityRange, entityRange)) {
                        float distance = calculateDistance(entityPosition, position);
                        if (this.entity != e && e.isEnabled() && distance <= entityRange) {
                            reachable.add(e);
                        }
                  //  }
                }
            }
        }
        return reachable;
    }
}
