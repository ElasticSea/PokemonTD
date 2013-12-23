package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class SimpleGuiBox extends InteractiveBlockParent {

    public static final float darker = 0.0f;
    public static final Color darkerColor = new Color(darker, darker, darker, 1f);
    protected final ShapeRenderer shapeRenderer;
    protected final SpriteBatch spriteBatch;
    protected final Gui ui;

    public SimpleGuiBox(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        this.ui = ui;
        this.shapeRenderer = ui.getShapeRenderer();
        this.spriteBatch = ui.getSpriteBatch();
    }

    @Override
    public void render() {
        super.render();
        drawRect();
    }

    private void drawRect() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(darkerColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    @Override
    public void process(float x, float y) {

    }

    @Override
    public void refresh() {

    }
}