package com.xkings.pokemontd.entity;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.TargetComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TintComponent;
import com.xkings.pokemontd.tween.ColorAccessor;


/**
 * Created by Tomas on 10/5/13.
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
        addComponent(new SpriteComponent(Assets.getTexture("sunbeam")));
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
