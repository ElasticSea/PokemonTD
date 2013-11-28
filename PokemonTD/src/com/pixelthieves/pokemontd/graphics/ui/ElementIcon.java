package com.pixelthieves.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Health;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.Treasure;
import com.pixelthieves.pokemontd.component.HealthComponent;
import com.pixelthieves.pokemontd.component.WaveComponent;
import com.pixelthieves.pokemontd.graphics.ui.menu.Options;
import com.pixelthieves.pokemontd.manager.WaveManager;

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
        super(ui, rectangle);
        this.spriteBatch = spriteBatch;
        picture = new DisplayPicture(ui, rectangle);
        waveManager = ui.getWaveManager();
    }

    @Override
    public void render() {
        if (element != null) {
            int elementCount = currentElements.getElement(element) + 1;
            if (Treasure.LIMIT.getElement(element) < elementCount) {
                picture.setColor(Color.DARK_GRAY);
                picture.render(Assets.getTexture("gems/" + element.toString().toLowerCase()), "max", true);
            } else {
                picture.setColor(player.getFreeElements() != 0 && elementAllowed() ? Color.WHITE : Color.DARK_GRAY);
                picture.render(Assets.getTexture("gems/" + element.toString().toLowerCase()), "lvl " + elementCount,
                        true);

            }
        }
    }

    private boolean elementAllowed() {
        return (!element.equals(Element.SOUL) || waveManager.isNextWaveLast());
    }

    @Override
    public void process(float x, float y) {
        if (player.getFreeElements() != 0) {
            int elements = currentElements.getElement(element);
            if (Treasure.LIMIT.getElement(element) > elements && elementAllowed()) {
                WaveComponent wave = waveManager.fireNextWave(WaveManager.getWave(element, elements));
                for (Entity creep : wave.getWave()) {
                    Health health = creep.getComponent(HealthComponent.class).getHealth();
                    int newHealth = health.getMaxHealth() * waveManager.getNextWave().getHealth();
                    health.setHealth(newHealth, newHealth);
                }
                currentElements.addElement(element, 1);
                player.subtractFreeElement();
                if (!Options.MUTE) {
                    Assets.getSound("roar").play();
                }
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