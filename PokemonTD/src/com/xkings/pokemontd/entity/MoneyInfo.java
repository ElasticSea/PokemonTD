package com.xkings.pokemontd.entity;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.core.tween.Vector3Accessor;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.TextComponent;
import com.xkings.pokemontd.component.TintComponent;
import com.xkings.pokemontd.tween.ColorAccessor;


/**
 * Created by Tomas on 10/5/13.
 */
public class MoneyInfo extends ConcreteEntity {

    private final TintComponent color;
    private final PositionComponent position;

    private MoneyInfo(World world, int money, float x, float y) {
        super(world);
        color = new TintComponent(Color.YELLOW);
        position = new PositionComponent(x, y, 0);
        addComponent(position);
        addComponent(new SizeComponent(1f, 0, 0));
        addComponent(color);
        addComponent(new TextComponent("+" + String.valueOf(money)));

    }

    public static void registerMoneyInfo(World world, int money, float x, float y) {
        final MoneyInfo moneyInfo = new MoneyInfo(world, money, x, y);
        moneyInfo.register();


        TweenCallback callback = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                moneyInfo.entity.deleteFromWorld();
            }
        };
        Tween.to(moneyInfo.position.getPoint(), Vector3Accessor.VECTOR_Y, 1).target(
                moneyInfo.position.getPoint().y + 10).start(App.getTweenManager());
        Tween.to(moneyInfo.color.getTint(), ColorAccessor.A, 1).target(0).setCallback(callback).start(
                App.getTweenManager());
    }


 /*   private static void translateAtoB(Vector3 a, Vector3 b, float duration, TweenCallback callback) {
        Tween.to(a, Vector3Accessor.XYZ, duration).target(b.x, b.y, b.z).setCallback(callback).start(App.getTweenManager());
    }    */
}
