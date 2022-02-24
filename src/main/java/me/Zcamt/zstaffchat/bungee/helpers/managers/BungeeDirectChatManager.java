package me.Zcamt.zstaffchat.bungee.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class BungeeDirectChatManager {

    private final HashMap<UUID, ChatTypes> toggledDirectMessagingChats = new HashMap<UUID, ChatTypes>();

    public void togglePlayer(ProxiedPlayer player, ChatTypes chatType) {
        String prefix;
        switch (chatType){
            default:
            case STAFF_CHAT:
                prefix = BungeeConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = BungeeConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = BungeeConfigManager.devchatPrefix;
                break;
        }
        toggledDirectMessagingChats.put(player.getUniqueId(), chatType);
        BungeeUtilities.sendMessage(player, prefix + "&r &7Toggled on direct messaging");
    }

    public boolean contains(UUID uuid) {
        return toggledDirectMessagingChats.containsKey(uuid);
    }

    public void unTogglePlayer(ProxiedPlayer player) {
        if(!contains(player.getUniqueId())) return;
        ChatTypes chatType = get(player.getUniqueId());
        String prefix;
        switch (chatType){
            default:
            case STAFF_CHAT:
                prefix = BungeeConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                prefix = BungeeConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                prefix = BungeeConfigManager.devchatPrefix;
                break;
        }
        toggledDirectMessagingChats.remove(player.getUniqueId());
        BungeeUtilities.sendMessage(player, prefix + "&r &7Toggled off direct messaging");
    }

    public ChatTypes get(UUID uuid) {
        return toggledDirectMessagingChats.get(uuid);
    }

    public void remove(UUID uuid){
        toggledDirectMessagingChats.remove(uuid);
    }

    /*public Set<UUID> keySet(){
        return toggledChats.keySet();
    }*/

}
