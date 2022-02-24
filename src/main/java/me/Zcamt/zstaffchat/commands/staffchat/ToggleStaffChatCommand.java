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
import me.Zcamt.zstaffchat.managers.DirectChatManager;
import me.Zcamt.zstaffchat.managers.DisabledChatsManager;
import org.bukkit.entity.Player;

@CommandAlias("staffchattoggle|togglestaffchat|togglesc|sctoggle")
public class ToggleStaffChatCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final DirectChatManager directChatManager;
    private final ChatTypes chatType = ChatTypes.STAFF_CHAT;
    private final String permission = Permissions.STAFF_CHAT.getPermission();

    public ToggleStaffChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
        this.directChatManager = chatManager.getDirectChatManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            Utils.sendMessage(player, ConfigManager.staffchatPrefix + "&r &7Usage: /sctoggle");
            return;
        }
        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player, ConfigManager.staffchatPrefix + "&r &cError: Cannot toggle a chat you've disabled");
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
