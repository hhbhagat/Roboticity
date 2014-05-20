package roboticity;

import java.awt.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Random;

public class RobotCMDConverter {

    public RobotCMDConverter() {
        mKeys = new HashMap();
        tk = Toolkit.getDefaultToolkit();
        screenDim = tk.getScreenSize();
        createHashtable();
    }

    private void createHashtable() {
        mKeys.put("0", new Integer(48));
        mKeys.put("1", new Integer(49));
        mKeys.put("2", new Integer(50));
        mKeys.put("3", new Integer(51));
        mKeys.put("4", new Integer(52));
        mKeys.put("5", new Integer(53));
        mKeys.put("6", new Integer(54));
        mKeys.put("7", new Integer(55));
        mKeys.put("8", new Integer(56));
        mKeys.put("9", new Integer(57));
        mKeys.put("a", new Integer(65));
        mKeys.put("b", new Integer(66));
        mKeys.put("c", new Integer(67));
        mKeys.put("d", new Integer(68));
        mKeys.put("e", new Integer(69));
        mKeys.put("f", new Integer(70));
        mKeys.put("g", new Integer(71));
        mKeys.put("h", new Integer(72));
        mKeys.put("i", new Integer(73));
        mKeys.put("j", new Integer(74));
        mKeys.put("k", new Integer(75));
        mKeys.put("l", new Integer(76));
        mKeys.put("m", new Integer(77));
        mKeys.put("n", new Integer(78));
        mKeys.put("o", new Integer(79));
        mKeys.put("p", new Integer(80));
        mKeys.put("q", new Integer(81));
        mKeys.put("r", new Integer(82));
        mKeys.put("s", new Integer(83));
        mKeys.put("t", new Integer(84));
        mKeys.put("u", new Integer(85));
        mKeys.put("v", new Integer(86));
        mKeys.put("w", new Integer(87));
        mKeys.put("x", new Integer(88));
        mKeys.put("y", new Integer(89));
        mKeys.put("z", new Integer(90));
        mKeys.put("A", new Integer(65));
        mKeys.put("B", new Integer(66));
        mKeys.put("C", new Integer(67));
        mKeys.put("D", new Integer(68));
        mKeys.put("E", new Integer(69));
        mKeys.put("F", new Integer(70));
        mKeys.put("G", new Integer(71));
        mKeys.put("H", new Integer(72));
        mKeys.put("I", new Integer(73));
        mKeys.put("J", new Integer(74));
        mKeys.put("K", new Integer(75));
        mKeys.put("L", new Integer(76));
        mKeys.put("M", new Integer(77));
        mKeys.put("N", new Integer(78));
        mKeys.put("O", new Integer(79));
        mKeys.put("P", new Integer(80));
        mKeys.put("Q", new Integer(81));
        mKeys.put("R", new Integer(82));
        mKeys.put("S", new Integer(83));
        mKeys.put("T", new Integer(84));
        mKeys.put("U", new Integer(85));
        mKeys.put("V", new Integer(86));
        mKeys.put("W", new Integer(87));
        mKeys.put("X", new Integer(88));
        mKeys.put("Y", new Integer(89));
        mKeys.put("Z", new Integer(90));
        mKeys.put(" ", new Integer(32));
        mKeys.put("space", new Integer(32));
        mKeys.put("enter", new Integer(10));
        mKeys.put("shift", new Integer(16));
        mKeys.put("esc", new Integer(27));
        mKeys.put("del", new Integer(127));
        mKeys.put("backspace", new Integer(8));
        mKeys.put("capslock", new Integer(20));
        mKeys.put("ctrl", new Integer(17));
        mKeys.put("alt", new Integer(18));
        mKeys.put("tab", new Integer(9));
        mKeys.put(".", new Integer(46));
        mKeys.put("down", new Integer(40));
        mKeys.put("up", new Integer(38));
        mKeys.put("left", new Integer(37));
        mKeys.put("right", new Integer(39));
        mKeys.put("-", new Integer(45));
        mKeys.put("f4", new Integer(115));
        mKeys.put("/", new Integer(47));
        mKeys.put(";", new Integer(59));
        mKeys.put(":", new Integer(59));
    }

    public void parseAndRun(String input, Robot robot) {
        input = input.replaceAll("\n", "").replaceAll("\r", "");
        String ins[] = input.split(";");
        for (int i = 0; i < ins.length;) {
            if (ins[i].equals(String.format(">", new Object[0]))) {
                continue;
            }
            try {
                parse(ins[i], robot);
                continue;
            } catch (Exception e) {
                System.out.print((new StringBuilder()).append("Problem with line number ").append(i).append(": ").toString());
                System.out.println(e.getMessage());
                i++;
            }
        }

    }

