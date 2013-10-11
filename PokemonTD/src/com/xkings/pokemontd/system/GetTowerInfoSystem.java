package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.RangeComponent;
import com.xkings.pokemontd.component.AttackComponent;
import com.xkings.pokemontd.component.ProjectileComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.entity.CurrentTowerInfo;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.manager.SelectedTower;

public class GetTowerInfoSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<TowerTypeComponent> towerTypwMapper;
    @Mapper
    ComponentMapper<ProjectileComponent> projectileMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    public GetTowerInfoSystem() {
        super(Aspect.getAspectForAll(SpriteComponent.class));
    }

    public void getInfo(CurrentTowerInfo currentTowerInfo, SelectedTower selectedTower, TowerType tower) {
        if (selectedTower.getTower() != null) {
            fillInfoFromEntity(currentTowerInfo, selectedTower);
        } else if (tower != null) {
            fillIntoFromType(currentTowerInfo, tower);
        } else {
            currentTowerInfo.clear();
        }
    }

    private void fillInfoFromEntity(CurrentTowerInfo currentTowerInfo, SelectedTower selectedTower) {
        fillIntoFromType(currentTowerInfo, towerTypwMapper.get(selectedTower.getTower()).getTowerType());
        currentTowerInfo.setAttack((int) projectileMapper.get(selectedTower.getTower()).getProjectileType().getDamage());
        currentTowerInfo.setRange((int) rangeMapper.get(selectedTower.getTower()).getRange());
    }

    private void fillIntoFromType(CurrentTowerInfo currentTowerInfo, TowerType tower) {
        currentTowerInfo.setName(tower.getName());
        currentTowerInfo.setAtlasRegion(tower.getTexture());
        currentTowerInfo.setAttack((int) tower.getProjectile().getDamage());
        currentTowerInfo.setRange((int) tower.getRange());
    }

    @Override
    protected void process(Entity e) {

    }
}
