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
public class ConvolutionRenderer implements Renderable {
    public enum Type {
        BLUR;
    }

    public static final float FBO_SCALE = 1f;
    private final Renderable wrappedRenderer;
    private final SpriteBatch spriteBatch;
    private final ShaderProgram shader;
    private final FrameBuffer fbo;
    private final int width;
    private final int height;
    private final int fboWidth;
    private final int fboHeight;
    private Type type = Type.BLUR;

    public ConvolutionRenderer(Renderable wrappedRenderer) {
        this.wrappedRenderer = wrappedRenderer;
        this.spriteBatch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        fboWidth = (int) (width * FBO_SCALE);
        fboHeight = (int) (height * FBO_SCALE);
        shader = App.getShaders().getShader("convolution");
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, fboWidth, fboHeight, false);
    }

    @Override
    public void render() {
        fbo.begin();
        wrappedRenderer.render();
        fbo.end();
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        fillShaderData(shader);
        spriteBatch.draw(fbo.getColorBufferTexture(), 0, 0, width, height);
        spriteBatch.end();
    }

    private void fillShaderData(ShaderProgram shader) {
        switch (type) {
            case BLUR:
                fillBlurData(shader);
                break;
        }
    }

    private void fillBlurData(ShaderProgram shader) {
        shader.setUniformf("conPixel", 1f / width, 1f / height);
        float[] conMatrix = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        for (int i = 0; i < conMatrix.length; i++) {
            shader.setUniformf("conMatrix[" + i + "]", conMatrix[i]);
        }
        shader.setUniformf("conWeight", 1.0f / 9.0f);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
