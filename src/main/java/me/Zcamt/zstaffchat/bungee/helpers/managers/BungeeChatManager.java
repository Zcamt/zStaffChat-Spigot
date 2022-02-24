package me.Zcamt.zstaffchat.bungee.helpers.managers;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.bungee.helpers.BungeePermissionChecker;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

public class BungeeChatManager {

    private final BungeeDirectChatManager bungeeDirectChatManager = new BungeeDirectChatManager();
    public BungeeDirectChatManager getDirectChatManager() {
        return bungeeDirectChatManager;
    }

    private final BungeeDisabledChatsManager bungeeDisabledChatsManager = new BungeeDisabledChatsManager(this);
    public BungeeDisabledChatsManager getDisabledChatsManager() {
        return bungeeDisabledChatsManager;
    }

    public void sendMessageToChat(ProxiedPlayer sender, String message, ChatTypes chatType){
        String permission;
        String prefix;
        Collection<ProxiedPlayer> playerList = ProxyServer.getInstance().getPlayers();
        switch (chatType){
            default:
            case STAFF_CHAT:
                permission = Permissions.STAFF_CHAT.getPermission();
                prefix = BungeeConfigManager.staffchatPrefix;
                break;
            case ADMIN_CHAT:
                permission = Permissions.ADMIN_CHAT.getPermission();
                prefix = BungeeConfigManager.adminchatPrefix;
                break;
            case DEV_CHAT:
                permission = Permissions.DEV_CHAT.getPermission();
                prefix = BungeeConfigManager.devchatPrefix;
                break;
        }
        for(ProxiedPlayer target : playerList){
            if(BungeePermissionChecker.hasPermission(target, permission)){
                BungeeUtilities.sendMessage(target, BungeeConfigManager.chatFormat
                        .replaceAll("%prefix%", prefix)
                        .replaceAll("%sender%", sender.getName())
                        .replaceAll("%message%", message));
            }
        }
    }

}
