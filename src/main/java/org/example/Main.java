package org.example;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LeaderboardGUI gui = new LeaderboardGUI();

            gui.setVisible(true);

        });
    }
}