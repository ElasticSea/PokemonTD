package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class FireFirstWave extends NoticeTask {

    public FireFirstWave(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getWaveStartButton(), Notice.Orientation.BOTTOM_LEFT, "Start the game",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return tutorial.getUpgrade() != null && tutorial.getShop() != null;
    }

}
