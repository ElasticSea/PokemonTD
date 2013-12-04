package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.Animation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/4/13.
 */
public class SpriteComponent extends Component {

    Map<String, Animation> map = new LinkedHashMap<String, Animation>();
    private Animation defaultAnimation;
    private boolean dirty = true;
    private Animation[] values;

    public SpriteComponent(String type) {
        add(type);
    }

    public SpriteComponent(TextureAtlas.AtlasRegion texture) {
        this(texture.name);
    }

    public TextureAtlas.AtlasRegion getSprite() {
        return defaultAnimation.getSprite();
    }

    public void add(String type) {
        Animation a = new Animation(Assets.getTextureArray(type));
        if (map.isEmpty()) {
            defaultAnimation = a;
        }
        map.put(type, a);
        this.dirty = true;
    }

    public Animation remove(String type) {
        this.dirty = true;
        return map.remove(type);
    }

    public Animation get(String type) {
        return map.get(type);
    }

    public Animation[] get() {
        if (dirty) {
            values = new Animation[map.size()];
            int i = 0;
            for (Animation animation : map.values()) {
                values[i++] = animation;
            }
            dirty = false;
        }
        return values;
    }

    public void clear(){
        map = new LinkedHashMap<String, Animation>();
        dirty = true;
    }


}
