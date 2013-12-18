package com.pixelthieves.pokemontd.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.logic.UpdateFilter;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.core.logic.UpdateableAdapter;
import com.pixelthieves.core.services.Achievement;
import com.pixelthieves.pokemontd.*;
import com.pixelthieves.pokemontd.component.WaveComponent;
import com.pixelthieves.pokemontd.entity.creep.Creep;
import com.pixelthieves.pokemontd.entity.creep.CreepAbilityType;
import com.pixelthieves.pokemontd.entity.creep.CreepType;
import com.pixelthieves.pokemontd.entity.creep.CreepTypeBuilder;
import com.pixelthieves.pokemontd.map.Path;
import com.pixelthieves.pokemontd.map.PathPack;
import com.pixelthieves.pokemontd.system.resolve.FireTextInfo;

import java.util.*;
import java.util.Map;

/**
 * Created by Tomas on 10/5/13.
 */
public class WaveManager implements Updateable {
    private final float initialDelay;

    private final UpdateFilter filter;
    private final Player player;
    private final App app;
    private final float waveInterval;
    private final float betweenWaveInterval;
    private int lastWaveId;
    private boolean active;
    private CreepType nextWave;
    private final List<WaveComponent> waves = new LinkedList<WaveComponent>();

    public static CreepTypeBuilder creepTypeBuilder;
    private static List<CreepType> waveStore;
    private CreepType lastWave;
    private Iterator<CreepType> waveIterator;


    public static CreepType getWave(Element element, int count) {
        return elementWaves.get(element).get(count);
    }

    private static Map<Element, List<CreepType>> elementWaves;

    public static Map<Element, List<CreepType>> getElementWave(Difficulty difficulty) {
        List<CreepType> waves = creepTypeBuilder.build(App.WORLD_SCALE, CreepTypeBuilder.element, difficulty);
        Map<Element, List<CreepType>> map = new HashMap<Element, List<CreepType>>();
        for (CreepType creep : waves) {
            Treasure treasure = creep.getTreasure();
            for (Element element : Element.values()) {
                if (treasure.hasElement(element, 1)) {
                    List<CreepType> list = map.get(element);
                    if (list == null) {
                        list = new ArrayList<CreepType>();
                        map.put(element, list);
                    }
                    list.add(creep);
                    break;
                }
            }
        }
        return map;
    }


    public WaveManager(App app, float waveInterval, float betweenWaveInterval, float initialDelay) {
        this.app = app;
        this.player = app.getPlayer();
        this.waveInterval = waveInterval;
        this.betweenWaveInterval = betweenWaveInterval;
        this.initialDelay = initialDelay;
        this.filter = new UpdateFilter(this, initialDelay);
    }

    public void init(Difficulty difficulty) {
        creepTypeBuilder = new CreepTypeBuilder(app.getAssets());

        waveStore = creepTypeBuilder.build(App.WORLD_SCALE, getWaveStore(app.getMode()), difficulty);
        waveIterator = waveStore.iterator();
        elementWaves = getElementWave(difficulty);
        lastWaveId = waveStore.size() - 1;
        filter.reset(initialDelay);
        updateWave();
        this.active = true;
    }

    private List<CreepTypeBuilder.Specs> getWaveStore(Mode mode) {
        switch (mode) {
            case Classic:
                return CreepTypeBuilder.normal;
            case Endless:
                return CreepTypeBuilder.endless;
        }
        return null;
    }

    public boolean isNextWaveLast() {
        return nextWave != null ? nextWave.getId() == lastWaveId : false;
    }

    private enum State {
        WAVE, BETWEEN;
    }

    private State state = State.BETWEEN;

    @Override
    public void update(float delta) {
        if (nextWave != null) {
            switch (state) {
                case WAVE:
                    filter.setInterval(betweenWaveInterval);
                    state = State.BETWEEN;
                    filter.reset();
                    return;
                case BETWEEN:
                    filter.setInterval(waveInterval);
                    state = State.WAVE;
                    filter.reset();
                    break;
            }
            if (isNextWaveLast() && app.getMode().equals(Mode.Endless)) {
                registerWave(nextWave, true);
            } else {
                registerWave(nextWave, false);
            }
        } else {
            if (waves.isEmpty() && App.STRESS_TEST == null) {
                app.endGame(true);
                if(app.getMode().equals(Mode.Classic)){
                    app.getGameSevice().submitAchievement(Achievement.Champion);
                }
            }
        }
    }

