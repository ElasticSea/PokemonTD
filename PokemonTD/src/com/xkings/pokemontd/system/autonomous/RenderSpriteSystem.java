package com.xkings.pokemontd.system.autonomous;

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
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TintComponent;
import com.xkings.pokemontd.component.VisibleComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderSpriteSystem extends EntitySystem {
    private final Camera camera;
    private final SpriteBatch spriteBatch = new SpriteBatch();

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

    public RenderSpriteSystem(Camera camera) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, SpriteComponent.class));
        this.camera = camera;
    }

    protected void process(Entity e) {
        if (visibleMapper.has(e) && !visibleMapper.get(e).isVisible()) {
            return;
        }

        PositionComponent positionComponent = positionMapper.get(e);
        Vector3 size = sizeMapper.get(e).getPoint();
        TextureAtlas.AtlasRegion sprite = spriteMapper.get(e).getSprite();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        float x = positionComponent.getPoint().x - size.x / 2f;
        float y = positionComponent.getPoint().y - size.y / 2f;
        spriteBatch.setColor(tintMapper.has(e) ? tintMapper.get(e).getTint() : Color.WHITE);
        if (rotationMapper.has(e)) {
            Vector3 rotation = rotationMapper.get(e).getPoint();
            spriteBatch.draw(sprite, x, y, size.x / 2f, size.y / 2f, size.x, size.y, 1f, 1f, rotation.x);
        } else {
            spriteBatch.draw(sprite, x, y, size.x, size.y);
        }
        spriteBatch.end();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        ArrayList<PositionComponent> positions = new ArrayList<PositionComponent>(entities.size());
        Map<PositionComponent, Entity> map = new HashMap<PositionComponent, Entity>();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            PositionComponent position = positionMapper.get(entity);
            positions.add(position);
            map.put(position, entity);
        }
        Collections.sort(positions);
        for (PositionComponent position : positions) {
            process(map.get(position));
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
