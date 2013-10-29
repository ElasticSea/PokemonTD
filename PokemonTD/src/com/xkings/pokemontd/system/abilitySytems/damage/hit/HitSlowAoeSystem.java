package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.projectile.data.AoeComponent;
import com.xkings.pokemontd.component.attack.projectile.data.NormalData;
import com.xkings.pokemontd.system.abilitySytems.damage.AoeSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitSlowAoeSystem extends HitSystem<NormalData> {

    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<AoeComponent> aoeMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    private AoeSystem aoe;
    private float damage;


    public HitSlowAoeSystem() {
        super(NormalData.class, AoeComponent.class);
    }

    @Override
    protected void initialize() {
        super.initialize();
        aoe = new AoeSystem() {
            @Override
            protected void processAoe(Entity entity) {
                healthMapper.get(entity).getHealth().decease((int) damage);
            }
        };
        world.setSystem(aoe, true);
    }

    public void onHit(NormalData effectData, Entity entity, Entity target) {
        damage = damageMapper.get(entity).getDamage();
        aoe.start(target, positionMapper.get(target).getPoint(), damage, aoeMapper.get(entity).getRange());
    }

}
