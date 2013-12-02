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
    private final MenuButton interactiveTutorialButton;

    Help(final Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);

        basic = new Basic(menu,  this, menu.getRectangle( Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        advanced = new Advanced(menu,  this, menu.getRectangle( Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        abilityTab = new AbilityTab(menu, this, rectangle, count);
        interactiveTutorialButton = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                menu.getApp().activateTutorial(true);
                menu.switchCard((MenuTab) null);
            }
        };
        basicButton = new MenuButton(menu, rects.get(1)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(basic);
            }
        };
        advancedButton = new MenuButton(menu, rects.get(2)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(advanced);
            }
        };

        towersButton = new MenuButton(menu, rects.get(3)) {
            @Override
            public void process(float x, float y) {
                menu.switchCard(abilityTab);
            }
        };

        register(interactiveTutorialButton);
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
        basicButton.render("Quick Tips");
        advancedButton.render("Advanced Tutorial");
        towersButton.render("Towers Combinations");
        interactiveTutorialButton.render("Interactive Tutorial");
    }
}
