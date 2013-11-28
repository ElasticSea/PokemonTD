package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.entity.tower.TowerType;
import com.pixelthieves.pokemontd.manager.TowerManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Seda on 10/27/13.
 */
public class TowerIcons extends PickTable<TowerIcon> {

    private final TowerManager towerManager;
    private List<TowerType> lastHierarchy;

    TowerIcons(Gui ui, Rectangle rectangle, TowerManager towerManager) {
        super(ui, rectangle);
        this.towerManager = towerManager;
        for (TowerIcon towerIcon : pickIcons) {
            towerIcon.setTowerManager(towerManager);
        }
    }

    @Override
    protected TowerIcon createPickIcon() {
        return new TowerIcon(ui, new Rectangle()) {
            @Override
            public void process(float x, float y) {
                towerManager.setPickedTower(towerType);
            }
        };
    }

    private void update(List<TowerType> hierarchy) {
        Iterator<TowerType> hierarchyIterator = hierarchy.iterator();
        for (TowerIcon towerIcon : pickIcons) {
            if (hierarchyIterator.hasNext()) {
                towerIcon.towerType = hierarchyIterator.next();
            } else {
                towerIcon.towerType = null;
            }
        }
    }

    public void update(TowerName towerName) {
        if (lastHierarchy != TowerType.getHierarchy(towerName)) {
            lastHierarchy = TowerType.getHierarchy(towerName);
            update(lastHierarchy);
        }
    }
}
