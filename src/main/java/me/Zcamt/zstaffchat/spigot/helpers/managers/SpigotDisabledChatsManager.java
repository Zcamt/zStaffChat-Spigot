package me.Zcamt.zstaffchat.spigot.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotUtilities;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpigotDisabledChatsManager {

    private final SpigotChatManager spigotChatManager;
    private final List<UUID> disabledStaffChats = new ArrayList<UUID>();
    private final List<UUID> disabledAdminChats = new ArrayList<UUID>();
    private final List<UUID> disabledDevChats = new ArrayList<UUID>();

    public SpigotDisabledChatsManager(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
    }

    public void disablePlayer(Player player, ChatTypes chatType) {
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
        if(contains(player.getUniqueId(), chatType)){
            SpigotUtilities.sendMessage(player, prefix + "&r &7Is already disabled");
            return;
        }
        add(player.getUniqueId(), chatType);
        SpigotUtilities.sendMessage(player, prefix + "&r &7Disabled");
        if(spigotChatManager.getDirectChatManager().contains(player.getUniqueId()) && spigotChatManager.getDirectChatManager().get(player.getUniqueId()).equals(chatType)){
            spigotChatManager.getDirectChatManager().unTogglePlayer(player);
        }
    }

    public void enablePlayer(Player player, ChatTypes chatType) {
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
        if(!contains(player.getUniqueId(), chatType)){
            SpigotUtilities.sendMessage(player, prefix + "&r &7Is already enabled");
            return;
        }
        remove(player.getUniqueId(), chatType);
        SpigotUtilities.sendMessage(player, prefix + "&r &7Enabled");
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
