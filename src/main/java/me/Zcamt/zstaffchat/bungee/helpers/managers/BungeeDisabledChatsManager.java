package me.Zcamt.zstaffchat.bungee.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BungeeDisabledChatsManager {

    private final BungeeChatManager bungeeChatManager;
    private final List<UUID> disabledStaffChats = new ArrayList<UUID>();
    private final List<UUID> disabledAdminChats = new ArrayList<UUID>();
    private final List<UUID> disabledDevChats = new ArrayList<UUID>();

    public BungeeDisabledChatsManager(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
    }

    public void disablePlayer(ProxiedPlayer player, ChatTypes chatType) {
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
        if(contains(player.getUniqueId(), chatType)){
            BungeeUtilities.sendMessage(player, prefix + "&r &7Is already disabled");
            return;
        }
        add(player.getUniqueId(), chatType);
        BungeeUtilities.sendMessage(player, prefix + "&r &7Disabled");
        if(bungeeChatManager.getDirectChatManager().contains(player.getUniqueId()) && bungeeChatManager.getDirectChatManager().get(player.getUniqueId()).equals(chatType)){
            bungeeChatManager.getDirectChatManager().unTogglePlayer(player);
        }
    }

    public void enablePlayer(ProxiedPlayer player, ChatTypes chatType) {
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
        if(!contains(player.getUniqueId(), chatType)){
            BungeeUtilities.sendMessage(player, prefix + "&r &7Is already enabled");
            return;
        }
        remove(player.getUniqueId(), chatType);
        BungeeUtilities.sendMessage(player, prefix + "&r &7Enabled");
    }

    public boolean contains(UUID uuid, ChatTypes chatType) {
        switch (chatType){
            default:
                return false;
            case STAFF_CHAT:
                return disabledStaffChats.contains(uuid);
            case ADMIN_CHAT:
                return disabledAdminChats.contains(uuid);
            case DEV_CHAT:
                return disabledDevChats.contains(uuid);
        }
    }

    public void remove(UUID uuid, ChatTypes chatType){
        switch (chatType){
            default:
            case STAFF_CHAT:
                disabledStaffChats.remove(uuid);
                break;
            case ADMIN_CHAT:
                disabledAdminChats.remove(uuid);
                break;
            case DEV_CHAT:
                disabledDevChats.remove(uuid);
                break;
        }
    }

    public void add(UUID uuid, ChatTypes chatType){
        switch (chatType){
            default:
            case STAFF_CHAT:
                disabledStaffChats.add(uuid);
                break;
            case ADMIN_CHAT:
                disabledAdminChats.add(uuid);
                break;
            case DEV_CHAT:
                disabledDevChats.add(uuid);
                break;
        }
    }

    /*public Set<UUID> keySet(){
        return toggledChats.keySet();
    }*/

}
