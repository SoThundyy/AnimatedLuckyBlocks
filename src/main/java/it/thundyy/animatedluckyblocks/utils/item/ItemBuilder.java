package it.thundyy.animatedluckyblocks.utils.item;

import com.google.common.collect.Lists;
import it.thundyy.animatedluckyblocks.LuckyBlockPlugin;
import it.thundyy.animatedluckyblocks.utils.chat.ChatUtils;
import it.thundyy.animatedluckyblocks.utils.chat.objects.Placeholder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private final List<PersistentData> persistentData = Lists.newArrayList();
    private LuckyBlockPlugin plugin;
    private Material material;
    private Integer modelData;
    private String name;
    private List<String> lore;
    private List<Enchantment> enchantments;
    
    public ItemBuilder() {
    }
    
    public ItemBuilder(LuckyBlockPlugin plugin) {
        this.plugin = plugin;
    }
    
    public ItemBuilder(Material material) {
        this.material = material;
    }
    
    public ItemBuilder fromConfig(String path, Placeholder... placeholders) {
        this.material = Material.valueOf(plugin.getConfig().getString(path + ".material"));
        name(plugin.getConfig().getString(path + ".name"), placeholders);
        lore(plugin.getConfig().getStringList(path + ".lore"), placeholders);
        modelData(plugin.getConfig().getInt(path + ".modelData"));
        return this;
    }
    
    public ItemBuilder persistentData(String key, PersistentDataType<String, String> type, String value) {
        this.persistentData.add(new PersistentData(new NamespacedKey(plugin, key), type, value));
        return this;
    }
    
    public ItemBuilder enchant(Enchantment... enchantment) {
        this.enchantments = Arrays.asList(enchantment);
        return this;
    }
    
    public ItemBuilder name(String name, Placeholder... placeholders) {
        this.name = ChatUtils.color(name, placeholders);
        return this;
    }
    
    public ItemBuilder lore(List<String> lore, Placeholder... placeholders) {
        List<String> loreList = new ArrayList<>();
        
        for (String string : lore)
            loreList.add(ChatUtils.color(string, placeholders));
        
        this.lore = loreList;
        return this;
    }
    
    public ItemBuilder modelData(int modelData) {
        this.modelData = modelData;
        return this;
    }
    
    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }
    
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        
        if (name != null)
            itemMeta.setDisplayName(name);
        
        if (lore != null)
            itemMeta.setLore(lore);
        
        if (modelData != null) {
            itemMeta.setCustomModelData(modelData);
        }
        
        if (!persistentData.isEmpty()) {
            for (PersistentData data : persistentData)
                itemMeta.getPersistentDataContainer().set(data.key(), data.type(), data.value());
        }
        
        if (enchantments != null) {
            for (Enchantment enchantment : enchantments)
                itemMeta.addEnchant(enchantment, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
