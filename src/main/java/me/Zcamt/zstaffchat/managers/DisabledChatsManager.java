package me.Zcamt.zstaffchat.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DisabledChatsManager {

    private final ChatManager chatManager;
    private final List<UUID> disabledStaffChats = new ArrayList<UUID>();
    private final List<UUID> disabledAdminChats = new ArrayList<UUID>();
    private final List<UUID> disabledDevChats = new ArrayList<UUID>();

    public DisabledChatsManager(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    public void disablePlayer(Player player, ChatTypes chatType) {
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
        if(contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player, prefix + "&r &7Is already disabled");
            return;
        }
        add(player.getUniqueId(), chatType);
        Utils.sendMessage(player, prefix + "&r &7Disabled");
        if(chatManager.getDirectChatManager().contains(player.getUniqueId()) && chatManager.getDirectChatManager().get(player.getUniqueId()).equals(chatType)){
            chatManager.getDirectChatManager().unTogglePlayer(player);
        }
    }

    public void enablePlayer(Player player, ChatTypes chatType) {
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
        if(!contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player, prefix + "&r &7Is already enabled");
            return;
        }
        remove(player.getUniqueId(), chatType);
        Utils.sendMessage(player, prefix + "&r &7Enabled");
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
