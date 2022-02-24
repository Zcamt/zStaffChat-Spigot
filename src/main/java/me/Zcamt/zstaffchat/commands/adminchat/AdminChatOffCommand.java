package me.Zcamt.zstaffchat.commands.adminchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
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

@CommandAlias("adminchatoff|acoff")
public class AdminChatOffCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final ChatTypes chatType = ChatTypes.ADMIN_CHAT;
    private final String permission = Permissions.ADMIN_CHAT.getPermission();

    public AdminChatOffCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            Utils.sendMessage(player, ConfigManager.adminchatPrefix + "&r &7Usage: /acoff");
            return;
        }
        disabledChatsManager.disablePlayer(player, chatType);
    }
}
