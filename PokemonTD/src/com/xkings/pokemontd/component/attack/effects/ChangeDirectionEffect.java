package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class ChangeDirectionEffect extends AbstractEffect<ChangeDirectionEffect> {

    public ChangeDirectionEffect set(String effect, float interval) {
        super.set(effect, interval, 1);
        return this;
    }


    @Override
    public int compareTo(ChangeDirectionEffect o) {
        return 0;
    }

    public String getName(){
        return  "  ";
    }

    public String getDescription(){
        return "  ";
    }
}

