package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.pokemontd.graphics.ui.DisplayBlock;

/**
 * Created by Tomas on 11/19/13.
 */
class ScrollableChildTab extends ChildTab {

    private DisplayBlock content;

    ScrollableChildTab(Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);
        this.setRenderLines(false);
        fixPosition();
    }

    private void fixPosition() {
        if (content != null) {
            Vector2 overlap = new Vector2(content.getWidth() - width, content.getHeight() - (height - close.height));
            content.x = MathUtils.clamp(content.x, x, x + overlap.x);
            content.y = MathUtils.clamp(content.y, y, y + overlap.y);
            content.refresh();
        }
    }

    @Override
    public void pan(float x, float y, float deltaX, float deltaY) {
        content.x += deltaX;
        content.y -= deltaY;
        fixPosition();
        super.pan(x, y, deltaX, deltaY);
    }

    public void setContent(DisplayBlock content) {
        this.content = content;
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int) x, (int) (close.y + close.height), (int) width, (int) (height - close.height));
        content.render();
        Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
    }
}
