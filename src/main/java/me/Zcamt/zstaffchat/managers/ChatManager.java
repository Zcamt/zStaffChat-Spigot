package me.Zcamt.zstaffchat.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.utils.PermissionChecker;
import me.Zcamt.zstaffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ChatManager {

    private final DirectChatManager directChatManager = new DirectChatManager();
    public DirectChatManager getDirectChatManager() {
        return directChatManager;
    }

    private final DisabledChatsManager disabledChatsManager = new DisabledChatsManager(this);
    public DisabledChatsManager getSpigotDisabledChatsManager() {
        return disabledChatsManager;
    }

    public void sendMessageToChat(Player sender, String message, ChatTypes chatType){
        String permission;
        String prefix;
        Collection<Player> playerList = (Collection<Player>) Bukkit.getOnlinePlayers();
        switch (chatType){
            default:
            case STAFF_CHAT:
                permission = Permissions.STAFF_CHAT.getPermission();
                prefix = ConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                permission = Permissions.ADMIN_CHAT.getPermission();
                prefix = ConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                permission = Permissions.DEV_CHAT.getPermission();
                prefix = ConfigManager.devchatPrefix;
                break;
        }
        for(Player target : playerList){
            if(PermissionChecker.hasPermission(target, permission)){
                Utils.sendMessage(target, ConfigManager.chatFormat
                        .replaceAll("%prefix%", prefix)
                        .replaceAll("%sender%", sender.getName())
                        .replaceAll("%message%", message));
            }
        }
    }

}
