package com.xkings.pokemontd.entity;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/5/13.
 */
public class Projectile extends ConcreteEntity {

    private Projectile(World world, TextureAtlas.AtlasRegion atlasRegion, float size, float speed, float x, float y,
                       Vector3 targetPosition, Entity target) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(new Path(new Vector3(x, y, 0), targetPosition)));
        addComponent(new SpriteComponent(atlasRegion));
        addComponent(new SizeComponent(size, size, 0));
        addComponent(new SpeedComponent(speed));
        addComponent(new DamageComponent(1));
        addComponent(new TimeComponent());
        addComponent(new TargetComponent(target));
    }


    public static void registerProjectile(World world, TextureAtlas.AtlasRegion atlasRegion, float size, float speed,
                                          float x, float y, Vector3 targetPosition, Entity target) {
        Projectile projectile = new Projectile(world, atlasRegion, size, speed, x, y, targetPosition, target);
        projectile.register();
    }
}
