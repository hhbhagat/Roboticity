package roboticity;

import java.awt.Robot;
import java.io.PrintStream;

public class RoboCommand {

    public RoboCommand() {
        robot = null;
        cmd = new RobotCMDConverter();
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Failed to init Robot object");
        }
    }

    public void commandParser(String input) {
        cmd.parseAndRun(input, robot);
    }

    private boolean isValidInput(String input) {
        return input.contains(";");
    }
    private Robot robot;
    private RobotCMDConverter cmd;
}