package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.entity.Creep;

import java.util.LinkedList;
import java.util.List;

public class WaveComponent extends Component {

    private final List<Creep> wave = new LinkedList<Creep>();


    public void addCreep(Creep creep) {
        wave.add(creep);
    }

    public void removeCreep(Creep creep) {
        wave.remove(creep);
    }

}
