package me.Zcamt.zstaffchat.bungee.commands.devchat;

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
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeDisabledChatsManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandAlias("devchaton|dcon")
public class BungeeDevChatOnCommand extends BaseCommand {

    private final BungeeChatManager bungeeChatManager;
    private final BungeeDisabledChatsManager disabledChatsManager;
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;
    private final String permission = Permissions.DEV_CHAT.getPermission();

    public BungeeDevChatOnCommand(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
        this.disabledChatsManager = bungeeChatManager.getDisabledChatsManager();
    }

    @Default @CatchUnknown
    public void onCommand(ProxiedPlayer player, String[] args){

        if(!BungeePermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            BungeeUtilities.sendMessage(player, BungeeConfigManager.devchatPrefix + "&r &7Usage: /dcon");
            return;
        }
        disabledChatsManager.enablePlayer(player, chatType);
    }
}
