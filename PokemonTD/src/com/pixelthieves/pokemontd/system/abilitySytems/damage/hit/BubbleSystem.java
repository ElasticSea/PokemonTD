package com.pixelthieves.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.component.TargetComponent;
import com.pixelthieves.core.component.Time;
import com.pixelthieves.core.component.TimeComponent;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.PathComponent;
import com.pixelthieves.pokemontd.component.WaveComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.projectile.data.BubbleData;
import com.pixelthieves.pokemontd.system.resolve.IntersectEnemySystem;

/**
 * Created by Seda on 10/4/13.
 */
public class BubbleSystem extends EntityProcessingSystem {

    private final IntersectEnemySystem intersectSystem;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;
    @Mapper
    ComponentMapper<BubbleData> bubbleMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    private BubbleData bubble;
    private float damage;


    public BubbleSystem(World world) {
        super(Aspect.getAspectForAll(TargetComponent.class, BubbleData.class));
        intersectSystem = new IntersectEnemySystem(WaveComponent.class) {

            @Override
            protected void intersect(Entity e) {
                healthMapper.get(e).getHealth().decease(damage / bubble.getInterval());
            }
        };
        world.setSystem(intersectSystem, true);
    }


    @Override
    protected void process(Entity e) {
        if (pathMapper.get(e).getPath().isFinished()) {
            e.deleteFromWorld();
        }
        bubble = bubbleMapper.get(e);
        damage = damageMapper.get(e).getDamage();

        Time time = timeMapper.get(e).getTime(this.getClass());
        time.increase(world.getDelta());
        sizeMapper.get(e).getPoint().scl(1 + bubble.getGrow() * world.getDelta());

        if (time.getAvailableTime() >= bubble.getInterval()) {
            time.decrease(bubble.getInterval());
            intersectSystem.start(e);
        }
    }
}
