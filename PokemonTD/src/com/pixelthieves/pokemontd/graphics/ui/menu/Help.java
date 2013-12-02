package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
public class Help extends ChildTab {

    private final Button basicButton;
    private final Button advancedButton;
    private final Button towersButton;
    private final TutorialTab basic;
    private final TutorialTab advanced;
    private final AbilityTab abilityTab;

    Help(final Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);

        basic = new Basic(menu,  this, menu.getRectangle( Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        advanced = new Advanced(menu,  this, menu.getRectangle( Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        abilityTab = new AbilityTab(menu, this, rectangle, count);
        basicButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(basic);
            }
        };
        advancedButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(advanced);
            }
        };

        towersButton = new MenuButton(menu, rects.get(2)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(abilityTab);
            }
        };

        register(basicButton);
        register(advancedButton);
        register(towersButton);
        cards.add(basic);
        cards.add(advanced);
        cards.add(abilityTab);
    }

    @Override
    public void render() {
        super.render();
        basicButton.render("Basics");
        advancedButton.render("Advanced");
        towersButton.render("Towers");
    }
}
