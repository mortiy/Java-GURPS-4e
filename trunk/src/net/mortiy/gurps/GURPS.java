package net.mortiy.gurps;

import net.mortiy.gurps.codemodel.Generator;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GURPS {
    public static void main(String args[]) {
        System.out.println("Hi :)");
        Generator generator = new Generator();
        generator.generateWeapons();

        try {

            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();


        } catch (LWJGLException e) {

            e.printStackTrace();

        }
    }
}
