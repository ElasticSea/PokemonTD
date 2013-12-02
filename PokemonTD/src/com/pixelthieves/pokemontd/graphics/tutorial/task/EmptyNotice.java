package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;


/**
 * Created by Tomas on 12/2/13.
 */
public class EmptyNotice extends NoticeTask {

    public EmptyNotice(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        return null;
    }

    @Override
    public boolean checkConditions(App entity) {
        return true;
    }

}
