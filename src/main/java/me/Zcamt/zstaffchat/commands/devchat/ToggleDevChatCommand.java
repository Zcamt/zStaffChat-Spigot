package me.Zcamt.zstaffchat.commands.devchat;

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

@CommandAlias("devchattoggle|toggledevchat|toggledc|dctoggle")

public class ToggleDevChatCommand extends BaseCommand {

    private final ChatManager chatManager;
    private final DisabledChatsManager disabledChatsManager;
    private final DirectChatManager directChatManager;
    private final ChatTypes chatType = ChatTypes.DEV_CHAT;
    private final String permission = Permissions.DEV_CHAT.getPermission();

    public ToggleDevChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.disabledChatsManager = chatManager.getSpigotDisabledChatsManager();
        this.directChatManager = chatManager.getDirectChatManager();
    }

    @Default @CatchUnknown
    public void onCommand(Player player, String[] args){

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        if(args.length != 0){
            Utils.sendMessage(player, ConfigManager.devchatPrefix + "&r &7Usage: /dctoggle");
            return;
        }
        if(disabledChatsManager.contains(player.getUniqueId(), chatType)){
            Utils.sendMessage(player, ConfigManager.devchatPrefix + "&r &cError: Cannot toggle a chat you've disabled");
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
