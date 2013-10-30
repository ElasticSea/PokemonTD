package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Animation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/4/13.
 */
public class SpriteComponent extends Component {

    Map<String, Animation> map = new LinkedHashMap<String, Animation>();
    private Animation defaultAnimation;

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
    }

    public Animation remove(String type) {
        return map.remove(type);
    }

    public Animation get(String type) {
        return map.get(type);
    }

    public Collection<Animation> get() {
        return map.values();
    }


}
