package org.example;

import java.util.ArrayList;

public class MaxHeap {

    private ArrayList<Player> heap = new ArrayList<>();

    // Insert player into heap
    public void insert(Player player) {
        heap.add(player);
        heapifyUp(heap.size() - 1);
    }

    // Move player upward
    private void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (heap.get(index).getScore()
                    > heap.get(parent).getScore()) {

                Player temp = heap.get(index);
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);

                index = parent;

            } else {
                break;
            }
        }
    }

    // Get player with highest score
    public Player getMax() {
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    // Remove highest-scoring player
    public Player removeMax() {

        if (heap.isEmpty()) {
            return null;
        }

        Player max = heap.get(0);

        Player last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return max;
    }

    // Move player downward
    private void heapifyDown(int index) {

        int size = heap.size();

        while (true) {

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int largest = index;

            if (left < size &&
                    heap.get(left).getScore()
                            > heap.get(largest).getScore()) {
                largest = left;
            }

            if (right < size &&
                    heap.get(right).getScore()
                            > heap.get(largest).getScore()) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            Player temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);

            index = largest;
        }
    }

    // Get number of players
    public int size() {
        return heap.size();
    }
}