package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.TextComponent;
import com.xkings.pokemontd.component.TintComponent;

import static com.xkings.core.main.Assets.getTexture;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderTextSystem extends EntityProcessingSystem {
    private final Camera camera;
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final BitmapFont font =
            new BitmapFont(Gdx.files.internal("data/fonts/pixelFont.fnt"), getTexture("pixelFont"), false);

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<TextComponent> textMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public RenderTextSystem(Camera camera) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, TextComponent.class));
        this.camera = camera;
        this.font.setScale(0.05f);
    }

    @Override
    protected void process(Entity e) {
        PositionComponent positionComponent = positionMapper.get(e);
        //Vector3 size = sizeMapper.get(e).getPoint();

        String text = textMapper.get(e).getText();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        BitmapFont.TextBounds fontBounds = font.getBounds(text);
        float x = positionComponent.getPoint().x - fontBounds.width / 2f;
        float y = positionComponent.getPoint().y + fontBounds.height / 2f;
        spriteBatch.setColor(tintMapper.has(e) ? tintMapper.get(e).getTint() : Color.WHITE);
        font.draw(spriteBatch, text, x, y);
        spriteBatch.end();
    }

}
