package com.xkings.pokemontd.component;

import com.artemis.Component;

public class AttackComponent extends Component {

    private int attack;

    public AttackComponent(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }
}
