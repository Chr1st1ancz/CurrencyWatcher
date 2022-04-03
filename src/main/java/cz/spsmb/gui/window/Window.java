package cz.spsmb.gui.window;

import javax.swing.*;

public class Window {

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, "Information: " + infoMessage, "Info: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorBox(String errorMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, "Error Message:\n" + errorMessage, "Error: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }

    public static void errorBox(String errorMessage, String errorInformation, String titleBar) {
        JOptionPane.showMessageDialog(null, "Error Message:\n" + errorMessage + "\n\nInformation: " + errorInformation, "Error: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }

}
