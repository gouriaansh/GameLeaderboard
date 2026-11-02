package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardManager {

    private AVLTree avlTree = new AVLTree();
    private MaxHeap maxHeap = new MaxHeap();

    private Map<String, Player> players = new HashMap<>();

    // Add a new player
    public void addPlayer(String name, int score) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }

        if (players.containsKey(name)) {
            throw new IllegalArgumentException("Player already exists.");
        }

        Player player = new Player(name, score);

        players.put(name, player);

        rebuildStructures();
    }

    // Update an existing player's score
    public void updateScore(String name, int newScore) {

        Player player = players.get(name);

        if (player == null) {
            throw new IllegalArgumentException("Player not found.");
        }

        player.setScore(newScore);

        rebuildStructures();
    }

    // Rebuild AVL Tree and Max Heap after changes
    private void rebuildStructures() {

        avlTree = new AVLTree();
        maxHeap = new MaxHeap();

        for (Player player : players.values()) {

            avlTree.insert(player);

            maxHeap.insert(player);
        }
    }

    // Get all players in descending score order using AVL Tree
    public List<Player> getLeaderboard() {

        List<Player> list = new ArrayList<>();

        avlTree.getPlayersDescending(list);

        return list;
    }

    // Get Top 10 players using Max Heap
    public List<Player> getTop10() {

        MaxHeap tempHeap = new MaxHeap();

        for (Player player : players.values()) {
            tempHeap.insert(player);
        }

        List<Player> topPlayers = new ArrayList<>();

        int count = Math.min(10, players.size());

        for (int i = 0; i < count; i++) {
            topPlayers.add(tempHeap.removeMax());
        }

        return topPlayers;
    }
}