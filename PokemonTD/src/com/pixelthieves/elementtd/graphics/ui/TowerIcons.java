package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.entity.tower.TowerName;
import com.pixelthieves.elementtd.entity.tower.TowerType;
import com.pixelthieves.elementtd.manager.TowerManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Tomas on 10/27/13.
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
                System.out.println("CLICK");
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
        if (lastHierarchy != towerManager.getHierarchy(towerName)) {
            lastHierarchy = towerManager.getHierarchy(towerName);
            update(lastHierarchy);
        }
    }

    public TowerIcon getIcon(int count){
        return pickIcons.get(count);
    }
}
