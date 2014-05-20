package roboticity;

import java.awt.Robot;
import java.io.PrintStream;

public class Roboticity {

    public Roboticity() {
    }

    public static void main(String args[]) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println(e);
        }
        RoboCommand robocmd = new RoboCommand();
        CommandBox viewer = new CommandBox(robot, robocmd);
        viewer.addElems();
        viewer.display();
    }
}