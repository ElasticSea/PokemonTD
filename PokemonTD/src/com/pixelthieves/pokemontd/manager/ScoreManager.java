package com.pixelthieves.pokemontd.manager;

import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Mode;

/**
 * Created by Tomas on 12/16/13.
 */
public class ScoreManager {
    private final App app;

    public ScoreManager(App app) {
        this.app = app;
    }

    public void addElement(Element element, int count) {
        if (app.getMode().equals(Mode.Classic)) {
            app.getPlayer().addScore(count * (element.equals(Element.SOUL) ? 500 : 100));
        }
    }

    public void addGold(int gold) {
        if (app.getMode().equals(Mode.Classic)) {
            app.getPlayer().addScore(gold);
        }
    }

    public void addHealth(int health) {
        if (app.getMode().equals(Mode.Endless)) {
            app.getPlayer().addScore(health);
        }
    }
}
