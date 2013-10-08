package com.xkings.pokemontd;

import java.util.EnumMap;
import java.util.Map;

import static com.xkings.pokemontd.Element.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class Treasure {

    private int gold;
    private final Map<Element, Integer> elementSet = new EnumMap<Element, Integer>(Element.class);

    public Treasure(Treasure treasure) {
        this(treasure.getGold(), treasure.getElement(WATER), treasure.getElement(FIRE), treasure.getElement(NATURE),
                treasure.getElement(LIGHT), treasure.getElement(DARKNESS), treasure.getElement(PURE));
    }

    public Treasure(int gold) {
        this(gold, 0, 0, 0, 0, 0, 0);
    }

    public Treasure(int gold, int water, int fire, int nature, int light, int darkness, int pure) {
        this.gold = gold;
        elementSet.put(WATER, water);
        elementSet.put(FIRE, fire);
        elementSet.put(NATURE, nature);
        elementSet.put(LIGHT, light);
        elementSet.put(DARKNESS, darkness);
        elementSet.put(PURE, pure);
    }

    public void addElement(Element element, int count) {
        elementSet.put(element, getElement(element) + count);
    }

    public boolean hasElement(Element element, int count) {
        return getElement(element) >= count;
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

    public int getGold() {
        return gold;
    }

    public int getElement(Element element) {
        return elementSet.get(element);
    }

    public void transferTo(Treasure treasure) {
        for (Map.Entry<Element, Integer> element : this.elementSet.entrySet()) {
            treasure.addElement(element.getKey(), Math.max(element.getValue(), 0));
        }
        treasure.addGold(Math.max(this.gold, 0));
    }

    public void transferFrom(Treasure treasure) {
        treasure.transferTo(this);
    }

    /**
     * Checks whether a treasure is greater or equal then another treasure.
     *
     * @param treasure to be checked
     * @return {@code True} if this treasure is greater or equal to another treasure, {@code false} otherwise
     */
    public boolean includes(Treasure treasure) {
        if (hasGold(treasure.gold)) {
            for (Map.Entry<Element, Integer> entry : elementSet.entrySet()) {
                Element element = entry.getKey();
                if (!hasElement(element, treasure.elementSet.get(element))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public void subtract(Treasure treasure) {
        for (Map.Entry<Element, Integer> entry : elementSet.entrySet()) {
            this.removeElement(entry.getKey(), entry.getValue());
        }
        this.removeGold(treasure.getGold());
    }
}
