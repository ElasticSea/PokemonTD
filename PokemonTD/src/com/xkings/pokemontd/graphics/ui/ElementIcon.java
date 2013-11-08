package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Element;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.entity.creep.CreepType;
import com.xkings.pokemontd.manager.WaveManager;

/**
 * Created by Tomas on 10/8/13.
 */
class ElementIcon extends InteractiveBlock {

    private final DisplayPicture picture;
    private final WaveManager waveManager;
    protected Element element;
    private Player player;
    private final SpriteBatch spriteBatch;
    private Treasure currentElements;

    ElementIcon(Gui ui, Rectangle rectangle, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        picture = new DisplayPicture(ui, rectangle);
        waveManager = ui.getWaveManager();
    }

    @Override
    public void render() {
        if (element != null) {
            int elementCount = currentElements.getElement(element) + 1;
            String text = Treasure.LIMIT.getElement(element) < elementCount ? "max" : "lvl " + elementCount;

            picture.render(Assets.getTexture("gems/" + element.toString().toLowerCase()), text, true);
            spriteBatch.begin();
            if (player.getFreeElements() == 0) {
                spriteBatch.draw(Assets.getTexture("blocked"), x, y, width, height);
            }
            spriteBatch.end();
        }
    }

    @Override
    public void process(float x, float y) {
        if (player.getFreeElements() != 0) {
            int elements = currentElements.getElement(element) + 1;
            if (Treasure.LIMIT.getElement(element) >= elements) {
                waveManager.fireNextWave(CreepType.getWave(element, elements));
                currentElements.addElement(element, 1);
                player.subtractFreeElement();
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setElement(Element element) {
        this.element = element;
        System.out.println(element);
    }

    public Element getElement() {
        return element;
    }

    public void setCurrentElements(Treasure currentElements) {
        this.currentElements = currentElements;
    }

    @Override
    public void refresh() {
        picture.set(this);
        picture.refresh();
    }
}