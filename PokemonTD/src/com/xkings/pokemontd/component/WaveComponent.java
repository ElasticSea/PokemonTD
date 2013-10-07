package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.xkings.pokemontd.entity.Creep;

import java.util.LinkedList;
import java.util.List;

public class WaveComponent extends Component {

    private final List<Entity> wave = new LinkedList<Entity>();


    public boolean addCreep(Entity creep) {
        return wave.add(creep);
    }

    public boolean removeCreep(Entity creep) {
        return wave.remove(creep);
    }

    public boolean isEmpty(){
        return wave.size() == 0;
    }

}
