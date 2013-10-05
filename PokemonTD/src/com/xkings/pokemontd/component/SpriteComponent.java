package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/4/13.
 */
public class SpriteComponent extends Component {

    private final Array<AtlasRegion> sprites;
    private int position;

    public SpriteComponent(AtlasRegion... sprites) {
        this(new Array<AtlasRegion>(sprites));
    }

    public SpriteComponent(Array<AtlasRegion> sprites) {
        this.sprites = sprites;
    }

    public AtlasRegion getSprite() {
        return sprites.get(position);
    }

    public void next() {
        position = position + 1 % sprites.size;
    }
}
