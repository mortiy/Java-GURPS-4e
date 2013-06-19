package net.mortiy.gurps.rules.character;

import java.util.LinkedList;
import java.util.List;

public class Body {
    public enum Part {
        Head,
        Torso,
        LeftHand,
        RightHand,
        LeftLeg,
        RightLeg

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
