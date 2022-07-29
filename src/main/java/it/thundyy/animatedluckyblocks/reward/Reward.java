package it.thundyy.animatedluckyblocks.reward;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Reward {
    private final int weight;
    private final List<String> rewards = Lists.newArrayList();
    
    public void add(String reward) {
        rewards.add(reward);
    }
    
    public void addAll(List<String> rewards) {
        this.rewards.addAll(rewards);
    }
}
