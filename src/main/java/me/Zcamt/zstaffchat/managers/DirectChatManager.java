package me.Zcamt.zstaffchat.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DirectChatManager {

    private final HashMap<UUID, ChatTypes> toggledChats = new HashMap<UUID, ChatTypes>();

    public void togglePlayer(Player player, ChatTypes chatType) {
        String prefix;
        switch (chatType){
            default:
            case STAFF_CHAT:
                prefix = ConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = ConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = ConfigManager.devchatPrefix;
                break;
        }
        toggledChats.put(player.getUniqueId(), chatType);
        Utils.sendMessage(player, prefix + "&r &7Toggled on direct messaging");
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
                prefix = ConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = ConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = ConfigManager.devchatPrefix;
                break;
        }
        toggledChats.remove(player.getUniqueId());
        Utils.sendMessage(player, prefix + "&r &7Toggled off direct messaging");
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
