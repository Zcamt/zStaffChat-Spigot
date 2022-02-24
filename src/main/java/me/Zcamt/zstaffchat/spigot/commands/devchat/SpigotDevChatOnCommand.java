package me.Zcamt.zstaffchat.spigot.commands.devchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
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

@CommandAlias("devchaton|dcon")
public class SpigotDevChatOnCommand extends BaseCommand {

    private final SpigotChatManager spigotChatManager;
    private final SpigotDisabledChatsManager disabledChatsManager;
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;
    private final String permission = Permissions.DEV_CHAT.getPermission();

    public SpigotDevChatOnCommand(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
        this.disabledChatsManager = spigotChatManager.getSpigotDisabledChatsManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!SpigotPermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            SpigotUtilities.sendMessage(player, SpigotConfigManager.devchatPrefix + "&r &7Usage: /dcon");
            return;
        }
        disabledChatsManager.enablePlayer(player, chatType);
    }
}
