package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;

/**
 * Created by Tomas on 11/19/13.
 */
class Tutorial extends ChildTab {

    private final float textBlockHeight;
    private final float textBlockY;
    private String tutorialText =
            "In Pokemon tower defense the goal is to prevents the creeps from reaching the end of the path by building towers and surviving all creep waves. Every time a creep gets through the whole way, you loose life.\n\n" +
                    "Information about current live count, your gold, what the next wave will be and interestManager can be seen in the upper right box.\n\n" +
                    "To build a tower pick one of the towers in the lower right box, place it where you want it on the map, and conform it by pressing BUY button.\n\n" +
                    "You can also upgrade the towers by clicking at already built towers and then choosing an upgrade in the lower right box.\n\n" +
                    "To build some towers you need more than just gold - elements {WATER, FIRE, NATURE, LIGHT, DARKNESS, PURE, SOUL}. To get the elements you need, build a shop tower, which can be also found in lower right corner along with other basic tower types. When you choose particular element a creep carrying the element will spawn. To claim element you need to kill this creep.\n\n" +
                    "It´s generally good idea to save your money, because each 15 seconds you got interestManager.";
    private final DisplayText text;

    Tutorial(Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, rectangle, 8);

        textBlockHeight = rectangle.height - buttonHeight - buttonHeight * 2;
        textBlockY = rectangle.y + buttonHeight + buttonHeight;
        text = new DisplayText(ui,
                new Rectangle(rectangle.x + segment, textBlockY, rectangle.width - segment * 2, textBlockHeight),
                ui.getFont(), BitmapFont.HAlignment.LEFT, true, new String());

        register(text);
        this.setRenderLines(false);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int)x, (int)(close.y+close.height), (int)width, (int)(height-close.height-segment));
        text.render(tutorialText);
        Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
    }

    @Override
    public void pan(float x, float y, float deltaX, float deltaY) {
        float overlap = text.getTextHeight() - textBlockHeight;
        if (overlap > 0) {
            text.y = MathUtils.clamp(text.y - deltaY, textBlockY, textBlockY + overlap);
        }
        super.pan(x, y, deltaX, deltaY);
    }
}
