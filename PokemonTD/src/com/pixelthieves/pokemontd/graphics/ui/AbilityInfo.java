package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.component.attack.effects.AbstractEffect;
import com.pixelthieves.pokemontd.component.attack.projectile.data.*;

/**
 * User: Seda
 * Date: 2.11.13
 * Time: 11:15
 */

public class AbilityInfo extends HeaderGuiBox {
    private final DisplayText text;
    private EffectData ability;
    private float speed;
    private float damage;
    private EffectName abilityName;
    private float splash;
    private AbstractEffect effect;

    public AbilityInfo(Ui ui, Rectangle rectangle) {
        super(ui, rectangle);
        text = new DisplayText(ui, new Rectangle(), ui.getFont(), BitmapFont.HAlignment.LEFT,
                true, new String());
        text.setScale(0.75f);
        refresh();
    }

    public void update(EffectData effect, EffectName effectName, float splash, float speed, float damage) {
        this.ability = effect;
        this.abilityName = effectName;
        this.splash = splash;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render() {
        if (abilityName != null || effect != null) {
            if (abilityName != null) {
                this.setLeftHeaderText(abilityName.name());
            } else {
                this.setLeftHeaderText(effect.getName());
            }
            super.render();
            if (abilityName != null) {
                text.render(getDescription(abilityName, splash, ability, damage));
            } else {
                text.render(effect.getDescription());
            }
        }
    }

    private String getDescription(EffectName effectName, float splash, EffectData abilityCache, float damage) {
        switch (effectName) {
            case Normal:
            case Thunderbolt:
                return "Deals " + (int) (damage) + " damage.";
            case Splash:
            case Shatter:
            case Boulder:
                return "Deals " + (int) (damage) + " damage in " + (int) splash + " radius.";
            case Wave:
            case SoundWave:
                BubbleData bubble = (BubbleData) abilityCache;
                return "Deals " + (int) damage + " every " + speed + " ms on contact." +
                        (bubble.getGrow() != 1 ?
                                "Bubble grows by " + (int) ((bubble.getGrow() - 1) * 100) + "% per second." : "");

            case Burn:
                DotData data = (DotData) abilityCache;
                return "Deals " + data.getIterations() + "x " + (int) (data.getDamage()*damage) + " every " +
                        (int) (data.getInterval() * 1000) +
                        " ms.";

            case Entangle:
                SlowData slow = (SlowData) abilityCache;
                return "Has " + (int) (slow.getChance() * 100) + "% chance to slow creep by " +
                        (int) (((1 - slow.getSlowRatio()) * 100)) + "% for " + (int) slow.getDuration() + " seconds.";

            case Peck:
                BonusAttack bonusAttack = (BonusAttack) abilityCache;
                return (int) (bonusAttack.getChance() * 100) + "% chance to deal " + bonusAttack.getIterations() +
                        "x additional " + (int) damage + " damage";

            case Terrify:
                TempLifeData lifeSteal = (TempLifeData) abilityCache;
                return "Temporary steal " + (int) (damage * 100) + "% of creeps health for " +
                        (int) lifeSteal.getDuration() + "s.";

            case Haste:
                return "Increases speed of nearby towers by " + (int) ((damage - 1) * 100) + "%.";

            case Sunbeam:
                return "Deals " + (int) damage + " every " + speed + " ms on contact with the light beam.";

            case Weaken:
                data = (DotData) abilityCache;
                return "Deals " + data.getIterations() + "X " + (int) (damage) + " every " + (int) data.getInterval() +
                        " ms.";

            case Freeze:
                slow = (SlowData) abilityCache;
                return "Has " + (int) (slow.getChance() * 100) + "% chance to slow creep by " +
                        (int) (((1 - slow.getSlowRatio()) * 100)) + "% for " + (int) slow.getDuration() + " seconds.";

            case Incinerate:
                IncreasingDamageData increaseDamage = (IncreasingDamageData) abilityCache;
                return "While attacking the same creep tower gains damage by " +
                        (int) ((increaseDamage.getDamageIncreasing() - 1) * 100) + "% every " +
                        (int) ((increaseDamage.getDuration() * 1000)) + " ms.";

            case Stomp:
                slow = (SlowData) abilityCache;
                return "Has " + (int) (slow.getChance() * 100) + "% chance to slow creep by " +
                        (int) (((1 - slow.getSlowRatio()) * 100)) + "% for " + (int) slow.getDuration() + " seconds.";

            case Steal:
                MoneyData money = (MoneyData) abilityCache;
                return "Killed creeps earn " + (int) ((money.getEarnRatio() - 1) * 100) + "% more money.";

            case Impair:
                slow = (SlowData) abilityCache;
                return "Has " + (int) (slow.getChance() * 100) + "% chance to slow creep by " +
                        (int) (((1 - slow.getSlowRatio()) * 100)) + "% for " + (int) slow.getDuration() + " seconds.";

            case Charm:
                return "Increases damage of nearby towers by " + (int) ((damage - 1) * 100) + "%.";

            case Blaze:
                bubble = (BubbleData) abilityCache;
                return "Deals " + (damage * bubble.getInterval()) + " every " + speed + " ms on contact." +
                        (bubble.getGrow() != 1 ?
                                "Bubble grows by " + (int) ((bubble.getGrow() - 1) * 100) + "% per second." : "");

            case Corrode:
                return "Tower not yet implemented.";

            case LifeSteal:
                LifeData lifeData = (LifeData) abilityCache;
                return (int) (lifeData.getChance() * 100) +
                        "% chance to earn life from killed creep, instead of money.";

            case Infect:
                return "Deals " + damage + " damage in " + splash + " radius around tower.";

            case Quake:
                slow = (SlowData) abilityCache;
                return "Has " + (int) (slow.getChance() * 100) + "% chance to slow creep by " +
                        (int) (((1 - slow.getSlowRatio()) * 100)) + "% for " + (int) slow.getDuration() + " seconds.";

            case Magma:
                data = (DotData) abilityCache;
                return "Deals " + data.getIterations() + "X " + (int) (damage) + " every " + (int) data.getInterval() +
                        " ms in " + splash + " radius around tower.";

            case Puzzle:
                ChangeDirectionData changeDirectionData = (ChangeDirectionData) abilityCache;
                return (int) (changeDirectionData.getChance() * 100) + "% chance to that hit creep changes direction.";
        }
        return "";
    }

    public void reset() {
        this.ability = null;
        this.abilityName = null;
        this.effect = null;
    }

    @Override
    public void refresh() {
        super.refresh();
        float offset = (int) (height / 10);
        text.set(x + offset, y + offset, width - offset * 2, height - offset * 2);
        text.refresh();
    }

    public void update(AbstractEffect effect) {
        this.effect = effect;
    }

    public boolean isVisible() {
        return abilityName != null || effect != null;
    }
}
