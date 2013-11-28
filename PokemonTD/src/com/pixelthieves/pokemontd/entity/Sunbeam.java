package com.pixelthieves.pokemontd.entity;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.RotationComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.component.TargetComponent;
import com.pixelthieves.core.entity.ConcreteEntity;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.SpriteComponent;
import com.pixelthieves.pokemontd.component.TintComponent;
import com.pixelthieves.pokemontd.tween.ColorAccessor;


/**
 * Created by Seda on 10/5/13.
 */
public class Sunbeam extends ConcreteEntity {

    private final PositionComponent position;
    private final TintComponent color;

    private Sunbeam(World world, float x, float y, float sizeX, float sizeY, Entity target) {
        super(world);
        color = new TintComponent(Color.WHITE);
        position = new PositionComponent(x, y, 0);
        addComponent(color);
        addComponent(position);
        addComponent(new SizeComponent(sizeX, sizeY, 0));
        RotationComponent component = new RotationComponent(0, 0, 0);
        addComponent(component);
        component.getOrigin().set(0, 0.5f, 0);
        addComponent(new SpriteComponent("sunbeam"));
        addComponent(new TargetComponent(target));

    }

    public static void registerSunbeam(World world, float x, float y, float sizeX, float sizeY, Entity target) {
        final Sunbeam sunbeam = new Sunbeam(world, x, y, sizeX, sizeY, target);
        sunbeam.register();


        TweenCallback callback = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                sunbeam.entity.deleteFromWorld();
            }
        };
        Tween.to(sunbeam.color.getTint(), ColorAccessor.A, 1).target(0).setCallback(callback).start(
                App.getTweenManager());

    }
}
