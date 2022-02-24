package me.Zcamt.zstaffchat.spigot.commands.misc;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotPermissionChecker;
import me.Zcamt.zstaffchat.spigot.helpers.SpigotUtilities;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@CommandAlias("zstaffchat|zsc")
public class SpigotCoreCommand extends BaseCommand {

    //private final SpigotChatManager spigotChatManager;
    private final JavaPlugin plugin;

    public SpigotCoreCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Default @CatchUnknown @Subcommand("help")
    public void onHelp(Player player, String[] args){
        SpigotUtilities.sendMessage(player, "&6Running &7" + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion());
        SpigotUtilities.sendMessage(player, "&a/zstaffchat help &7- Shows this message");
        if(SpigotPermissionChecker.hasPermission(player, Permissions.ADMIN.getPermission())) {
            SpigotUtilities.sendMessage(player, "&a/zstaffchat reload &7- Used to reload the config");
        }
        if(SpigotPermissionChecker.hasPermission(player, Permissions.STAFF_CHAT.getPermission())) {
            SpigotUtilities.sendMessage(player, "&a/staffchat [message] &7- Send a message to everyone with access to the staffchat");
            SpigotUtilities.sendMessage(player, "&a/sctoggle  &7- Send messages directly to the staffchat without having to use the command");
            SpigotUtilities.sendMessage(player, "&a/scon  &7- Receive messages from the staffchat, enabled by default on login");
            SpigotUtilities.sendMessage(player, "&a/scoff  &7- Stop receiving message from the staffchat until you re-enable or log off");
        }
        if(SpigotPermissionChecker.hasPermission(player, Permissions.ADMIN_CHAT.getPermission())) {
            SpigotUtilities.sendMessage(player, "&a/adminchat [message] &7- Send a message to everyone with access to the adminchat");
            SpigotUtilities.sendMessage(player, "&a/actoggle  &7- Send messages directly to the adminchat without having to use the command");
            SpigotUtilities.sendMessage(player, "&a/acon  &7- Receive messages from the adminchat, enabled by default on login");
            SpigotUtilities.sendMessage(player, "&a/acoff  &7- Stop receiving message from the adminchat until you re-enable or log off");
        }
        if(SpigotPermissionChecker.hasPermission(player, Permissions.DEV_CHAT.getPermission())) {
            SpigotUtilities.sendMessage(player, "&a/devchat [message] &7- Send a message to everyone with access to the devchat");
            SpigotUtilities.sendMessage(player, "&a/dctoggle  &7- Send messages directly to the devchat without having to use the command");
            SpigotUtilities.sendMessage(player, "&a/dcon  &7- Receive messages from the devchat, enabled by default on login");
            SpigotUtilities.sendMessage(player, "&a/dcoff  &7- Stop receiving message from the devchat until you re-enable or log off");
        }
    }

    @Subcommand("reload")
    public void onReload(Player player, String[] args){
        final String permission = Permissions.ADMIN.getPermission();

        if(!SpigotPermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        SpigotConfigManager.reload();
        SpigotUtilities.sendMessage(player, "&aConfig reloaded!");

    }
}
