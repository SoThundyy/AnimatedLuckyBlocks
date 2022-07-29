package it.thundyy.animatedluckyblocks.utils.chat;

import it.thundyy.animatedluckyblocks.utils.chat.objects.Placeholder;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ChatUtils {
    
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public List<String> color(List<String> message, Placeholder... placeholders) {
        List<String> colored = new ArrayList<>();
        for (String line : message) {
            colored.add(color(line, placeholders));
        }
        
        return colored;
    }
    
    public String color(String message, Placeholder... placeholders) {
        for (Placeholder placeholder : placeholders) {
            message = message.replace(placeholder.key(), placeholder.value());
        }
        
        return color(message);
    }
}
