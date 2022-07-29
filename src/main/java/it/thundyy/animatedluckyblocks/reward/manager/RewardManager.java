package it.thundyy.animatedluckyblocks.reward.manager;

import com.google.common.collect.Lists;
import it.thundyy.animatedluckyblocks.reward.Reward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class RewardManager {
    
    public static List<Reward> findRewardsFor(ConfigurationSection section) {
        ConfigurationSection rewardsSection = section.getConfigurationSection("rewards");
        if (rewardsSection == null) return null;
    
        List<Reward> rewards = Lists.newArrayList();
        for (String key : rewardsSection.getKeys(false)) {
            Reward reward = new Reward(rewardsSection.getInt(key + ".weight"));
            reward.addAll(rewardsSection.getStringList(key + ".actions"));
        }
        
        return rewards;
    }
}
