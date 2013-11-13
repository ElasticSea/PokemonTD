package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.component.attack.EffectName;
import com.xkings.pokemontd.component.attack.projectile.data.BonusAttack;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;
import com.xkings.pokemontd.component.attack.projectile.data.LifeStealData;
import com.xkings.pokemontd.component.attack.projectile.data.MoneyData;

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
    private EffectName effectName;

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

    public void update(EffectData abilityCache, EffectName effectName, float speed, float damage) {
        this.abilityCache = abilityCache;
        this.effectName = effectName;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render() {
        if (effectName != null) {
            super.render();
            header.render(effectName.name());
            text.render(getDescription(effectName, abilityCache, damage));
        }
    }

    private String getDescription(EffectName effectName, EffectData abilityCache, float damage) {
        switch (effectName) {
            case Normal:
                return "Deals " + (int) (damage) + " damage.";
            case Splash:
                break;
            case Wave:
                break;
            case Burn:
                break;
            case Entangle:
                break;
            case Peck:
                BonusAttack bonusAttack = (BonusAttack) abilityCache;
                return (int) (bonusAttack.getChance() * 100) + "% chance to deal " + bonusAttack.getIterations() +
                        "X aditional " + (int) damage + " damage";
            case Terrify:
                break;
            case Haste:
                return "Increases speed of nearby towers by " + (int) ((damage - 1) * 100) + "%.";
            case Sunbeam:
                break;
            case Weaken:
                break;
            case Freeze:
                break;
            case Incinerate:
                break;
            case Stomp:
                break;
            case Steal:
                MoneyData money = (MoneyData) abilityCache;
                return "Killed creeps earn " + (int) ((money.getEarnRatio() - 1) * 100) + "% more money.";
            case Boulder:
                break;
            case Impair:
                break;
            case Charm:
                return "Increases damage of nearby towers by " + (int) ((damage - 1) * 100) + "%.";
            case Shatter:
                break;
            case Blaze:
                break;
            case Corrode:
                break;
            case LifeSteal:
                LifeStealData lifeSteal = (LifeStealData) abilityCache;
                return (int) (lifeSteal.getChance() * 100) + "% chance to earn life from killed creep, instead of money.";
            case Infect:
                break;
            case SoundWave:
                break;
            case Thunderbolt:
                break;
            case Quake:
                break;
            case Magma:
                break;
            case Puzzle:
                break;
        }
        return "";
    }

    public void reset() {
        this.abilityCache = null;
        this.effectName = null;
    }

    @Override
    public void refresh() {
        super.refresh();
        int headerSize = (int) (height / 5);
        float offset = (int) (height / 7);
        Rectangle innerRectangle = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
        float textBlockSize = innerRectangle.height - headerSize;
        text.set(innerRectangle.x, innerRectangle.y, innerRectangle.width, textBlockSize);
        header.set(innerRectangle.x, innerRectangle.y + textBlockSize, innerRectangle.width, headerSize);
        text.refresh();
        header.refresh();
    }
}
