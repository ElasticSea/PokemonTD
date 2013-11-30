package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.*;

/**
 * Created by Tomas on 11/19/13.
 */
class TutorialTab extends ScrollableChildTab {

    private final Tutorial tutorial;

    TutorialTab(Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle, 8);
        tutorial = new Tutorial(menu, this.width);
        register(tutorial);
        this.setRenderLines(false);
        this.setContent(tutorial);
    }

    private static class Tutorial extends InteractiveBlock {
        private String tutorialText =
                "In Pokemon tower defense the goal is to prevents the creeps from reaching the end of the path by building towers and surviving all creep waves. Every time a creep gets through the whole way, you loose life.\n\n" +
                        "Information about current live count, your gold, what the next wave will be and interestManager can be seen in the upper right box.\n\n" +
                        "To build a tower pick one of the towers in the lower right box, place it where you want it on the map, and conform it by pressing BUY button.\n\n" +
                        "You can also upgrade the towers by clicking at already built towers and then choosing an upgrade in the lower right box.\n\n" +
                        "To build some towers you need more than just gold - elements {WATER, FIRE, NATURE, LIGHT, DARKNESS, PURE, SOUL}. To get the elements you need, build a shop tower, which can be also found in lower right corner along with other basic tower types. When you choose particular element a creep carrying the element will spawn. To claim element you need to kill this creep.\n\n" +
                        "It´s generally good idea to save your money, because each 15 seconds you got interestManager.";

        private final DisplayText text;

        protected Tutorial(Gui gui, float width) {
            super(gui, new Rectangle());
            text = new DisplayText(gui,
                    new Rectangle(0,0,width,0),
                    gui.getFont(), BitmapFont.HAlignment.LEFT, true, new String());

            text.set(0,0,width,text.getHeight());
            this.set(text);
            gui.register(text);
        }

        @Override
        public void refresh() {

        }

        @Override
        public void process(float x, float y) {

        }

        @Override
        public void render() {
            super.render();
            text.render(tutorialText);
        }
    }
}
