package it.thundyy.animatedluckyblocks.listeners;

import it.thundyy.animatedluckyblocks.LuckyBlockPlugin;
import it.thundyy.animatedluckyblocks.luckyblock.manager.LuckyBlockManager;
import it.thundyy.animatedluckyblocks.utils.item.ItemUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class LuckyBlockListener implements Listener {
    private final LuckyBlockPlugin plugin;
    private final LuckyBlockManager luckyBlockManager;
    
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack playerHandItem = event.getItemInHand();
        Block block = event.getBlockPlaced();
        
        if (playerHandItem.getType() == Material.AIR ||
                !ItemUtils.hasPersistentData(plugin, playerHandItem, "animated-lucky-block")) return;
        
        String persistentData = ItemUtils.getPersistentData(plugin, playerHandItem, "animated-lucky-block");
        if (persistentData == null) return;
        
        luckyBlockManager.getLuckyBlock(persistentData).ifPresent(luckyBlock -> {
            event.setCancelled(true);
            
            luckyBlockManager.placeLuckyBlock(block.getLocation(), luckyBlock);
        });
    }
}
