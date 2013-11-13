package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.xkings.pokemontd.manager.WaveManager;

import java.util.LinkedList;
import java.util.List;

public class WaveComponent extends Component {

    private final List<Entity> wave = new LinkedList<Entity>();
    private final WaveManager waveManager;

    public WaveComponent(WaveManager waveManager) {
        this.waveManager = waveManager;
    }

    public boolean addCreep(Entity creep) {
        return wave.add(creep);
    }

    public boolean removeCreep(Entity creep) {
        boolean remove = wave.remove(creep);
        if (remove && isEmpty()) {
            waveManager.removeWave(this);
        }
        return remove;
    }

    public boolean isEmpty() {
        return wave.size() == 0;
    }

    public int getId() {
        return !isEmpty() ? wave.get(0).getId() : -1;
    }

}
