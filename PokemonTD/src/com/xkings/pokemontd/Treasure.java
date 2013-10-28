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
    private static final Treasure limit = new Treasure(Integer.MAX_VALUE, 3, 3, 3, 3, 3, 3, 1);

    public Treasure(Treasure treasure) {
        this(treasure.getGold(), treasure.getElement(WATER), treasure.getElement(FIRE), treasure.getElement(NATURE),
                treasure.getElement(LIGHT), treasure.getElement(DARKNESS), treasure.getElement(PURE),
                treasure.getElement(SOUL));
    }

    public Treasure(int gold) {
        this(gold, 0, 0, 0, 0, 0, 0, 0);
    }

    public Treasure(int gold, int water, int fire, int nature, int light, int darkness, int neutral, int pure) {
        this.gold = gold;
        addElement(WATER, water);
        addElement(FIRE, fire);
        addElement(NATURE, nature);
        addElement(LIGHT, light);
        addElement(DARKNESS, darkness);
        addElement(PURE, neutral);
        addElement(SOUL, pure);
    }

    public void addElement(Element element, int count) {
        int add = getElement(element) + count;
        if (add > 0) {
            elementSet.put(element, add);
        }
    }

    public boolean hasElement(Element element, int count) {
        return getElement(element) >= count;
    }

    public void subtractElement(Element element, int count) {
        addElement(element, -count);
    }

    public void addGold(int count) {
        gold += count;
    }

    public boolean hasGold(int count) {
        return gold >= count;
    }

    public void subtractGold(int count) {
        addGold(-count);
    }

    public int getGold() {
        return gold;
    }

    public int getElement(Element element) {
        Integer count = elementSet.get(element);
        return count != null ? count : 0;
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
            for (Element element : Element.values()) {
                if (!hasElement(element, treasure.getElement(element))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public void subtract(Treasure treasure) {
        for (Map.Entry<Element, Integer> entry : treasure.elementSet.entrySet()) {
            this.subtractElement(entry.getKey(), treasure.getElement(entry.getKey()));
        }
        this.subtractGold(treasure.getGold());
    }

    public void add(Treasure treasure) {
        for (Map.Entry<Element, Integer> entry : treasure.elementSet.entrySet()) {
            this.addElement(entry.getKey(), entry.getValue());
        }
        this.addGold(treasure.getGold());
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "elementSet=" + elementSet +
                ", gold=" + gold +
                '}';
    }


    public boolean canAdd(Element element, int count) {
        Treasure treasure = new Treasure(this);

        int add = treasure.getElement(element) + count;
        if (add > 0) {
            treasure.elementSet.put(element, add);
        }

        return limit.includes(treasure);
    }

    public boolean reachedMaximum(Element element) {
        return this.getElement(element) == limit.getElement(element);
    }

    public static Treasure fromNone() {
        return new Treasure(0);
    }

    public static Treasure fromGold(int count) {
        return new Treasure(count, 0, 0, 0, 0, 0, 0, 0);
    }

    public static Treasure fromWater(int count) {
        return new Treasure(0, count, 0, 0, 0, 0, 0, 0);
    }

    public static Treasure fromFire(int count) {
        return new Treasure(0, 0, count, 0, 0, 0, 0, 0);
    }

    public static Treasure fromNature(int count) {
        return new Treasure(0, 0, 0, count, 0, 0, 0, 0);
    }

    public static Treasure fromLight(int count) {
        return new Treasure(0, 0, 0, 0, count, 0, 0, 0);
    }

    public static Treasure fromDarkness(int count) {
        return new Treasure(0, 0, 0, 0, 0, count, 0, 0);
    }

    public static Treasure fromPure(int count) {
        return new Treasure(0, 0, 0, 0, 0, 0, count, 0);
    }

    public static Treasure fromSoul(int count) {
        return new Treasure(0, 0, 0, 0, 0, 0, 0, count);
    }
}
