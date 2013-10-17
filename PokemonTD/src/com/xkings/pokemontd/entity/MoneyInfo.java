package com.xkings.pokemontd.entity;

import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.TextComponent;
import com.xkings.pokemontd.component.TintComponent;


/**
 * Created by Tomas on 10/5/13.
 */
public class MoneyInfo extends ConcreteEntity {

    private MoneyInfo(World world, int money, float x, float y) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new SizeComponent(1f, 0, 0));
        addComponent(new TintComponent(Color.YELLOW));
        addComponent(new TextComponent(String.valueOf(money)));
    }

    public static void registerMoneyInfo(World world, int money, float x, float y) {
        MoneyInfo moneyInfo = new MoneyInfo(world, money, x, y);
        moneyInfo.register();
    }
}
