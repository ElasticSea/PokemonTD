package com.xkings.pokemontd.entity.datatypes;

import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class StaticObjectType implements CommonDataType {

    public static final StaticObjectType GRAVE = new StaticObjectType("grave", App.WORLD_SCALE/2f);
    private final AtlasRegion texture;
    private final float size;

    private StaticObjectType(String texture, float size) {
        this.texture = Assets.getTexture(texture);
        this.size = size;
    }

    public AtlasRegion getTexture() {
        return texture;
    }

    public float getSize() {
        return size;
    }


}
