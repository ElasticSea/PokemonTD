package com.pixelthieves.pokemontd.system.abilitySytems.damage;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.pixelthieves.pokemontd.component.PathComponent;
import com.pixelthieves.pokemontd.component.attack.effects.ChangeDirectionEffect;
import com.pixelthieves.pokemontd.map.Path;

/**
 * Created by Tomas on 10/4/13.
 */
public class ChangeDirectionSystem extends EffectSystem<ChangeDirectionEffect> {

    @Mapper
    ComponentMapper<PathComponent> pathMapper;

    public ChangeDirectionSystem() {
        super(ChangeDirectionEffect.class);
    }

    @Override
    protected void finished(ChangeDirectionEffect effect, Entity e) {
    }

    @Override
    protected void started(ChangeDirectionEffect effect, Entity e) {
        Path path = pathMapper.get(e).getPath();
        if (!path.isChangedDirection()) {
            path.changeDirection();
        }
    }

    @Override
    protected void processEffect(ChangeDirectionEffect effect, Entity e) {
        Path path = pathMapper.get(e).getPath();
        path.changeDirection();
    }

    @Override
    protected void resetEffect(ChangeDirectionEffect effect, Entity e) {
        processEffect(effect, e);
        started(effect, e);
    }

    @Override
    protected void reattachEffect(ChangeDirectionEffect effect, Entity e) {

    }
}
