package me.Zcamt.zstaffchat.bungee.commands.devchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.bungee.helpers.BungeePermissionChecker;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeChatManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeConfigManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeDisabledChatsManager;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotConfigManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandAlias("devchat")
public class BungeeDevChatCommand extends BaseCommand {

    private final BungeeChatManager bungeeChatManager;
    private final BungeeDisabledChatsManager disabledChatsManager;
    private final String permission = Permissions.DEV_CHAT.getPermission();
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;

    public BungeeDevChatCommand(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
        this.disabledChatsManager = bungeeChatManager.getDisabledChatsManager();
    }

    @Default
    public void onCommand(ProxiedPlayer player, String[] args){

        if(!BungeePermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            BungeeUtilities.sendMessage(player,
                    SpigotConfigManager.devchatPrefix + "&r &cError: You cannot send messages to a chat you've disabled");
            return;
        }

        if(args.length < 1){
            BungeeUtilities.sendMessage(player, BungeeConfigManager.devchatPrefix + "&r &7Usage: /devchat [MESSAGE]");
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        bungeeChatManager.sendMessageToChat(player, message.toString(), ChatTypes.DEV_CHAT);
    }


}