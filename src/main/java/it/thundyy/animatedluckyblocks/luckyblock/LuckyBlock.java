package it.thundyy.animatedluckyblocks.luckyblock;

import it.thundyy.animatedluckyblocks.reward.Reward;
import it.thundyy.animatedluckyblocks.utils.datastorage.WeightedCollection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class LuckyBlock implements Cloneable {
    private final String identifier;
    private final Material blockMaterial;
    private final ItemStack item;
    private final WeightedCollection<Reward> rewards = new WeightedCollection<>();
    
    public void addAllRewards(List<Reward> rewards) {
        for (Reward reward : rewards) {
            this.rewards.add(reward, reward.getWeight());
        }
    }
    
    @Override
    public LuckyBlock clone() {
        try {
            return (LuckyBlock) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
