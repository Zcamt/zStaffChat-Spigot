package me.Zcamt.zstaffchat.commands.devchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.utils.PermissionChecker;
import me.Zcamt.zstaffchat.utils.Utils;
import me.Zcamt.zstaffchat.managers.ChatManager;
import me.Zcamt.zstaffchat.managers.ConfigManager;
import me.Zcamt.zstaffchat.managers.DisabledChatsManager;
import org.bukkit.entity.Player;

@CommandAlias("devchat")
public class DevChatCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final String permission = Permissions.DEV_CHAT.getPermission();
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;

    public DevChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
    }

    @Default
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player,
                    ConfigManager.devchatPrefix + "&r &cError: You cannot send messages to a chat you've disabled");
            return;
        }

        if(args.length < 1){
            Utils.sendMessage(player, ConfigManager.devchatPrefix + "&r &7Usage: /devchat [MESSAGE]");
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        chatManager.sendMessageToChat(player, message.toString(), chatType);
    }


}
