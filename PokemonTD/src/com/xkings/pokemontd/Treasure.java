package com.xkings.pokemontd;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Tomas on 10/5/13.
 */
public class Treasure {

    private int gold;
    private final Map<Element, Integer> elementSet = new EnumMap<Element, Integer>(Element.class);

    public Treasure(int gold) {
        this(gold, 0, 0, 0, 0, 0, 0);
    }

    public Treasure(int gold, int water, int fire, int nature, int light, int darkness, int pure) {
        this.gold = gold;
        this.addElement(Element.WATER, water);
        this.addElement(Element.FIRE, fire);
        this.addElement(Element.NATURE, nature);
        this.addElement(Element.LIGHT, light);
        this.addElement(Element.DARKNESS, darkness);
        this.addElement(Element.PURE, pure);
    }

    public void addElement(Element element, int count) {
        elementSet.put(element, elementSet.get(element) + count);
    }

    public boolean hasElement(Element element, int count) {
        return elementSet.get(element) >= count;
    }

    public void removeElement(Element element, int count) {
        addElement(element, -count);
    }

    public void addGold(int count) {
        gold += count;
    }

    public boolean hasGold(int count) {
        return gold >= count;
    }

    public void removeGold(int count) {
        addGold(-count);
    }

    public void transferTo(Treasure treasure) {
        for (Map.Entry<Element, Integer> element : this.elementSet.entrySet()) {
            treasure.addElement(element.getKey(), Math.min(element.getValue(), 0));
        }
        treasure.addGold(Math.min(this.gold, 0));
    }

    public void transferFrom(Treasure treasure) {
        treasure.transferTo(this);
    }
}
