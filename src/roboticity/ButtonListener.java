package roboticity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ButtonListener implements ActionListener {

    public ButtonListener(JTextArea ta, RoboCommand robo) {
        tiedField = null;
        roboCMD = null;
        tiedField = ta;
        roboCMD = robo;
    }

    public ButtonListener() {
        tiedField = null;
        roboCMD = null;
    }

    public void actionPerformed(ActionEvent e) {
        String input = tiedField.getText().replace(String.format("%n", new Object[0]), "");
        int dialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to execute the script?", "Warning", 0);
        if (dialog == 0) {
            roboCMD.commandParser(input);
        }
    }
    private JTextArea tiedField;
    private RoboCommand roboCMD;
}