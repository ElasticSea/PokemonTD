package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;

import java.util.ArrayList;

/**
 * User: Seda
 * Date: 24.10.13
 * Time: 20:42
 */

public class CommonInfo extends InteractiveBlock {
    protected final Ui ui;
    protected final SpriteBatch spriteBatch;
    protected final BitmapFont pixelFont;
    protected final DisplayText name;
    private DisplayPicture picture;
    private TextureAtlas.AtlasRegion regionCache;
    private String nameCache;
    protected final ArrayList<Clickable> clickables = new ArrayList<Clickable>();

    public CommonInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                      BitmapFont font) {
        super(rectangle);
        float scale = height / App.FONT_SCALE2;
        this.spriteBatch = spriteBatch;
        this.ui = ui;
        this.pixelFont = App.getAssets().getPixelFont();
        float offset = height / 5;
        picture = new DisplayPicture(ui,x + offset, y + offset, height - offset * 2, height - offset * 2);
        name = new DisplayText(ui,new Rectangle(x + offset, y + offset, height - offset * 2, offset), font, BitmapFont.HAlignment.CENTER);
    }

    @Override
    public void render() {
        App.getAssets().getPixelFont().setScale(height / 8 / 32);
        picture.render(regionCache);
        name.render(nameCache);
    }

    public void render(TextureAtlas.AtlasRegion region, String name) {
        this.regionCache = region;
        this.nameCache = name;
        render();
    }

    @Override
    public void setEnabled(boolean enabled) {
        for (Clickable clickable : clickables) {
            clickable.setEnabled(enabled);
        }
    }

    @Override
    public void process(float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
