package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Seda on 10/8/13.
 */
public class GuiBox extends InteractiveBlockParent {

    public static final float darker = 0.0f;
    public static final float lighter = 0.1f;
    public static final Color darkerColor = new Color(darker, darker, darker, 1f);
    public static final Color lighterColor = new Color(lighter, lighter, lighter, 1f);
    protected int offset;
    protected final ShapeRenderer shapeRenderer;
    protected final Rectangle offsetRectange;
    protected final SpriteBatch spriteBatch;
    protected final Gui ui;

    public GuiBox(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        this.ui = ui;
        this.offset = ui.getOffset();
        this.shapeRenderer = ui.getShapeRenderer();
        this.spriteBatch = ui.getSpriteBatch();
        this.offsetRectange = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
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
        shapeRenderer.setColor(lighterColor);
        shapeRenderer.rect(offsetRectange.x, offsetRectange.y, offsetRectange.width, offsetRectange.height);
        shapeRenderer.end();
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void refresh() {
        offsetRectange.set(x + offset, y + offset, width - offset * 2, height - offset * 2);
    }

    @Override
    public void process(float x, float y) {

    }
}