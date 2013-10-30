package com.xkings.pokemontd;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Tomas on 10/25/13.
 */
public class Animation {
    private int position;
    private final Array<TextureAtlas.AtlasRegion> sprites;
    private int filter;


    public Animation(TextureAtlas.AtlasRegion... sprites) {
        this(new Array<TextureAtlas.AtlasRegion>(sprites));
    }

    public Animation(Array<TextureAtlas.AtlasRegion> sprites) {
        this.sprites = sprites;
    }

    public TextureAtlas.AtlasRegion getSprite() {
        return sprites.get(position);
    }

    public TextureAtlas.AtlasRegion next() {
        if (++filter > 5) {
            filter = 0;
            position = ++position % sprites.size;
        }
        return getSprite();
    }

    public boolean hasNext(){
        return sprites.size > position;
    }
}
