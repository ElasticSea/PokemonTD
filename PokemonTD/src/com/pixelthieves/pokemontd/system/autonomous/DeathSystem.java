package com.pixelthieves.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.DamageComponent;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.*;
import com.pixelthieves.pokemontd.component.*;
import com.pixelthieves.pokemontd.entity.StaticObject;
import com.pixelthieves.pokemontd.entity.TextInfo;
import com.pixelthieves.pokemontd.entity.creep.Creep;
import com.pixelthieves.pokemontd.entity.creep.CreepAbilityType;
import com.pixelthieves.pokemontd.entity.creep.CreepType;
import com.pixelthieves.pokemontd.entity.datatypes.StaticObjectType;
import com.pixelthieves.pokemontd.graphics.ui.menu.Options;
import com.pixelthieves.pokemontd.map.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomas on 10/4/13.
 */
public class DeathSystem extends EntityProcessingSystem {

    private final Player player;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TreasureComponent> treasureMapper;
    @Mapper
    ComponentMapper<CreepAbilityComponent> creepAbilityMapper;
    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<WaveComponent> waveMapper;
    @Mapper
    ComponentMapper<CreepTypeComponent> creepTypeMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;

    private final List<ResurrectTrigger> deathsToProcess = new ArrayList<ResurrectTrigger>();

    public DeathSystem(Player player) {
        super(Aspect.getAspectForAll(HealthComponent.class, TreasureComponent.class, CreepAbilityComponent.class,
                DamageComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity e) {
        Health health = healthMapper.get(e).getHealth();
        if (!health.isAlive()) {
            switch (creepAbilityMapper.get(e).getCreepAbilityType()) {
                case RESURRECT:
                    resurrect(e, 4000);
                    break;
                case SPAWN:
                    spawn(e, 8);
                    break;
                default:
                    earn(e);
                    die(e);
            }
        }
    }

    public void reviveDeath() {
        synchronized (this) {
            for (ResurrectTrigger death : deathsToProcess) {
                death.run();
            }
            deathsToProcess.clear();
        }
    }

    private void addToDeaths(ResurrectTrigger resurrectTrigger) {
        synchronized (this) {
            deathsToProcess.add(resurrectTrigger);
        }
    }

    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    private void resurrect(final Entity e, int delayMs) {
        final Vector3 position = positionMapper.get(e).getPoint();
        final Path path = pathMapper.get(e).getPath();
        final WaveComponent waveComponent = waveMapper.get(e);
        final CreepType creepType = creepTypeMapper.get(e).getCreepType();
        e.deleteFromWorld();
        final Entity grave = StaticObject.registerStaticObject(world, StaticObjectType.GRAVE, position.x, position.y);

        Runnable task = new Runnable() {
            public void run() {
                addToDeaths(new ResurrectTrigger(world, e, position, path, waveComponent, creepType, grave));
            }
        };
        worker.schedule(task, delayMs, TimeUnit.MILLISECONDS);
    }

    private static class ResurrectTrigger {
        private final World world;
        private final Entity e;
        private final Vector3 position;
        private final Path path;
        private final WaveComponent waveComponent;
        private final CreepType creepType;
        private final Entity grave;

        private ResurrectTrigger(World world, Entity e, Vector3 position, Path path, WaveComponent waveComponent,
                                 CreepType creepType, Entity grave) {
            this.world = world;
            this.e = e;
            this.position = position;
            this.path = path;
            this.waveComponent = waveComponent;
            this.creepType = creepType;
            this.grave = grave;
        }

        public void run() {
            grave.deleteFromWorld();
            Creep.registerCreep(world, path, waveComponent, creepType, CreepAbilityType.NORMAL, creepType.getSpeed(),
                    creepType.getSize(), creepType.getHealth(), position.x, position.y);
            waveComponent.removeCreep(e);
        }
    }

    private void spawn(Entity e, int creeps) {
        final Vector3 position = positionMapper.get(e).getPoint();
        final Path path = pathMapper.get(e).getPath();
        final WaveComponent waveComponent = waveMapper.get(e);
        final CreepType creepType = creepTypeMapper.get(e).getCreepType();
        final int health = healthMapper.get(e).getHealth().getMaxHealth()/creeps;

        double circleSegment = Math.PI * 2 / creeps;
        float radius = path.getWidth() / 2f * App.WORLD_SCALE;
        for (int i = 0; i < creeps; i++) {
            float x = position.x + (float) (Math.cos(circleSegment * i) * radius);
            float y = position.y + (float) (Math.sin(circleSegment * i) * radius);
            Creep.registerCreep(world, new Path(path), waveComponent, creepType, CreepAbilityType.NORMAL,
                    creepType.getSpeed(), creepType.getSize() / 4f, health, x, y);
        }
        die(e);
    }


    private void earn(Entity e) {
        Vector3 position = positionMapper.get(e).getPoint();
        if (healthMapper.get(e).getHealth().isEarnTreasure()) {
            earnTreasure(e, position);
        }
        if (healthMapper.get(e).getHealth().isStealLife()) {
            stealLife(e, position);
        }
    }

    private void earnTreasure(Entity e, Vector3 position) {
        Treasure treasure = treasureMapper.get(e).getTreasure();
        int gold = treasure.getGold();
        if (gold != 0) {
            TextInfo.registerMoneyInfo(world, gold, position.x, position.y);
            player.addScore(gold);
        }
        for (Element element : Element.values()) {
            if (treasure.hasElement(element, 1)) {
                TextInfo.registerElementInfo(world, treasure.getElement(element), element, position.x, position.y);
                player.addScore(treasure.getElement(element) * (element.equals(Element.SOUL) ? 500 : 100));
            }
        }
        treasure.transferTo(player.getTreasure());
    }

    private void stealLife(Entity e, Vector3 position) {
        int lifeToSteal = (int) damageMapper.get(e).getDamage();
        TextInfo.registerLifeStealInfo(world, lifeToSteal, position.x, position.y);
        player.increaseHealth(lifeToSteal);
    }

    private void die(Entity e) {
        // FIXME tested so far on resurrect and does not work.
        if (!Options.MUTE) {
            Assets.getSound("pickup").play(0.10f);
        }
        waveMapper.get(e).removeCreep(e);
        e.deleteFromWorld();
    }


}
