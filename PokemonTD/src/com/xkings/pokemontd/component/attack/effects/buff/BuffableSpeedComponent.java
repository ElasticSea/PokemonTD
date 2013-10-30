package com.xkings.pokemontd.component.attack.effects.buff;

import com.xkings.core.component.SpeedComponent;

/**
 * Created by Tomas on 10/30/13.
 */
public class BuffableSpeedComponent extends SpeedComponent {
    private SpeedBuffEffect buff;

    public BuffableSpeedComponent(float speed) {
        super(speed);
    }

    public void setBuff(SpeedBuffEffect buff) {
        this.buff = buff;
    }

    public SpeedBuffEffect getBuff() {
        return buff;
    }

    @Override
    public float getSpeed() {
        return super.getSpeed() / (buff != null ? buff.getRatio() : 1);
    }
}
