package me.Zcamt.zstaffchat.spigot.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotUtilities;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SpigotDirectChatManager {

    private final HashMap<UUID, ChatTypes> toggledChats = new HashMap<UUID, ChatTypes>();

    public void togglePlayer(Player player, ChatTypes chatType) {
        String prefix;
        switch (chatType){
            default:
            case STAFF_CHAT:
                prefix = SpigotConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = SpigotConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = SpigotConfigManager.devchatPrefix;
                break;
        }
        toggledChats.put(player.getUniqueId(), chatType);
        SpigotUtilities.sendMessage(player, prefix + "&r &7Toggled on direct messaging");
    }

    public boolean contains(UUID uuid) {
        return toggledChats.containsKey(uuid);
    }

    public void unTogglePlayer(Player player) {
        if(!contains(player.getUniqueId())) return;
        ChatTypes chatType = get(player.getUniqueId());
        String prefix;
        switch (chatType){
            default:
            case STAFF_CHAT:
                prefix = SpigotConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = SpigotConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = SpigotConfigManager.devchatPrefix;
                break;
        }
        toggledChats.remove(player.getUniqueId());
        SpigotUtilities.sendMessage(player, prefix + "&r &7Toggled off direct messaging");
    }

    public ChatTypes get(UUID uuid) {
        return toggledChats.get(uuid);
    }

    public void remove(UUID uuid){
        toggledChats.remove(uuid);
    }

    /*public Set<UUID> keySet(){
        return toggledChats.keySet();
    }*/

}
