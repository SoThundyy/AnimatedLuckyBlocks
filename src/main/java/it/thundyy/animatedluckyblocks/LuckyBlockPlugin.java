package it.thundyy.animatedluckyblocks;

import it.thundyy.animatedluckyblocks.luckyblock.manager.LuckyBlockManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LuckyBlockPlugin extends JavaPlugin {
    private LuckyBlockManager luckyBlockManager;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadInstances();
        
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    private void loadInstances() {
        luckyBlockManager = new LuckyBlockManager(this);
        luckyBlockManager.loadAllLuckyBlocks();
    }
}
