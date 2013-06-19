package net.mortiy.gurps.rules.skills.modifiers;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class TaskDifficulty {
    enum Difficulty {
        /**
         * Tasks so trivial that the GM should waive the need for a success roll, except under extraordinary circumstances.
         * Example:  A Driving roll to start a car
         */
        Automatic,
        /**
         * Situations where failure is extremely unlikely, and would require incredibly bad luck.
         * Example: A Driving roll to drive around an empty parking lot.
         */
        Trivial,
        /**
         * Tasks where failure is possible, but would require bad luck.
         * Example: A Driving roll to drive down an empty suburban street.
         */
        VeryEasy,
        /**
         * Most mundane tasks, including rolls made by ordinary people at day-to-day jobs.
         * Example: A Driving roll to commute to work in a small town
         */
        Easy,
        /**
         * Mildly risky tasks that most people would undertake without hesitation.
         * Example:  A Driving roll to commute to work in a teeming metropolis.
         */
        VeryFavorable,
        /**
         * Tasks that most people would hesitate at, due to the risk, but that a career adventurer would regard as easy.
         * Example: A Driving roll to compete in a road rally
         */
        Favorable,
        /**
         * Most  adventuring tasks, and the majority of skill use under stress.
         * Example: A Driving roll in a car chase.
         */
        Average,
        /**
         * Stressful tasks that would challenge a novice adventurer, but not an old hand.
         * Example: A Driving roll in a high-speed car chase.
         */
        Unfavorable,
        /**
         * Stressful tasks that would challenge a professional.
         * Skilled adventurers still routinely accept such risks!
         * Example: A Driving roll in a highspeed car chase on a busy freeway
         */
        VeryUnfavorable,
        /**
         * Tasks so challenging that even an expert will look for alternatives.
         * A true “master” is still unlikely to feel challenged.
         * Example: A Driving roll to keep the car on the road while shooting a gun out the window during a highspeed chase.
         */
        Hard,
        /**
         * Situations that even the masters might have second thoughts about.
         * Example: A Driving roll in a high-speed chase during a blizzard
         */
        VeryHard,
        /**
         * Tasks at which even the greatest masters expect to fail.
         * Example: A Driving roll while shooting a gun in a high-speed chase during a blizzard
         */
        Dangerous,
        /**
         * No sane person would attempt such a task. The GM may wish to forbid such attempts altogether.
         * Example: A Driving roll to steer a car with the knees while firing a bazooka twohanded during a chase through a
         blizzard.
         */
        Impossible

    }
    int[] Modifiers = new int[] {10, 9, 7, 5, 3, 1, 0, -1, -3, -5, -7, -9, -10};

    public int getModifier(Difficulty difficulty){
        return Modifiers[difficulty.ordinal()];
    }
}
