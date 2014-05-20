package roboticity;

import java.awt.BorderLayout;
import java.awt.Robot;
import java.util.ArrayList;
import javax.swing.*;

public class CommandBox extends JFrame {

    public CommandBox() {
        super("CommandBox");
        jtaArr = new ArrayList();
        robot = null;
        rc = null;
        super.setLayout(new BorderLayout());
    }

    public CommandBox(Robot robo, RoboCommand robocmd) {
        super("CommandBox");
        jtaArr = new ArrayList();
        robot = null;
        rc = null;
        super.setSize(700, 550);
        super.setDefaultCloseOperation(3);
        super.setResizable(true);
        robot = robo;
        rc = robocmd;
        super.setLayout(new BorderLayout());
    }

    public void addLabel() {
        JLabel jl = new JLabel();
        jl.setText("Welcome to the Java Robot Scripting Tool");
        super.add(jl, "North");
    }

    public void addTextBox() {
        JTextArea tf = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(tf);
        tf.setWrapStyleWord(false);
        jtaArr.add(tf);
        super.add(scrollPane, "Center");
    }

    public void addButton() {
        JButton jb = new JButton("Run Script");
        jb.addActionListener(new ButtonListener((JTextArea) jtaArr.get(jtaArr.size() - 1), rc));
        super.add(jb, "South");
    }

    public void addElems() {
        addTextBox();
        addButton();
    }

    public void display() {
        super.setVisible(true);
    }
    ArrayList jtaArr;
    private Robot robot;
    private RoboCommand rc;
}