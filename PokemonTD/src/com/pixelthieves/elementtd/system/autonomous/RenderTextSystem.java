package com.pixelthieves.elementtd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.component.TextComponent;
import com.pixelthieves.elementtd.component.TintComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderTextSystem extends EntityProcessingSystem {
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TextComponent> textMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public RenderTextSystem(SpriteBatch spriteBatch) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, TextComponent.class));
        this.font = App.getAssets().createFont("pixelFont");
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
