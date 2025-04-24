package com.paulvalue.ntminimum.service;

import com.paulvalue.ntminimum.service.algorithm.HeapManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberService {
    public int findNthMinimum(List<Integer> numbers, int n) {
        HeapManager heapManager = new HeapManager(n);
        numbers.forEach(heapManager::processNumber);
        return heapManager.getResult();
    }
}
