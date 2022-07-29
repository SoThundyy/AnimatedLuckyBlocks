package it.thundyy.animatedluckyblocks.utils.item;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;


public record PersistentData(NamespacedKey key, PersistentDataType<String, String> type, String value) {
}
