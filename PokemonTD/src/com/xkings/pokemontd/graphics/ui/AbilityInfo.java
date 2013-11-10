package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

/**
 * User: Seda
 * Date: 2.11.13
 * Time: 11:15
 */

public class AbilityInfo extends GuiBox {
    private final DisplayText text;
    private EffectData abilityCache;
    private float speed;
    private float damage;

    public AbilityInfo(Ui ui, Rectangle rectangle) {
        super(ui, rectangle);
        text = new DisplayText(ui, offsetRectange, ui.getFont(), BitmapFont.HAlignment.LEFT, true);
    }

    public void update(EffectData abilityCache, float speed, float damage) {
        this.abilityCache = abilityCache;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render() {
        if (abilityCache != null) {
            super.render();
            text.render(abilityCache.getEffectDescription(speed, damage));
        }
    }

    public void reset() {
        this.abilityCache = null;
    }
}
