package me.Zcamt.zstaffchat.commands.misc;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.Zcamt.zstaffchat.Permissions;
import me.Zcamt.zstaffchat.utils.PermissionChecker;
import me.Zcamt.zstaffchat.utils.Utils;
import me.Zcamt.zstaffchat.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@CommandAlias("zstaffchat|zsc")
public class CoreCommand extends BaseCommand {

    //private final SpigotChatManager spigotChatManager;
    private final JavaPlugin plugin;

    public CoreCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Default @CatchUnknown @Subcommand("help")
    public void onHelp(Player player, String[] args){
        Utils.sendMessage(player, "&6Running &7" + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion());
        Utils.sendMessage(player, "&a/zstaffchat help &7- Shows this message");
        if(PermissionChecker.hasPermission(player, Permissions.ADMIN.getPermission())) {
            Utils.sendMessage(player, "&a/zstaffchat reload &7- Used to reload the config");
        }
        if(PermissionChecker.hasPermission(player, Permissions.STAFF_CHAT.getPermission())) {
            Utils.sendMessage(player, "&a/staffchat [message] &7- Send a message to everyone with access to the staffchat");
            Utils.sendMessage(player, "&a/sctoggle  &7- Send messages directly to the staffchat without having to use the command");
            Utils.sendMessage(player, "&a/scon  &7- Receive messages from the staffchat, enabled by default on login");
            Utils.sendMessage(player, "&a/scoff  &7- Stop receiving message from the staffchat until you re-enable or log off");
        }
        if(PermissionChecker.hasPermission(player, Permissions.ADMIN_CHAT.getPermission())) {
            Utils.sendMessage(player, "&a/adminchat [message] &7- Send a message to everyone with access to the adminchat");
            Utils.sendMessage(player, "&a/actoggle  &7- Send messages directly to the adminchat without having to use the command");
            Utils.sendMessage(player, "&a/acon  &7- Receive messages from the adminchat, enabled by default on login");
            Utils.sendMessage(player, "&a/acoff  &7- Stop receiving message from the adminchat until you re-enable or log off");
        }
        if(PermissionChecker.hasPermission(player, Permissions.DEV_CHAT.getPermission())) {
            Utils.sendMessage(player, "&a/devchat [message] &7- Send a message to everyone with access to the devchat");
            Utils.sendMessage(player, "&a/dctoggle  &7- Send messages directly to the devchat without having to use the command");
            Utils.sendMessage(player, "&a/dcon  &7- Receive messages from the devchat, enabled by default on login");
            Utils.sendMessage(player, "&a/dcoff  &7- Stop receiving message from the devchat until you re-enable or log off");
        }
    }

    @Subcommand("reload")
    public void onReload(Player player, String[] args){
        final String permission = Permissions.ADMIN.getPermission();

        if(!PermissionChecker.hasPermissionWithMessage(player, permission, null)) return;

        ConfigManager.reload();
        Utils.sendMessage(player, "&aConfig reloaded!");

    }
}
