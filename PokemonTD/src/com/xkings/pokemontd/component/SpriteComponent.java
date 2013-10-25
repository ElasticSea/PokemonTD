package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Animation;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Tomas on 10/4/13.
 */
public class SpriteComponent extends Component {

    public SpriteComponent(TextureAtlas.AtlasRegion texture) {
        this.add(Type.NORMAL, new Animation(texture));
       // this.add(Type.EFFECT, new Animation(Assets.getTexture("fireAnimation")));
    }

    public TextureAtlas.AtlasRegion getSprite() {
        return spriteMap.get(Type.NORMAL).getSprite();
    }


    public enum Type {
        NORMAL, EFFECT;
    }

    private final Map<Type, Animation> spriteMap = new EnumMap<Type, Animation>(Type.class);

    public void add(Type type, Animation a) {
        spriteMap.put(type, a);
    }

    public Animation remove(Type type) {
        return spriteMap.remove(type);
    }

    public Animation get(Type type) {
        return spriteMap.get(type);
    }

}
