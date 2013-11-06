package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class AoeComponent extends EffectData {
    private final float range;

    public AoeComponent(float range) {
        super();
        this.range = range;
    }

    public float getRange() {
        return range;
    }
    public String getEffectDescription(float speed,float damage){
        return "asdasd";
    }
}
