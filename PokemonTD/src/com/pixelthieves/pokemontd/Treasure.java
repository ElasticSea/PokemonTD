package com.pixelthieves.pokemontd;

import java.util.EnumMap;
import java.util.Map;

import static com.pixelthieves.pokemontd.Element.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class Treasure {

    public static final Element[] ELEMENTS = Element.values();
    private int gold;
    private final Map<Element, Integer> elementSet = new EnumMap<Element, Integer>(Element.class);
    public static final Treasure LIMIT = new Treasure(Integer.MAX_VALUE, 3, 3, 3, 3, 3, 1);

    public Treasure(Treasure treasure) {
        this(treasure.getGold(), treasure.getElement(WATER), treasure.getElement(FIRE), treasure.getElement(NATURE),
                treasure.getElement(LIGHT), treasure.getElement(DARKNESS), treasure.getElement(SOUL));
    }

    public Treasure() {
        this(0);
    }

    public Treasure(int gold) {
        this(gold, 0, 0, 0, 0, 0, 0);
    }

    public Treasure(int gold, int water, int fire, int nature, int light, int darkness, int soul) {
        set(gold, water, fire, nature, light, darkness, soul);
    }

    public void set(int gold) {
        elementSet.clear();
        this.gold = gold;
    }

    public void set(int gold, int water, int fire, int nature, int light, int darkness, int soul) {
        set(gold);
        addElement(WATER, water);
        addElement(FIRE, fire);
        addElement(NATURE, nature);
        addElement(LIGHT, light);
        addElement(DARKNESS, darkness);
        addElement(SOUL, soul);
    }

    public void addElement(Element element, int count) {
        int add = getElement(element) + count;
        if (add > 0) {
            setElement(element, add);
        }
    }

    private void setElement(Element element, int value) {
        if (element != null) {
            elementSet.put(element, value);
        }
    }

    public boolean hasElement(Element element, int count) {
        return getElement(element) >= count;
    }

    public void subtractElement(Element element, int count) {
        addElement(element, -count);
    }

    public Treasure addGold(int count) {
        setGold(getGold() + count);
        return this;
    }

    public void setGold(int count) {
        gold = count;
    }

    public Treasure addWater(int count) {
        addElement(WATER, count);
        return this;
    }

    public Treasure addFire(int count) {
        addElement(FIRE, count);
        return this;
    }

    public Treasure addNature(int count) {
        addElement(NATURE, count);
        return this;
    }

    public Treasure addLight(int count) {
        addElement(LIGHT, count);
        return this;
    }

    public Treasure addDarkness(int count) {
        addElement(DARKNESS, count);
        return this;
    }

    public Treasure addSoul(int count) {
        addElement(SOUL, count);
        return this;
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
            for (Element element : ELEMENTS) {
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


    public void purchase(Treasure cost) {
        this.subtractGold(cost.getGold());
        this.subtractElement(SOUL, cost.getElement(SOUL));
    }

    public void add(Treasure treasure) {
        for (Map.Entry<Element, Integer> entry : treasure.elementSet.entrySet()) {
            this.addElement(entry.getKey(), entry.getValue());
        }
        this.addGold(treasure.getGold());
    }


    public void multiplyGold(float value) {
        this.setGold((int) (this.getGold() * value));
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "elementSet=" + elementSet +
                ", gold=" + gold +
                '}';
    }

    public static Treasure fromNone() {
        return new Treasure(0);
    }

    public static Treasure fromGold(int count) {
        return new Treasure(count, 0, 0, 0, 0, 0, 0);
    }

    public static Treasure fromWater(int count) {
        return new Treasure(0, count, 0, 0, 0, 0, 0);
    }

    public static Treasure fromFire(int count) {
        return new Treasure(0, 0, count, 0, 0, 0, 0);
    }

    public static Treasure fromNature(int count) {
        return new Treasure(0, 0, 0, count, 0, 0, 0);
    }

    public static Treasure fromLight(int count) {
        return new Treasure(0, 0, 0, 0, count, 0, 0);
    }

    public static Treasure fromDarkness(int count) {
        return new Treasure(0, 0, 0, 0, 0, count, 0);
    }

    public static Treasure fromSoul(int count) {
        return new Treasure(0, 0, 0, 0, 0, 0, count);
    }
}
