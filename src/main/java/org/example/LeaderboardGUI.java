
package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class LeaderboardGUI extends JFrame {

    private final LeaderboardManager manager = new LeaderboardManager();

    private JTextField nameField;
    private JTextField scoreField;

    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    // Theme colors
    private final Color BACKGROUND = new Color(15, 23, 42);
    private final Color PANEL = new Color(30, 41, 59);
    private final Color ACCENT = new Color(56, 189, 248);
    private final Color TEXT = new Color(226, 232, 240);
    private final Color MUTED = new Color(148, 163, 184);

    public LeaderboardGUI() {

        setTitle("Game Leaderboard | Gaming Dashboard");
        setSize(900, 650);
        setMinimumSize(new Dimension(750, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 20, 30)
        );

        // ---------------- HEADER ----------------

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("GAME LEADERBOARD");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(ACCENT);

        JLabel subtitle = new JLabel(
                "REAL-TIME PLAYER RANKINGS"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ---------------- CENTER CONTENT ----------------

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setOpaque(false);

        // Player input card
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(PANEL);
        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(51, 65, 85)
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = createLabel("PLAYER NAME");
        JLabel scoreLabel = createLabel("PLAYER SCORE");

        nameField = createTextField();
        scoreField = createTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.25;
        inputPanel.add(scoreLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;
        inputPanel.add(scoreField, gbc);

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        // ---------------- LEADERBOARD TABLE ----------------

        String[] columns = {
                "RANK", "PLAYER NAME", "SCORE"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setRowHeight(38);
        table.setBackground(PANEL);
        table.setForeground(TEXT);
        table.setSelectionBackground(new Color(30, 64, 175));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(51, 65, 85));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );
        table.getTableHeader().setBackground(
                new Color(51, 65, 85)
        );
        table.getTableHeader().setForeground(ACCENT);
        table.getTableHeader().setPreferredSize(
                new Dimension(0, 40)
        );
        table.getTableHeader().setReorderingAllowed(false);

        // Center rank and score columns
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        table.getColumnModel().getColumn(0)
                .setCellRenderer(centerRenderer);

        table.getColumnModel().getColumn(2)
                .setCellRenderer(centerRenderer);

        // Column widths
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(80);

        table.getColumnModel().getColumn(1)
                .setPreferredWidth(400);

        table.getColumnModel().getColumn(2)
                .setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(51, 65, 85)
                )
        );

        scrollPane.getViewport().setBackground(PANEL);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ---------------- BUTTONS ----------------

        JButton addButton = createButton(
                "ADD PLAYER", ACCENT
        );

        JButton updateButton = createButton(
                "UPDATE SCORE", new Color(99, 102, 241)
        );

        JButton leaderboardButton = createButton(
                "LEADERBOARD", new Color(16, 185, 129)
        );

        JButton top10Button = createButton(
                "TOP 10", new Color(245, 158, 11)
        );

        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 4, 12, 0)
        );

        buttonPanel.setOpaque(false);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(top10Button);

        // Footer status
        statusLabel = new JLabel(
                "Ready | Add players to start building your leaderboard"
        );

        statusLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        statusLabel.setForeground(MUTED);

        JPanel footer = new JPanel(new BorderLayout(0, 12));
        footer.setOpaque(false);

        footer.add(buttonPanel, BorderLayout.NORTH);
        footer.add(statusLabel, BorderLayout.SOUTH);

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        // ---------------- BUTTON ACTIONS ----------------

        addButton.addActionListener(e -> addPlayer());

        updateButton.addActionListener(e -> updateScore());

        leaderboardButton.addActionListener(
                e -> showLeaderboard()
        );

        top10Button.addActionListener(
                e -> showTop10()
        );
    }

    // Create styled label
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        label.setForeground(MUTED);

        return label;
    }

    // Create styled text field
    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        field.setForeground(TEXT);
        field.setBackground(BACKGROUND);
        field.setCaretColor(ACCENT);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(71, 85, 105)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 10, 8, 10
                        )
                )
        );

        return field;
    }

    // Create styled button
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        button.setForeground(BACKGROUND);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setPreferredSize(
                new Dimension(150, 42)
        );

        return button;
    }

    // Add player
    private void addPlayer() {

        try {

            String name = nameField.getText().trim();
            int score = Integer.parseInt(
                    scoreField.getText().trim()
            );

            manager.addPlayer(name, score);

            statusLabel.setText(
                    "Player added successfully: " + name
            );

            clearFields();
            showLeaderboard();

        } catch (NumberFormatException ex) {

            showError("Please enter a valid numeric score.");

        } catch (IllegalArgumentException ex) {

            showError(ex.getMessage());
        }
    }

    // Update player score
    private void updateScore() {

        try {

            String name = nameField.getText().trim();
            int score = Integer.parseInt(
                    scoreField.getText().trim()
            );

            manager.updateScore(name, score);

            statusLabel.setText(
                    "Score updated successfully: " + name
            );

            clearFields();
            showLeaderboard();

        } catch (NumberFormatException ex) {

            showError("Please enter a valid numeric score.");

        } catch (IllegalArgumentException ex) {

            showError(ex.getMessage());
        }
    }

    // Show full leaderboard
    private void showLeaderboard() {

        List<Player> players = manager.getLeaderboard();

        displayPlayers(players);

        statusLabel.setText(
                "Leaderboard | Total players: " + players.size()
        );
    }

    // Show Top 10 players
    private void showTop10() {

        List<Player> players = manager.getTop10();

        displayPlayers(players);

        statusLabel.setText(
                "Top 10 | Players displayed: " + players.size()
        );
    }

    // Display players in table
    private void displayPlayers(List<Player> players) {

        tableModel.setRowCount(0);

        int rank = 1;

        for (Player player : players) {

            tableModel.addRow(new Object[]{
                    rank,
                    player.getName(),
                    player.getScore()
            });

            rank++;
        }
    }

    // Clear input fields
    private void clearFields() {

        nameField.setText("");
        scoreField.setText("");
        nameField.requestFocus();
    }

    // Show error message
    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
