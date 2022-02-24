package me.Zcamt.zstaffchat.bungee.commands.adminchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.bungee.helpers.BungeePermissionChecker;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeChatManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeConfigManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeDirectChatManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeDisabledChatsManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandAlias("adminchattoggle|toggleadminchat|toggleac|actoggle")
public class BungeeToggleAdminChatCommand extends BaseCommand {

    private final BungeeChatManager bungeeChatManager;
    private final BungeeDisabledChatsManager disabledChatsManager;
    private final BungeeDirectChatManager directChatManager;
    private final ChatTypes chatType = ChatTypes.ADMIN_CHAT;
    private final String permission = Permissions.ADMIN_CHAT.getPermission();

    public BungeeToggleAdminChatCommand(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
        this.disabledChatsManager = bungeeChatManager.getDisabledChatsManager();
        this.directChatManager = bungeeChatManager.getDirectChatManager();
    }

    @Default @CatchUnknown
    public void onCommand(ProxiedPlayer player, String[] args){

        if(!BungeePermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            BungeeUtilities.sendMessage(player, BungeeConfigManager.adminchatPrefix + "&r &7Usage: /actoggle");
            return;
        }
        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            BungeeUtilities.sendMessage(player, BungeeConfigManager.adminchatPrefix + "&r &cError: Cannot toggle a chat you've disabled");
            return;
        }
        if(directChatManager.contains(player.getUniqueId()) && directChatManager.get(player.getUniqueId()).equals(chatType)){
            directChatManager.unTogglePlayer(player);
        } else {
            directChatManager.unTogglePlayer(player);
            directChatManager.togglePlayer(player, chatType);
        }
    }
}