    private void parse(String input, Robot robot)
            throws Exception {
        if (input.contains("mmu")) {
            System.out.println((new StringBuilder()).append("before ").append(input).toString());
            input = input.replace("mmu", "");
            System.out.println((new StringBuilder()).append("after ").append(input).toString());
            int up;
            try {
                up = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                throw new Exception("Error parsing Mouse Up Command");
            }
            moveUp(up, robot);
        } else if (input.contains("mmd")) {
            input = input.replace("mmd", "");
            int down;
            try {
                down = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                throw new Exception("Error parsing Mouse Down Command");
            }
            moveDown(down, robot);
        } else if (input.contains("mml")) {
            input = input.replace("mml", "");
            int left;
            try {
                left = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                throw new Exception("Error parsing Mouse Left Command");
            }
            moveLeft(left, robot);
        } else if (input.contains("mmr")) {
            input = input.replace("mmr", "");
            int right;
            try {
                right = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                throw new Exception("Error parsing Mouse Right Command");
            }
            moveRight(right, robot);
        } else if (input.contains("mt")) {
            input = input.replace("mt", "").trim();
            String inp[] = input.split(",");
            try {
                robot.mouseMove(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]));
            } catch (NumberFormatException nfe) {
                throw new Exception("Error parsing Mouse Move Command");
            }
        } else if (input.contains("lc1")) {
            leftClick(robot);
        } else if (input.contains("lc2")) {
            doubleClick(robot);
        } else if (input.contains("rc")) {
            rightClick(robot);
        } else if (input.contains("type")) {
            typeString(input.replace("type", "").trim(), robot);
        } else if (input.contains("cmb")) {
            keyCombo(input.replace("cmb", ""), robot);
        } else if (input.contains("rs")) {
            int len;
            try {
                len = Integer.parseInt(input.replace("rs", "").trim());
            } catch (NumberFormatException a) {
                throw new Exception("Failed to parse random string size.");
            }
            typeString(getRandomString(len), robot);
        }
    }

    private void typeString(String input, Robot robot) {
        for (int index = 0; index < input.length(); index++) {
            char letter = input.charAt(index);
            if (letter >= 'A' && letter <= 'Z') {
                robot.keyPress(16);
            }
            Integer keyEvent = (Integer) mKeys.get(String.valueOf(letter));
            robot.keyPress(keyEvent.intValue());
            robot.keyRelease(keyEvent.intValue());
            robot.keyRelease(16);
        }

    }

    private int getXLoc() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    private int getYLoc() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    private void moveDown(int up, Robot robot) {
        up = Math.abs(up);
        up = getYLoc() + up;
        if (up > screenDim.height) {
            up = screenDim.height;
        } else if (up < 0) {
            up = 0;
        }
        robot.mouseMove(getXLoc(), up);
    }

    private void moveUp(int down, Robot robot) {
        down = 0 - Math.abs(down);
        down = getYLoc() + down;
        if (down > screenDim.height) {
            down = screenDim.height;
        } else if (down < 0) {
            down = 0;
        }
        robot.mouseMove(getXLoc(), down);
    }

    private void moveLeft(int left, Robot robot) {
        if (left > screenDim.width) {
            left = screenDim.width;
        } else if (left < 0) {
            left = 0;
        }
        robot.mouseMove(left, getYLoc());
    }

    private void moveRight(int right, Robot robot) {
        if (right > screenDim.width) {
            right = screenDim.width;
        } else if (right < 0) {
            right = 0;
        }
        robot.mouseMove(right, getYLoc());
    }

    private void leftClick(Robot robot) {
        robot.mousePress(1024);
        robot.mouseRelease(1024);
    }

    private void rightClick(Robot robot) {
        robot.mousePress(2048);
        robot.mouseRelease(2048);
    }

    private void doubleClick(Robot robot) {
        leftClick(robot);
        robot.delay(50);
        leftClick(robot);
    }

    private void keyCombo(String combo, Robot robot) {
        String cArr[] = combo.split("#");
        for (int i = 0; i < combo.length(); i++) {
            cArr[i] = cArr[i].toLowerCase();
            if (cArr[i].equals("alt")) {
                robot.keyPress(18);
                continue;
            }
            if (cArr[i].equals("win")) {
                robot.keyPress(524);
                continue;
            }
            if (cArr[i].equals("shift")) {
                robot.keyPress(16);
                continue;
            }
            if (cArr[i].equals("ctrl") || cArr[i].equals("control")) {
                robot.keyPress(17);
            } else {
                typeString(cArr[i], robot);
            }
        }

        robot.keyRelease(18);
        robot.keyRelease(16);
        robot.keyRelease(524);
        robot.keyRelease(17);
    }

    private static String getRandomString(int len) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) (rand.nextInt(26) + 97));
        }

        return sb.toString();
    }
    private HashMap mKeys;
    private Toolkit tk;
    private final Dimension screenDim;
}