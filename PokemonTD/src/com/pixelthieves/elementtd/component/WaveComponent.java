package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.pixelthieves.elementtd.manager.WaveManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WaveComponent extends Component {

    private final List<Entity> wave = new LinkedList<Entity>();
    private final WaveManager waveManager;
    private int id;

    public WaveComponent(WaveManager waveManager, int id) {
        this.waveManager = waveManager;
        this.id = id;
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
        return id;
    }

    public List<Entity> getWave() {
        return Collections.unmodifiableList(wave);
    }
}
