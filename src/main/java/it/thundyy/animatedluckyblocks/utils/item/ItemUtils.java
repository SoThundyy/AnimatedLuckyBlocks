package it.thundyy.animatedluckyblocks.utils.item;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

@UtilityClass
public class ItemUtils {
    
    public boolean hasPersistentData(JavaPlugin plugin, ItemStack itemStack, String key) {
        return itemStack.hasItemMeta() &&
                itemStack.getItemMeta().getPersistentDataContainer()
                        .has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }
    
    public String getPersistentData(JavaPlugin plugin, ItemStack itemStack, String key) {
        return itemStack.getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }
}
