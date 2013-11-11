package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/4/13.
 */
public class EffectComponent extends Component {

    private final EffectName name;

    public EffectComponent(EffectName name) {
        this.name = name;
    }

    public EffectName getName() {
        return name;
    }
}
