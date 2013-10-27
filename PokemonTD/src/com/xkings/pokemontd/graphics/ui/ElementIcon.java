package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Element;
import com.xkings.pokemontd.Player;

/**
 * Created by Tomas on 10/8/13.
 */
class ElementIcon extends InteractiveBlock {

    protected Element element;
    private Player player;
    private final SpriteBatch spriteBatch;

    ElementIcon(Rectangle rectangle, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render() {
        if (element != null) {
            spriteBatch.begin();
            spriteBatch.draw(Assets.getTexture("gems/" + element.toString().toLowerCase()), x, y, width, height);
            if (player.getFreeElements() == 0) {
                spriteBatch.draw(Assets.getTexture("blocked"), x, y, width, height);
            }
            spriteBatch.end();
        }
    }

    @Override
    public void process(float x, float y) {
        if (player.getFreeElements() != 0) {
            player.getTreasure().addElement(element, 1);
            player.subtractFreeElement();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}