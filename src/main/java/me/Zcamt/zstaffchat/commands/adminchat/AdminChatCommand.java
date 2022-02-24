package me.Zcamt.zstaffchat.commands.adminchat;

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

@CommandAlias("adminchat")
public class AdminChatCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final String permission = Permissions.ADMIN_CHAT.getPermission();
    private final ChatTypes chatType = ChatTypes.ADMIN_CHAT;

    public AdminChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
    }

    @Default
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player,
                    ConfigManager.adminchatPrefix + "&r &cError: You cannot send messages to a chat you've disabled");
            return;
        }

        if(args.length < 1){
            Utils.sendMessage(player, ConfigManager.adminchatPrefix + "&r &7Usage: /adminchat [MESSAGE]");
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        chatManager.sendMessageToChat(player, message.toString(), chatType);
    }


}
