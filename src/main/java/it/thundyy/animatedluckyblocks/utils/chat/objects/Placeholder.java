package it.thundyy.animatedluckyblocks.utils.chat.objects;

public record Placeholder(String key, String value) {
    
    public Placeholder(String key, String value) {
        this.key = "%" + key + "%";
        this.value = value;
    }
}
