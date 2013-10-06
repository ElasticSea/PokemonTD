package com.xkings.pokemontd.manager;

import com.xkings.core.behavior.task.UpdateFilter;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.UpdateFilter;
import com.xkings.core.logic.Updateable;

/**
 * Created by Tomas on 10/5/13.
 */
public class WaveManager {
    /**
     * @param clock    internal update timer
     * @param interval time in seconds between wave calls
     */
    public WaveManager(Clock clock, float interval) {
        clock.addService(new UpdateFilter(new WaveRepeater(), interval));
    }

    private static class WaveRepeater implements Updateable {

        private boolean active;

        @Override
        public void update(float delta) {
            fireNextWave();
        }

        private void fireNextWave() {

        }

        @Override
        public boolean isActive() {
            return active;
        }

        @Override
        public void setActive(boolean active) {
            this.active = active;
        }
    }
}
