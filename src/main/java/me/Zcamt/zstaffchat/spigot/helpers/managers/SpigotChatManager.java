package me.Zcamt.zstaffchat.spigot.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotPermissionChecker;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SpigotChatManager {

    private final SpigotDirectChatManager spigotDirectChatManager = new SpigotDirectChatManager();
    public SpigotDirectChatManager getDirectChatManager() {
        return spigotDirectChatManager;
    }

    private final SpigotDisabledChatsManager spigotDisabledChatsManager = new SpigotDisabledChatsManager(this);
    public SpigotDisabledChatsManager getSpigotDisabledChatsManager() {
        return spigotDisabledChatsManager;
    }

    public void sendMessageToChat(Player sender, String message, ChatTypes chatType){
        String permission;
        String prefix;
        Collection<Player> playerList = (Collection<Player>) Bukkit.getOnlinePlayers();
        switch (chatType){
            default:
            case STAFF_CHAT:
                permission = Permissions.STAFF_CHAT.getPermission();
                prefix = SpigotConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                permission = Permissions.ADMIN_CHAT.getPermission();
                prefix = SpigotConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                permission = Permissions.DEV_CHAT.getPermission();
                prefix = SpigotConfigManager.devchatPrefix;
                break;
        }
        for(Player target : playerList){
            if(SpigotPermissionChecker.hasPermission(target, permission)){
                SpigotUtilities.sendMessage(target, SpigotConfigManager.chatFormat
                        .replaceAll("%prefix%", prefix)
                        .replaceAll("%sender%", sender.getName())
                        .replaceAll("%message%", message));
            }
        }
    }

}
