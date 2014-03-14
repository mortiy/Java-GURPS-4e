package net.mortiy.gurps.rules.individual;

import java.util.LinkedList;
import java.util.List;

public class Body {
    public enum Part {
        Torso(0),
        Vitals(-3),
        Skull(-7),
        Eye(-9),
        Face(-5),
        Neck(-5),
        Groin(-3),
        Arms(-2),
        LeftArm(-2),
        RightArm(-2),
        LeftHand(-4),
        RightHand(-4),
        Legs(-2),
        LeftLeg(-2),
        RightLeg(-2),
        Feet(-4);

        private int modifier;

        private Part(int modifier) {
            this.modifier = modifier;
        }

        public int getModifier() {
            return modifier;
        }
    }

    float height;
    float weight;
    int sizeModifier;
    List<Part> parts = new LinkedList<Part>();

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSizeModifier() {
        return sizeModifier;
    }

    public void setSizeModifier(int sizeModifier) {
        this.sizeModifier = sizeModifier;
    }
}
