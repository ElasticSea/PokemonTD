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
abstract class TutorialTab extends ChildTab {

    private final float textBlockHeight;
    private final float textBlockY;
    private final DisplayText text;
    private final String headerText;
    private MenuButton header;

    TutorialTab(Menu menu, MenuTab parent, Rectangle rectangle) {
        this(menu, parent, rectangle, null);
    }

    TutorialTab(Menu menu, MenuTab parent, Rectangle rectangle, String headerText) {
        super(menu, parent, rectangle, 12);
               this.headerText = headerText;
        if (headerText != null) {
            header = new MenuButton(menu, rects.get(0), headerText) {

                @Override
                public void process(float x, float y) {

                }
            };
            register(header);
        }

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
        if (header != null) {
            header.render(headerText);
        }

        Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
        Gdx.gl.glScissor((int) x, (int) (close.y + close.height), (int) width, (int) (height - close.height * 2));
        text.render(getText());
        Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
    }

    protected abstract String getText();

    @Override
    public void pan(float x, float y, float deltaX, float deltaY) {
        float overlap = text.getTextHeight() - textBlockHeight;
        System.out.println(this.getClass() + " " + text.getTextHeight() + " " + textBlockHeight);
        if (overlap > 0) {
            text.y = MathUtils.clamp(text.y - deltaY, textBlockY, textBlockY + overlap);
        }
        super.pan(x, y, deltaX, deltaY);
    }
}
