package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 11/6/13.
 */
public abstract class InteractiveBlockParent extends InteractiveElement {
    private final List<InteractiveBlock> interactiveBlocks = new ArrayList<InteractiveBlock>();
    private boolean enabled = true;

    public InteractiveBlockParent(Gui gui, Rectangle rectangle) {
        super(gui, rectangle);
    }

    @Override
    public boolean hit(float x, float y) {
        y = Gdx.graphics.getHeight() - y;
        if (isEnabled() && contains(x, y)) {
            for (InteractiveBlock interactiveBlock : interactiveBlocks) {
                if (interactiveBlock.isEnabled() && interactiveBlock.contains(x, y)) {
                    interactiveBlock.process(x - width, y - height);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void register(InteractiveBlock interactiveBlock) {
        interactiveBlocks.add(interactiveBlock);
    }

    public void unregister(InteractiveBlock interactiveBlock) {
        interactiveBlocks.remove(interactiveBlock);
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void process(float x, float y);

}
