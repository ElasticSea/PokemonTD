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
    private final DisplayText header;
    private final DisplayText text;
    private EffectData abilityCache;
    private float speed;
    private float damage;
    private String effectName;

    public AbilityInfo(Ui ui, Rectangle rectangle) {
        super(ui, rectangle);
        int headerSize = (int) (height / 5);
        float textBlockSize = height - headerSize;
        text = new DisplayText(ui, new Rectangle(x, y, width, textBlockSize), ui.getFont(), BitmapFont.HAlignment.LEFT,
                true);
        header = new DisplayText(ui, new Rectangle(x, textBlockSize, width, headerSize), ui.getFont(),
                BitmapFont.HAlignment.LEFT, true);
        header.setScale(ui.getFont().getScaleX() * 2);
        refresh();
    }

    public void update(EffectData abilityCache, String effectName, float speed, float damage) {
        this.abilityCache = abilityCache;
        this.effectName = effectName;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render() {
        if (abilityCache != null) {
            super.render();
            header.render(effectName);
            text.render(abilityCache.getEffectDescription(speed, damage));
        }
    }

    public void reset() {
        this.abilityCache = null;
    }

    @Override
    public void refresh() {
        super.refresh();
        int headerSize = (int) (height / 5);
        float offset = (int) (height / 7);
        Rectangle innerRectangle = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
        float textBlockSize = innerRectangle.height - headerSize;
        text.set(innerRectangle.x, innerRectangle.y, innerRectangle.width, textBlockSize);
        header.set(innerRectangle.x, innerRectangle.y+textBlockSize, innerRectangle.width, headerSize);
        text.refresh();
        header.refresh();
    }
}
