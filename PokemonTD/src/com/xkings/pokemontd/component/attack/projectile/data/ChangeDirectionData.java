package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class ChangeDirectionData extends EffectData {

    private final float duration;

    public ChangeDirectionData(float duration, float chance) {
        super("", chance);
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }
    public String setText(){
        return "asdasd";
    }
}
