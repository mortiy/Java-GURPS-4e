package net.mortiy.gurps.game.map;

import net.mortiy.gurps.rules.map.GameMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Observable;

/**
 * Represents character token on the map
 */
public class Token extends Observable implements GameMap.MapToken {

    private float rotationAngle = 0;
    private float viewAngle = 60;
    private float speed = 0.2f;

    private Image image;

    enum TokenEventType {
        ViewAngleChange,
        RotationChange
    }

    class TokenEvent {
        TokenEventType eventType;
        Float value;

        TokenEvent(TokenEventType eventType, Float value) {
            this.eventType = eventType;
            this.value = value;
        }
    }

    public Token() {
        try {
            this.image = new Image("res/token.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
        image.setRotation(rotationAngle - 60);
        setChanged();
        notifyObservers(new TokenEvent(TokenEventType.RotationChange, rotationAngle));
    }

    public float getViewAngle() {
        return viewAngle;
    }

    public void setViewAngle(float viewAngle) {
        this.viewAngle = viewAngle;
        setChanged();
        notifyObservers(new TokenEvent(TokenEventType.ViewAngleChange, viewAngle));
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


}
