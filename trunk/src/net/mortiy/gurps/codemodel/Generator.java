package net.mortiy.gurps.codemodel;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.codemodel.*;
import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.Reach;
import net.mortiy.gurps.rules.skills.Skill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 21.01.13
 * Time: 0:08
 * To change this template use File | Settings | File Templates.
 */
public class Generator {

    private JCodeModel cm = new JCodeModel();

    public Generator() {
    }

    private final String PACKAGE = "net.mortiy.gurps.rules";
    private final String CODEMODEL_PACKAGE = "net.mortiy.gurps.rules";
    private final String EQUIPMENT_WEAPONS_ALL = CODEMODEL_PACKAGE + ".equipment.weapon.all";
    private final String EQUIPMENT_MELEE_DAMAGE = PACKAGE + ".equipment.weapon.statistics.MusclePoweredMeleeWeaponMode";
    private final String TECH_LEVEL = PACKAGE + ".TechLevel.Level";
    private final String MUSCLE_WEAPON_DAMAGE_TYPE = PACKAGE + ".equipment.weapon.statistics.MusclePoweredDamage";
    private final String DAMAGE_TYPE = PACKAGE + ".combat.Damage";
    private final String PARRY_TYPE = PACKAGE + ".equipment.weapon.statistics.Parry";
    private final String WEAPON_DAMAGE = PACKAGE + ".equipment.weapon.statistics.WeaponDamage";


    JClass MeleeMusclePoweredDamageClass = cm.directClass(EQUIPMENT_MELEE_DAMAGE);
    JClass WeaponDamageClass = cm.directClass(WEAPON_DAMAGE);

    JClass ClassClass = cm.directClass("java.lang.Class");

    JClass TechLevelEnum = cm.directClass(TECH_LEVEL);
    JClass MeleeDamageEnum = cm.directClass(MUSCLE_WEAPON_DAMAGE_TYPE);
    JClass WeaponDamageEnum = cm.directClass(DAMAGE_TYPE);
    JClass ParryTypeEnum = cm.directClass(PARRY_TYPE);


