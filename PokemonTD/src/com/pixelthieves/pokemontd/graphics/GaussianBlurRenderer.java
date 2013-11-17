package com.pixelthieves.pokemontd.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.core.graphics.Shader;

/**
 * Created by Tomas on 11/6/13.
 */
public class GaussianBlurRenderer implements Renderable {
    private final float FBO_SCALE;
    private final Renderable wrappedRenderer;
    private final float blurRatio;
    private final SpriteBatch spriteBatch;
    private final ShaderProgram blurShader;
    private final FrameBuffer fboA;
    private final FrameBuffer fboB;
    private final int width;
    private final int height;
    private final int fboWidth;
    private final int fboHeight;

    public GaussianBlurRenderer(Renderable wrappedRenderer, float blurRatio) {
        this.wrappedRenderer = wrappedRenderer;
        this.blurRatio = blurRatio;
        this.spriteBatch = new SpriteBatch();
        FBO_SCALE = 1 ;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        fboWidth = (int) (width * FBO_SCALE);
        fboHeight = (int) (height * FBO_SCALE);
        blurShader = Shader.getShader("gaussianBlur");
        fboA = new FrameBuffer(Pixmap.Format.RGBA8888, fboWidth, fboHeight, false);
        fboB = new FrameBuffer(Pixmap.Format.RGBA8888, fboWidth, fboHeight, false);
    }

    @Override
    public void render() {
        fboA.begin();
        wrappedRenderer.render();
        fboA.end();
        fboB.begin();
        spriteBatch.setShader(blurShader);
        spriteBatch.begin();
        blurShader.setUniformf("resolution", fboWidth);
        blurShader.setUniformf("radius", blurRatio * FBO_SCALE);
        blurShader.setUniformf("dir", 1, 0);
        spriteBatch.draw(fboA.getColorBufferTexture(), 0, 0, width, height);
        spriteBatch.end();
        fboB.end();
        spriteBatch.setShader(blurShader);
        spriteBatch.begin();
        blurShader.setUniformf("resolution", fboHeight);
        blurShader.setUniformf("radius", blurRatio * FBO_SCALE);
        blurShader.setUniformf("dir", 0, 1);
        spriteBatch.draw(fboB.getColorBufferTexture(), 0, 0, width, height);
        spriteBatch.end();
    }
}
