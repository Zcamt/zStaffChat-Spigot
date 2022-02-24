package me.Zcamt.zstaffchat.commands.staffchat;

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

@CommandAlias("staffchatoff|scoff")
public class StaffChatOffCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final ChatTypes chatType = ChatTypes.STAFF_CHAT;
    private final String permission = Permissions.STAFF_CHAT.getPermission();

    public StaffChatOffCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            Utils.sendMessage(player, ConfigManager.staffchatPrefix + "&r &7Usage: /scoff");
            return;
        }
        disabledChatsManager.disablePlayer(player, chatType);
    }
}
