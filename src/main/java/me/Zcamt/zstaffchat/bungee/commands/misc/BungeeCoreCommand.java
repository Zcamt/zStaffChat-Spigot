package me.Zcamt.zstaffchat.bungee.commands.misc;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.bungee.helpers.BungeePermissionChecker;
import me.Zcamt.zstaffchat.bungee.helpers.BungeeUtilities;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeConfigManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

@CommandAlias("zstaffchat|zsc")
public class BungeeCoreCommand extends BaseCommand {

    //private final BungeeChatManager bungeeChatManager;
    private final Plugin plugin;

    public BungeeCoreCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Default @CatchUnknown @Subcommand("help")
    public void onHelp(ProxiedPlayer player, String[] args){
        BungeeUtilities.sendMessage(player, "&6Running &7" + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion());
        BungeeUtilities.sendMessage(player, "&a/zstaffchat help &7- Shows this message");
        if(BungeePermissionChecker.hasPermission(player, Permissions.ADMIN.getPermission())) {
            BungeeUtilities.sendMessage(player, "&a/zstaffchat reload &7- Used to reload the config");
        }
        if(BungeePermissionChecker.hasPermission(player, Permissions.STAFF_CHAT.getPermission())) {
            BungeeUtilities.sendMessage(player, "&a/staffchat [message] &7- Send a message to everyone with access to the staffchat");
            BungeeUtilities.sendMessage(player, "&a/sctoggle  &7- Send messages directly to the staffchat without having to use the command");
            BungeeUtilities.sendMessage(player, "&a/scon  &7- Receive messages from the staffchat, enabled by default on login");
            BungeeUtilities.sendMessage(player, "&a/scoff  &7- Stop receiving message from the staffchat until you re-enable or log off");
        }
        if(BungeePermissionChecker.hasPermission(player, Permissions.ADMIN_CHAT.getPermission())) {
            BungeeUtilities.sendMessage(player, "&a/adminchat [message] &7- Send a message to everyone with access to the adminchat");
            BungeeUtilities.sendMessage(player, "&a/actoggle  &7- Send messages directly to the adminchat without having to use the command");
            BungeeUtilities.sendMessage(player, "&a/acon  &7- Receive messages from the adminchat, enabled by default on login");
            BungeeUtilities.sendMessage(player, "&a/acoff  &7- Stop receiving message from the adminchat until you re-enable or log off");
        }
        if(BungeePermissionChecker.hasPermission(player, Permissions.DEV_CHAT.getPermission())) {
            BungeeUtilities.sendMessage(player, "&a/devchat [message] &7- Send a message to everyone with access to the devchat");
            BungeeUtilities.sendMessage(player, "&a/dctoggle  &7- Send messages directly to the devchat without having to use the command");
            BungeeUtilities.sendMessage(player, "&a/dcon  &7- Receive messages from the devchat, enabled by default on login");
            BungeeUtilities.sendMessage(player, "&a/dcoff  &7- Stop receiving message from the devchat until you re-enable or log off");
        }
    }

    @Subcommand("reload")
    public void onReload(ProxiedPlayer player, String[] args){
        final String permission = Permissions.ADMIN.getPermission();

        if(!BungeePermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        BungeeConfigManager.reload();
        BungeeUtilities.sendMessage(player, "&aConfig reloaded!");

    }
}
