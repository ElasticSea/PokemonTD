package com.xkings.pokemontd.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.graphics.Shader;

/**
 * Created by Tomas on 11/6/13.
 */
public class AggresiveGaussianBlurRenderer implements Renderable {
    private final Renderable wrappedRenderer;
    private final float blurRatio;
    private final SpriteBatch spriteBatch;
    private final ShaderProgram blurShader;
    private final FrameBuffer fboA;
    private final FrameBuffer fboB;
    private final int width;
    private final int height;
    private Texture tex;
    private float scale;

    public AggresiveGaussianBlurRenderer(Renderable wrappedRenderer, float blurRatio) {
        this.wrappedRenderer = wrappedRenderer;
        this.blurRatio = blurRatio;
        this.spriteBatch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        blurShader = Shader.getShader("gaussianBlur");
        fboA = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        fboB = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        scale = Math.min(width, height) / 1000f;
    }

    @Override
    public void render() {
        Texture blurredTexture = getBlurredTexture();
        spriteBatch.begin();
        spriteBatch.setShader(null);
        spriteBatch.draw(blurredTexture, 0, 0, width, height, 0, 0, width, height, false, true);
        spriteBatch.end();
    }

    private Texture getBlurredTexture() {
        fboA.begin();
        wrappedRenderer.render();
        fboA.end();
        int passes = (int) (blurRatio* scale);
        Texture tex = fboA.getColorBufferTexture();
        spriteBatch.setShader(blurShader);
        for (int i = 0; i < passes; i++) {
            tex = blurTexture(tex, i);
        }

        return tex;
    }

    private Texture blurTexture(Texture texture, int i) {
        fboB.begin();
        spriteBatch.begin();
        blurShader.setUniformf("resolution", width);
        blurShader.setUniformf("radius", i );
        blurShader.setUniformf("dir", 1, 0);
        spriteBatch.draw(texture, 0, 0, width, height);
        spriteBatch.end();
        fboB.end();
        fboA.begin();
        spriteBatch.begin();
        blurShader.setUniformf("resolution", height);
        blurShader.setUniformf("radius", i );
        blurShader.setUniformf("dir", 0, 1);
        spriteBatch.draw(fboB.getColorBufferTexture(), 0, 0, width, height);
        spriteBatch.end();
        fboA.end();
        return fboA.getColorBufferTexture();
    }
}

