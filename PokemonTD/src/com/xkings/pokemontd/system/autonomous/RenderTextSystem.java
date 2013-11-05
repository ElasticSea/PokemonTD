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
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.TextComponent;
import com.xkings.pokemontd.component.TintComponent;

import static com.xkings.core.main.Assets.getTexture;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderTextSystem extends EntityProcessingSystem {
    private final SpriteBatch spriteBatch;
    private final BitmapFont font = Assets.createFont("pixelFont");

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TextComponent> textMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public RenderTextSystem(SpriteBatch spriteBatch) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, TextComponent.class));
        this.spriteBatch = spriteBatch;
        font.setScale(5);
    }

    @Override
    protected void process(Entity e) {
        PositionComponent positionComponent = positionMapper.get(e);
        String text = textMapper.get(e).getText();
        BitmapFont.TextBounds fontBounds = font.getBounds(text);
        float x = positionComponent.getPoint().x - fontBounds.width / 2f;
        float y = positionComponent.getPoint().y + fontBounds.height / 2f;
        font.setColor(tintMapper.has(e) ? tintMapper.get(e).getTint() : Color.WHITE);
        font.draw(spriteBatch, text, x, y);
    }

}
