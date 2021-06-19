package sol;

import src.IAttributeDatum;

public class Vegetable implements IAttributeDatum {
    public String color;
    public boolean lowCarb;
    public boolean highFiber;
    public boolean likeToEat;

    /**
     * A constructor for vegetables
     * @param color - The color of the vegetable
     * @param lowCarb - A boolean representing whether or not the vegetable is lowCarb
     * @param highFiber - A boolean representing whether or not the vegetable is high fiber
     * @param likeToEat - A boolean representing whether or not the vegetable is liked to eat
     */

    public Vegetable(String color, boolean lowCarb, boolean highFiber, boolean likeToEat){
        this.color = color;
        this.lowCarb = lowCarb;
        this.highFiber = highFiber;
        this.likeToEat = likeToEat;
    }

    /**
     * Looks up the value of a named attribute.
     * @param attributeName the name of the attribute
     * @return the value of the attribute with the given name
     */
    @Override
    public Object getValueOf(String attributeName) {
        if (attributeName.equals("color")){
            return this.color;
        }
        if (attributeName.equals("low carb")){
            return this.lowCarb;
        }
        if (attributeName.equals("high fiber")){
            return this.highFiber;
        }
        else if (attributeName.equals("like to eat")){
            return this.likeToEat;
        }
        throw new IllegalArgumentException("invalid value type");
    }
}
