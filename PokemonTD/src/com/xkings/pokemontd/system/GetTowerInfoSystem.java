package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.entity.CurrentTowerInfo;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.manager.SelectedTower;

public class GetTowerInfoSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

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
        currentTowerInfo.setAtlasRegion(spriteMapper.get(selectedTower.getTower()).getSprite());
    }

    private void fillIntoFromType(CurrentTowerInfo currentTowerInfo, TowerType tower) {
        currentTowerInfo.setAtlasRegion(tower.getTexture());

    }

    @Override
    protected void process(Entity e) {

    }
}
