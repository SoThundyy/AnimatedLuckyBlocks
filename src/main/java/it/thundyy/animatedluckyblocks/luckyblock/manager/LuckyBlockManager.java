package it.thundyy.animatedluckyblocks.luckyblock.manager;

import it.thundyy.animatedluckyblocks.LuckyBlockPlugin;
import it.thundyy.animatedluckyblocks.luckyblock.LuckyBlock;
import it.thundyy.animatedluckyblocks.reward.Reward;
import it.thundyy.animatedluckyblocks.reward.manager.RewardManager;
import it.thundyy.animatedluckyblocks.utils.chat.objects.Placeholder;
import it.thundyy.animatedluckyblocks.utils.item.ItemBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class LuckyBlockManager {
    private final LuckyBlockPlugin plugin;
    private final Object2ObjectArrayMap<String, LuckyBlock> luckyBlockCache = new Object2ObjectArrayMap<>();
    private final Object2ObjectArrayMap<Location, LuckyBlock> placedLuckyBlocks = new Object2ObjectArrayMap<>();
    
    
    @SuppressWarnings("ConstantConditions")
    public void loadAllLuckyBlocks() {
        clear();
        
        ConfigurationSection general = plugin.getConfig().getConfigurationSection("lucky-blocks");
        if (general == null) return;
        
        for (String key : general.getKeys(false)) {
            ConfigurationSection section = general.getConfigurationSection(key);
            if (section == null) continue;
            
            // the key is the identifier of the lucky block (persitent-data / nbt)
            Material blockMaterial = Material.getMaterial(section.getString("block-type"));
            if (blockMaterial == null) blockMaterial = Material.STONE;
            
            // item section
            ConfigurationSection itemSection = section.getConfigurationSection("item");
            if (itemSection == null) continue;
            ItemStack item = new ItemBuilder(plugin)
                    .fromConfig(itemSection.getCurrentPath(),
                            new Placeholder("lucky-block-name", key)
                    ).persistentData("animated-lucky-block", PersistentDataType.STRING, key)
                    .build();
            
            LuckyBlock luckyBlock = new LuckyBlock(key, blockMaterial, item);
            luckyBlock.addAllRewards(RewardManager.findRewardsFor(section));
            
            // create the lucky block
            addLuckyBlock(luckyBlock);
        }
    }
    
    public void addLuckyBlock(LuckyBlock luckyBlock) {
        luckyBlockCache.put(luckyBlock.getIdentifier(), luckyBlock);
    }
    
    public void clear() {
        luckyBlockCache.clear();
    }
    
    public void placeLuckyBlock(Location location, LuckyBlock luckyBlock) {
        placedLuckyBlocks.put(location, luckyBlock.clone());
        
        handleLuckyBlockPlacing(location, luckyBlock);
    }
    
    public void breakLuckyBlock(Player player, Location location) {
        if (!placedLuckyBlocks.containsKey(location)) return;
        
        handleLuckyBlockBreaking(player, placedLuckyBlocks.get(location));
        placedLuckyBlocks.remove(location);
    }
    
    private void handleLuckyBlockPlacing(Location location, LuckyBlock luckyBlock) {
        Block blockAt = location.getBlock();
        blockAt.setType(luckyBlock.getBlockMaterial());
        blockAt.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, blockAt.getLocation(), 3);
    }
    
    private void handleLuckyBlockBreaking(Player player, LuckyBlock luckyBlock) {
    
    }
    
    public Optional<LuckyBlock> getLuckyBlock(String identifier) {
        return Optional.ofNullable(luckyBlockCache.get(identifier));
    }
}
