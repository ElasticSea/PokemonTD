package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
class GuiBox extends InteractiveBlock {

    public static final float darker = 0.0f;
    public static final float lighter = 0.1f;
    public static final Color darkerColor = new Color(darker, darker, darker, 1f);
    public static final Color lighterColor = new Color(lighter, lighter, lighter, 1f);
    protected final int offset;
    private final ShapeRenderer shapeRenderer;
    protected final Rectangle offsetRectange;

    GuiBox(Rectangle rectangle, int offset, ShapeRenderer shapeRenderer) {
        super(rectangle);
        this.offsetRectange = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
        this.offset = offset;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void render() {
        drawRect(offset);
    }

    private void drawRect(int offset) {
       // Gdx.gl.glEnable(GL10.GL_BLEND);
     //   Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(darkerColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(lighterColor);
        shapeRenderer.rect(x + offset, y + offset, width - offset * 2, height - offset * 2);
        shapeRenderer.end();
      //  Gdx.gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void process(float x, float y) {

    }
}