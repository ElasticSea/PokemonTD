package com.pixelthieves.pokemontd.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.core.graphics.Shaders;
import com.pixelthieves.pokemontd.App;

/**
 * Created by Tomas on 11/6/13.
 */
public class BoxBlurRenderer implements Renderable {
    public static final float FBO_SCALE = 1f;
    private final Renderable wrappedRenderer;
    private final SpriteBatch spriteBatch;
    private final ShaderProgram blurShader;
    private final FrameBuffer fbo;
    private final int width;
    private final int height;
    private final int fboWidth;
    private final int fboHeight;

    public BoxBlurRenderer(Renderable wrappedRenderer) {
        this.wrappedRenderer = wrappedRenderer;
        this.spriteBatch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        fboWidth = (int) (width * FBO_SCALE);
        fboHeight = (int) (height * FBO_SCALE);
        blurShader = App.getShaders().getShader("boxBlur");
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, fboWidth, fboHeight, false);
    }

    @Override
    public void render() {
        fbo.begin();
        wrappedRenderer.render();
        fbo.end();
        spriteBatch.setShader(blurShader);
        spriteBatch.begin();
        spriteBatch.draw(fbo.getColorBufferTexture(), 0, 0, width, height);
        spriteBatch.end();
    }
}
