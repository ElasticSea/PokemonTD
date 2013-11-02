package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

/**
 * User: Seda
 * Date: 2.11.13
 * Time: 11:15
 */

public class AbilityInfo extends GuiBox {
    public AbilityInfo(Ui ui, Rectangle rectangle){
        super(ui, rectangle);
    }

    public void update(EffectData abilityCashe, float speed, float damage) {
        System.out.println(abilityCashe.getEffectDescription(speed,damage));
    }
}
