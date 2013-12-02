package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.artemis.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.entity.tower.TowerType;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class CancelThat extends NoticeTask {

    public CancelThat(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, new Rectangle(ui.getCenter().x,ui.getCenter().y,0,0), Notice.Orientation.TOP_LEFT, "Click here to deselect",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        Entity clicked = entity.getTowerManager().getClicked();
        if(clicked != null){
            TowerTypeComponent component = clicked.getComponent(TowerTypeComponent.class);
            if(component!= null){
                return component.getTowerType().getName().equals(TowerName.Shop);
            }
        }
        return false;
    }

}
