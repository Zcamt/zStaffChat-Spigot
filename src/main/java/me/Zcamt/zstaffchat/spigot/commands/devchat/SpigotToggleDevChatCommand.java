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
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotDirectChatManager;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotDisabledChatsManager;
import org.bukkit.entity.Player;

@CommandAlias("devchattoggle|toggledevchat|toggledc|dctoggle")

public class SpigotToggleDevChatCommand extends BaseCommand {

    private final SpigotChatManager spigotChatManager;
    private final SpigotDisabledChatsManager disabledChatsManager;
    private final SpigotDirectChatManager directChatManager;
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;
    private final String permission = Permissions.DEV_CHAT.getPermission();

    public SpigotToggleDevChatCommand(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
        this.disabledChatsManager = spigotChatManager.getSpigotDisabledChatsManager();
        this.directChatManager = spigotChatManager.getDirectChatManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!SpigotPermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            SpigotUtilities.sendMessage(player, SpigotConfigManager.devchatPrefix + "&r &7Usage: /dctoggle");
            return;
        }
        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            SpigotUtilities.sendMessage(player, SpigotConfigManager.devchatPrefix + "&r &cError: Cannot toggle a chat you've disabled");
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
