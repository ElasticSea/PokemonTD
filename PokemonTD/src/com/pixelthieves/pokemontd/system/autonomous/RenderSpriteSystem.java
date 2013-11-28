package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.RotationComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.pokemontd.Animation;
import com.pixelthieves.pokemontd.component.SpriteComponent;
import com.pixelthieves.pokemontd.component.TintComponent;
import com.pixelthieves.pokemontd.component.VisibleComponent;

/**
 * Created by Seda on 10/4/13.
 */
public class RenderSpriteSystem extends EntitySystem {
    private final Camera camera;
    private final SpriteBatch spriteBatch;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;
    @Mapper
    ComponentMapper<VisibleComponent> visibleMapper;

    public RenderSpriteSystem(Camera camera, SpriteBatch spriteBatch) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, SpriteComponent.class));
        this.camera = camera;
        this.spriteBatch = spriteBatch;
    }

    protected void process(Entity e) {
        if (visibleMapper.has(e) && !visibleMapper.get(e).isVisible()) {
            return;
        }

        PositionComponent positionComponent = positionMapper.get(e);
        Vector3 size = sizeMapper.get(e).getPoint();

        Animation[] animations = spriteMapper.get(e).get();
        for (int i = 0; i < animations.length; i++) {
            Animation animation = animations[i];
            if (animation != null && animation.hasNext()) {
                TextureAtlas.AtlasRegion sprite = animation.next();
                float x = positionComponent.getPoint().x - size.x / 2f;
                float y = positionComponent.getPoint().y - size.y / 2f;
                spriteBatch.setColor(tintMapper.has(e) ? tintMapper.get(e).getTint() : Color.WHITE);
                if (rotationMapper.has(e)) {
                    RotationComponent rotationComponent = rotationMapper.get(e);
                    Vector3 rotation = rotationComponent.getPoint();
                    Vector3 origin = rotationComponent.getOrigin();
                    if (origin.equals(Vector3.Zero)) {
                        spriteBatch.draw(sprite, x, y, size.x / 2f, size.y / 2f, size.x, size.y, 1f, 1f, rotation.x);
                    } else {
                        float originX = size.x * origin.x;
                        float originY = size.y * origin.y;
                        // FIXME this I have no idea how this works.
                        spriteBatch.draw(sprite, x + size.x * origin.y, y + size.y * origin.x, originX, originY, size.x,
                                size.y, 1f, 1f, rotation.x);
                    }
                    // spriteBatch.draw(sprite, x, y, size.x / 2f, size.y / 2f, size.x, size.y, 1f, 1f, rotation.x);
                } else {
                    spriteBatch.draw(sprite, x, y, size.x, size.y);
                }
            }
        }

    }

    @Override
    protected void end() {
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        Entity[] data = new Entity[entities.size()];
        int m = 0;
        int n = 0;
        while (n < data.length) {
            Entity entity = entities.get(m++);
            if (entity != null) {
                data[n++] = entity;
            }
        }
        Object[] dataCopy = data.clone();

        PositionComponent[] positions = new PositionComponent[data.length];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = positionMapper.get(data[i]);
        }

        Object[] positionCopy = positions.clone();
        mergeSort(dataCopy, data, positions, positionCopy,0, data.length, 0);

        for (int i = 0; i < data.length; i++) {
            process(data[i]);
        }
    }

    /**
     * Tuning parameter: list size at or below which insertion sort will be
     * used in preference to mergesort or quicksort.
     */
    private static final int INSERTIONSORT_THRESHOLD = 7;

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * off is the offset to generate corresponding low, high in src
     */
    private static void mergeSort(Object[] src, Object[] dest, Object[] compareSrc, Object[] compareDest, int low,
                                  int high, int off) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
                        ((Comparable) compareDest[j-1]).compareTo(compareDest[j])>0; j--) {
                    swap(dest, j, j-1);
            swap(compareDest, j, j-1);
                }
            return;
        }


        // Recursively sort halves of dest into src
        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, compareDest, compareSrc, low, mid, -off);
        mergeSort(dest, src, compareDest, compareSrc, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)compareSrc[mid-1]).compareTo(compareSrc[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable) compareSrc[p]).compareTo(compareSrc[q]) < 0) {
                int i1 = p++;
                dest[i] = src[i1];
                compareDest[i] = compareSrc[i1];
            } else {
                int i1 = q++;
                dest[i] = src[i1];
                compareDest[i] = compareSrc[i1];
            }
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
