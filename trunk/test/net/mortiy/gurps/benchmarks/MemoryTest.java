package net.mortiy.gurps.benchmarks;

import junit.framework.TestCase;
import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.traits.CostType;
import net.mortiy.gurps.rules.traits.Trait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 13.12.12
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public class MemoryTest extends TestCase {
    final double bytesToMegabytes = 9.5367431640625e-7;
    Runtime runtime = Runtime.getRuntime();

    public void testMemory() throws Exception {
        List<Character> characters = new ArrayList<net.mortiy.gurps.rules.Character>();
        List<Skill> skills = new ArrayList<Skill>();

        double usedMemory = getUsedMemory();
        Log.i("Memory Test", String.format("Used memory before characters creation: %.4f Mb", usedMemory));
        for(int i = 0; i < 500; i++){
            characters.add(new Character(400));
        }
        usedMemory = getUsedMemory() - usedMemory;
        Log.i("Memory Test", String.format("500 characters take: %.4f Mb", usedMemory));
        assertTrue("500 character objects should take less then 5 megabytes in memory for now", usedMemory < 5);
        characters.clear();
        runtime.gc();
        usedMemory = getUsedMemory();
        Log.i("Memory Test", String.format("Characters cleared, GC performed: %.4f Mb", usedMemory));

        for(int i = 0; i < 500; i++){
            Character character = new Character(200);
            for(int j = 0; j < 10; j++){
                character.addSkill(new Skill(character, "Test Skill " + j, Attribute.Health, Skill.Difficulty.Hard));
                character.addTrait(new Trait(character, "Test Skill" + j, CostType.Fixed, 5));
            }
            characters.add(character);
        }
        usedMemory = getUsedMemory() - usedMemory;
        Log.i("Memory Test", String.format("500 characters with 10 skills and 10 traits each take: %.4f Mb", usedMemory));
        assertTrue("5000 characters with 10 skills and 10 traits each should take less then 10 megabytes in memory for now", usedMemory < 30);

    }

    public double getUsedMemory(){
        return (runtime.totalMemory() - runtime.freeMemory()) * bytesToMegabytes;
    }
}
