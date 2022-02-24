package me.Zcamt.zstaffchat.spigot.commands.staffchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotPermissionChecker;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotUtilities;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotChatManager;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotConfigManager;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotDisabledChatsManager;
import org.bukkit.entity.Player;

@CommandAlias("staffchat")
public class SpigotStaffChatCommand extends BaseCommand {

    private final SpigotChatManager spigotChatManager;
    private final SpigotDisabledChatsManager disabledChatsManager;
    private final String permission = Permissions.STAFF_CHAT.getPermission();
    private final ChatTypes chatType = ChatTypes.STAFF_CHAT;

    public SpigotStaffChatCommand(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
        this.disabledChatsManager = spigotChatManager.getSpigotDisabledChatsManager();
    }

    @Default
    public void onCommand(Player player, String[] args){

        if(!SpigotPermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            SpigotUtilities.sendMessage(player,
                    SpigotConfigManager.staffchatPrefix + "&r &cError: You cannot send messages to a chat you've disabled");
            return;
        }

        if(args.length < 1){
            SpigotUtilities.sendMessage(player, SpigotConfigManager.staffchatPrefix + "&r &7Usage: /staffchat [MESSAGE]");
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        spigotChatManager.sendMessageToChat(player, message.toString(), chatType);
    }


}
