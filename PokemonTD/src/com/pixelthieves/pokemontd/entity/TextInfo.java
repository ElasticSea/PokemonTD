package com.pixelthieves.pokemontd.entity;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.entity.ConcreteEntity;
import com.pixelthieves.core.tween.Vector3Accessor;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.component.TextComponent;
import com.pixelthieves.pokemontd.component.TintComponent;
import com.pixelthieves.pokemontd.tween.ColorAccessor;


/**
 * Created by Tomas on 10/5/13.
 */
public class TextInfo extends ConcreteEntity {

    private final TintComponent tint;
    private final PositionComponent position;

    private TextInfo(World world, String text, Color color, float x, float y) {
        super(world);
        tint = new TintComponent(color);
        position = new PositionComponent(x, y, 0);
        addComponent(position);
        addComponent(new SizeComponent(1f, 0, 0));
        addComponent(tint);
        addComponent(new TextComponent(text));

    }

    public static void registerTextInfo(World world, String text, Color color, float x, float y) {
        final TextInfo textInfo = new TextInfo(world, text, color, x, y);
        textInfo.register();

        TweenCallback callback = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                textInfo.entity.deleteFromWorld();
            }
        };
        Tween.to(textInfo.position.getPoint(), Vector3Accessor.VECTOR_Y, 1).target(
                textInfo.position.getPoint().y + 10).start(App.getTweenManager());
        Tween.to(textInfo.tint.getTint(), ColorAccessor.A, 1).target(0).setCallback(callback).start(
                App.getTweenManager());
    }


    public static void registerMoneyInfo(World world, int money, float x, float y) {
        registerTextInfo(world, "+" + money, Color.YELLOW, x, y);
    }


    public static void registerElementInfo(World world, int count, Element element, float x, float y) {
        registerTextInfo(world, "+" + count + " " + element, element.getColor(), x, y);
    }

    public static void registerLifeStealInfo(World world, int lifeSteal, float x, float y) {
        registerTextInfo(world, "+" + lifeSteal + " <3", Color.RED, x, y);
    }
}
