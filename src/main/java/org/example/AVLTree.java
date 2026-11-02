package org.example;
import java.util.List;
public class AVLTree {

    private class Node {
        Player player;
        Node left;
        Node right;
        int height;

        Node(Player player) {
            this.player = player;
            height = 1;
        }
    }

    private Node root;

    // Get height of a node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get balance factor
    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Update node height
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    // Right rotation
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node temp = x.right;

        x.right = y;
        y.left = temp;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left rotation
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node temp = y.left;

        y.left = x;
        x.right = temp;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert player
    public void insert(Player player) {
        root = insert(root, player);
    }

    private Node insert(Node node, Player player) {

        if (node == null) {
            return new Node(player);
        }

        if (player.getScore() < node.player.getScore()) {
            node.left = insert(node.left, player);
        } else {
            node.right = insert(node.right, player);
        }

        updateHeight(node);

        int balance = balanceFactor(node);

        // Left Left case
        if (balance > 1 &&
                player.getScore() < node.left.player.getScore()) {
            return rotateRight(node);
        }

        // Right Right case
        if (balance < -1 &&
                player.getScore() >= node.right.player.getScore()) {
            return rotateLeft(node);
        }

        // Left Right case
        if (balance > 1 &&
                player.getScore() >= node.left.player.getScore()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left case
        if (balance < -1 &&
                player.getScore() < node.right.player.getScore()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
    public void getPlayersDescending(List<Player> list) {
        getPlayersDescending(root, list);
    }

    private void getPlayersDescending(Node node, List<Player> list) {
        if (node == null) {
            return;
        }

        getPlayersDescending(node.right, list);
        list.add(node.player);
        getPlayersDescending(node.left, list);
    }
}