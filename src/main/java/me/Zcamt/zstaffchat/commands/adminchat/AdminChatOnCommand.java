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

@CommandAlias("adminchaton|acon")
public class AdminChatOnCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final ChatTypes chatType = ChatTypes.ADMIN_CHAT;
    private final String permission = Permissions.ADMIN_CHAT.getPermission();

    public AdminChatOnCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            Utils.sendMessage(player, ConfigManager.adminchatPrefix + "&r &7Usage: /acon");
            return;
        }
        disabledChatsManager.enablePlayer(player, chatType);
    }
}
