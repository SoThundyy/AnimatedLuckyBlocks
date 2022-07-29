package it.thundyy.animatedluckyblocks.utils.datastorage;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.NavigableMap;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedCollection<T> {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private final NavigableMap<Integer, T> weightedItems = Maps.newTreeMap();
    private int totalWeight = 0;
    
    public void add(T item, int weight) {
        if (weight <= 0) return;
        
        weightedItems.put(totalWeight, item);
        totalWeight += weight;
    }
    
    public T getRandom() {
        int randomWeight = RANDOM.nextInt(totalWeight) + 1;
        return weightedItems.ceilingEntry(randomWeight).getValue();
    }
}