  /*  public WaveComponent fireWave(CreepType next) {

        WaveComponent wave = new WaveComponent(this, next.getId());
        for (int i = 0; i < next.getCreepsInWave(); i++) {
            Path path = getAppropriatePath(app.getMap().getPathPack(), next);
            Vector3 startPoint = path.getPath().get(0);
            Vector3 nextPoint = path.getPath().get(1);
            double angleToNextPoint = Math.atan2(nextPoint.y - startPoint.y, nextPoint.x - startPoint.x);
            float xOffset = (float) (Math.cos(angleToNextPoint + Math.PI) * next.getDistanceBetweenCreeps() * i);
            float yOffset = (float) (Math.sin(angleToNextPoint + Math.PI) * next.getDistanceBetweenCreeps() * i);
            Creep.registerCreep(app.getWorld(), new Path(path), wave, next, startPoint.x + xOffset,
                    startPoint.y + yOffset);
        }
        registerWave(wave);
        updateWave();
        return wave;
    }                    */

    public WaveComponent registerWave(final CreepType next, final boolean endless) {
        final WaveComponent wave = new WaveComponent(this, next.getId());
        Updateable action = new UpdateableAdapter() {
            private static final float LifeGrowCoefficient = 1.01f;
            public float multiplier = 1;

            @Override
            public void update(float delta) {
                Path path = getAppropriatePath(app.getMap().getPathPack(), next);
                Vector3 startPoint = path.getPath().get(0);
                Creep.registerCreep(app.getWorld(), new Path(path), wave, next, startPoint.x, startPoint.y, endless ? multiplier : 1,endless);
                multiplier*= LifeGrowCoefficient;
            }
        };

        float interval = (next.getDistanceBetweenCreeps() / App.WORLD_SCALE) / (next.getSpeed() / App.WORLD_SCALE);
        final UpdateFilter emmiter = endless ? new UpdateFilter(action, interval) :
                new UpdateFilter(action, interval, next.getCreepsInWave());

        emmiter.setListener(new UpdateFilter.Listener() {
            @Override
            public void onEnd() {
                app.unregister(emmiter);
            }
        });
        app.register(emmiter);
        registerWave(wave);
        updateWave();


    /*    for (int i = 0; i < next.getCreepsInWave(); i++) {
            Path path = getAppropriatePath(app.getMap().getPathPack(), next);
            Vector3 startPoint = path.getPath().get(0);
            Vector3 nextPoint = path.getPath().get(1);
            double angleToNextPoint = Math.atan2(nextPoint.y - startPoint.y, nextPoint.x - startPoint.x);
            float xOffset = (float) (Math.cos(angleToNextPoint + Math.PI) * next.getDistanceBetweenCreeps() * i);
            float yOffset = (float) (Math.sin(angleToNextPoint + Math.PI) * next.getDistanceBetweenCreeps() * i);
            Creep.registerCreep(app.getWorld(), new Path(path), wave, next, startPoint.x + xOffset,
                    startPoint.y + yOffset);
        }
        registerWave(wave);
        updateWave();  */
        return wave;
    }


    private void registerWave(WaveComponent wave) {
        waves.add(wave);
    }

    private void unregisterWave(WaveComponent wave) {
        if (wave.getId() == 0) {
            app.getGameSevice().submitAchievement(Achievement.Novice);
        }
        if ((wave.getId() + 1) % 5 == 0) {
            player.addFreeElement();
            app.getWorld().getSystem(FireTextInfo.class).fireText("FREE ELEMENT", Color.GREEN);
        }
        switch (wave.getId() + 1) {
            case 10:
                app.getGameSevice().submitAchievement(Achievement.Novice);
                break;
            case 20:
                app.getGameSevice().submitAchievement(Achievement.Apprentice);
                break;
            case 30:
                app.getGameSevice().submitAchievement(Achievement.Journeyman);
                break;
            case 40:
                app.getGameSevice().submitAchievement(Achievement.Expert);
                break;
            case 50:
                app.getGameSevice().submitAchievement(Achievement.Adept);
                break;
            case 60:
                app.getGameSevice().submitAchievement(Achievement.Master);
                break;
        }
        waves.remove(wave);
    }

    private Path getAppropriatePath(PathPack pathPack, CreepType next) {
        if (next.getAbilityType() == CreepAbilityType.SWARM) {
            return pathPack.get(App.RANDOM.nextInt(pathPack.size()));
        } else {
            return pathPack.getMain();
        }
    }

    private void updateWave() {
        lastWave = nextWave;
        nextWave = waveIterator.hasNext() ? waveIterator.next() : null;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRemainingTime() {
        return (int) filter.getRemainingTime() + 1;
    }

    public CreepType getLastWave() {
        return lastWave;
    }

    public CreepType getNextWave() {
        return nextWave;
    }

    public void removeWave(WaveComponent waveComponent) {
        this.unregisterWave(waveComponent);
        if (waves.isEmpty()) {
            filter.triggerUpdate();
        }
    }

    public void triggerNextWave() {
        if (state.equals(State.WAVE)) {
            app.updateFilters(filter.getRemainingTime());
        }
        app.updateFilters(filter.getRemainingTime());
    }

    public UpdateFilter getFilter() {
        return filter;
    }
}
