package com.paulvalue.ntminimum.service.algorithm;

import com.paulvalue.ntminimum.exception.InsufficientElementsException;

import java.util.Objects;
import java.util.PriorityQueue;

public class HeapManager {
    private final PriorityQueue<Integer> maxHeap;
    private final int capacity;

    public HeapManager(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.maxHeap = new PriorityQueue<>(capacity, (a, b) -> b - a);
    }

    public void processNumber(int num) {
        if (maxHeap.size() < capacity) {
            maxHeap.offer(num);
        } else if (num < Objects.requireNonNull(maxHeap.peek())) {
            maxHeap.poll();
            maxHeap.offer(num);
        }
    }

    public int getResult() {
        if (maxHeap.isEmpty()) {
            throw new InsufficientElementsException("No elements found in the file");
        }
        if (maxHeap.size() < capacity) {
            throw new InsufficientElementsException(
                    "Not enough elements in the file for given N");
        }
        return maxHeap.peek();
    }
}