    public void generateWeapons() {

        JPackage jp = cm._package(EQUIPMENT_WEAPONS_ALL);

        try {
            File file = new File("trunk/data/weapons.json");
            System.out.println(file.getAbsolutePath());

            JsonFactory f = new JsonFactory();
            JsonParser p = f.createJsonParser(file);

            // Move into 'Weapons' array:
            p.nextToken();
            p.nextToken();

            JInvocation superCall = null;

            // Loop thru all weapons:
            JDefinedClass jc = null;
            while (p.nextToken() != JsonToken.END_ARRAY) {

                if (p.getCurrentToken() == JsonToken.FIELD_NAME) {

                    String fieldName = p.getCurrentName();

                    switch (fieldName) {
                        case "name":
                            String weaponName = p.nextTextValue();
                            String className = getWeaponClassName(weaponName);
                            jc = jp._class(className);

                            jc._extends(MusclePoweredMeleeWeapon.class);

                            // Constructor:
                            JMethod constructor = jc.constructor(JMod.PUBLIC);
                            JBlock jb = constructor.body();


                            // Super invocator:
                            superCall = jb.invoke("super");
                            superCall.arg(weaponName);
                            break;
                        case "interface":
                             String interfaceName = getWeaponClassName(p.nextTextValue());

                            JClass interfaceClass = jp._getClass(interfaceName);
                            if(interfaceClass == null){
                                interfaceClass = jp._interface(interfaceName);
                            }

                            jc._implements(interfaceClass);

                            break;
                        case "skills":
                            JArray skillClasses = JExpr.newArray(ClassClass);
                            p.nextToken();
                            while (p.nextToken() != JsonToken.END_ARRAY) {
                                if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
                                    // Find skill class by name:
                                    Class skillClass = Skill.forName(p.getValueAsString());
                                    // Add skill class to array
                                    skillClasses.add(cm.ref(skillClass).staticRef("class"));
                                }
                            }
                            superCall.arg(skillClasses);
                            break;
                        case "TL":
                            superCall.arg(TechLevelEnum.staticRef(p.nextTextValue()));
                            break;
                        case "modes":
                            p.nextToken();

                            JArray weaponModes = JExpr.newArray(MeleeMusclePoweredDamageClass);

                            while (p.nextToken() != JsonToken.END_ARRAY) {
                                JInvocation weaponMode = null;
                                JInvocation weaponDamage = null;
                                while (p.nextToken() != JsonToken.END_OBJECT) {


                                    String modeField = p.getCurrentName();
                                    switch (modeField) {
                                        case "type":
                                            p.nextToken();
                                            JFieldRef meleeDamageType = MeleeDamageEnum.staticRef("Type").ref(p.nextTextValue());
                                            JExpression damageModifier = JExpr.lit(p.nextIntValue(0));
                                            JFieldRef damageType = WeaponDamageEnum.staticRef("Type").ref(p.nextTextValue());

                                            weaponDamage = JExpr._new(WeaponDamageClass)
                                                    .arg(JExpr.lit(0)) // Dice roll
                                                    .arg(damageModifier) // Modifier
                                                    .arg(damageType);

                                            weaponMode = JExpr._new(MeleeMusclePoweredDamageClass).arg(meleeDamageType);

                                            p.nextToken();
                                            if(p.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT){
                                                weaponDamage = weaponDamage.arg(JExpr.lit(p.getFloatValue()));
                                                p.nextToken();
                                            }
                                            weaponMode = weaponMode.arg(weaponDamage);
                                            break;

                                        case "reach":
                                            p.nextToken(); // Start array
                                            while (p.nextValue() != JsonToken.END_ARRAY) {
                                                String arg = p.getText().trim();
                                                JInvocation addReach = weaponMode.invoke("addReach");
                                                // Close reach
                                                if (arg.equals("C")) {
                                                    addReach = addReach.arg(JExpr.lit(Reach.HAS_CLOSE_COMBAT));
                                                } else {
                                                    // Get reach distance:
                                                    if (arg.contains("-")) {
                                                        final int MIN = 0;
                                                        final int MAX = 1;

                                                        String range[] = arg.replaceAll("[^0-9\\-]","").split("-");
                                                        addReach.arg(JExpr.lit(Integer.parseInt(range[MIN])));
                                                        addReach.arg(JExpr.lit(Integer.parseInt(range[MAX])));
                                                    } else {
                                                        addReach = addReach.arg(JExpr.lit(Integer.parseInt(arg)));
                                                    }

                                                    // Check for flags:
                                                    if (arg.contains("*")) {
                                                        addReach = addReach.arg(JExpr.lit(Reach.REQUIRES_READY_MANEUVER));
                                                    } else {
                                                        addReach = addReach.arg(JExpr.lit(Reach.NO_FLAGS));
                                                    }
                                                }

                                                weaponMode = addReach;

                                            }
                                            break;
                                        case "parry":
                                            final int PARRY_MODIFIER = 0;
                                            final int PARRY_TYPE = 1;

                                            String parryArg = p.nextTextValue().trim();
                                            JInvocation setParry = weaponMode.invoke("setParry");
                                            if (parryArg.equals("No")) {
                                                setParry.arg(JExpr.lit(0)).arg(ParryTypeEnum.staticRef("Type").ref("No"));
                                            } else {
                                                JExpression parryTypeExpr = null;
                                                String parryParts[] = parryArg.split(" ");

                                                int parryModifier = Integer.parseInt(parryParts[PARRY_MODIFIER]);

                                                if (parryParts.length > 1) {
                                                    String parryType = parryParts[PARRY_TYPE];
                                                    switch (parryType) {
                                                        case "U":
                                                            parryTypeExpr = ParryTypeEnum.staticRef("Type").ref("Unbalanced");
                                                            break;
                                                        case "F":
                                                            parryTypeExpr = ParryTypeEnum.staticRef("Type").ref("Fencing");
                                                            break;
                                                        default:
                                                            Log.w("Generator", "Unknown parry type: %s", parryType);
                                                    }
                                                    setParry.arg(JExpr.lit(parryModifier)).arg(parryTypeExpr);
                                                } else {
                                                    setParry.arg(JExpr.lit(parryModifier));
                                                }
                                            }

                                            weaponMode = setParry;
                                            break;
                                        default:
                                            throw new IllegalStateException("Unrecognized field at Weapon Mode: '" + modeField + "'!");

                                    }
                                }
                                weaponModes.add(weaponMode);
                            }
                            superCall.arg(weaponModes);
                            break;
                        case "price":
                            superCall.arg(JExpr.lit(p.nextIntValue(0))); // Price
                            break;
                        case "weight":
                            p.nextValue();
                            superCall.arg(JExpr.lit(p.getFloatValue())); // Weight
                            break;
                        case "minStrength":
                            superCall.arg(JExpr.lit(p.nextIntValue(0))); // Minimal strength
                            break;
                        default:
                            throw new IllegalStateException("Unrecognized field '" + p.getCurrentName() + "' of type " + p.getCurrentToken() + "!");
                    }
                }
            }
            cm.build(new File("trunk/src"));
            p.close(); // ensure resources get cleaned up timely and properly
        } catch(FileNotFoundException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            System.err.println("Skill class not found: " + e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public void processField(String fieldname) {
        System.out.println(fieldname);

    }

    public void processField(Integer fieldname) {
        System.out.println(fieldname);

    }

    public void generateWeapon(String weaponName) {
        generateWeapon(weaponName, null);
    }

    public void generateWeapon(String weaponName, Class superClass) {
        // TODO: Weapon generator

        try {

            JPackage jp = cm._package(EQUIPMENT_WEAPONS_ALL);
            JDefinedClass jc = jp._class(getWeaponClassName(weaponName));

            if (superClass != null) {
                jc._extends(superClass);
            }

            // Constructor:
            JMethod constructor = jc.constructor(JMod.PUBLIC);
            JBlock jb = constructor.body();

            // Super invocator:
            JInvocation superInvocation = jb.invoke("super");
            superInvocation.arg(weaponName);

            // Tech level
            superInvocation.arg(TechLevelEnum.staticRef("TL0"));

            // Damage types:
            JArray damagesArray = JExpr.newArray(MeleeMusclePoweredDamageClass);
            damagesArray.add(
                    JExpr._new(MeleeMusclePoweredDamageClass)
                            .arg(MeleeDamageEnum.staticRef("Type").ref("Swinging"))
                            .arg(JExpr.lit(-3))
                            .arg(WeaponDamageEnum.staticRef("Type").ref("Cutting"))
            );
            superInvocation.arg(damagesArray);

            superInvocation.arg(JExpr.lit(30)); // Price
            superInvocation.arg(JExpr.lit(0.5f)); // Weight
            superInvocation.arg(JExpr.lit(5)); // Minimal strength

            cm.build(new File("trunk/src"));

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private String getWeaponClassName(String string) {
        return string.replaceAll("[\\-\\s/]+", "").trim() + "Weapon";
    }
}
