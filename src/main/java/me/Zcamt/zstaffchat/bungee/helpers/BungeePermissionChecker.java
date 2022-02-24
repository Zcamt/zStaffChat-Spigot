package me.Zcamt.zstaffchat.bungee.helpers;

import me.Zcamt.zstaffchat.Permissions;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public class BungeePermissionChecker {

    public static boolean hasPermission(ProxiedPlayer player, String permission){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;
        return player.hasPermission(permission);
    }

    public static boolean hasPermission(ProxiedPlayer player, List<String> permissions){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        for(String string : permissions){
            if(player.hasPermission(string)){
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermissionWithMessage(ProxiedPlayer player, String commandPermission, String deniedMessage){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        if(player.hasPermission(commandPermission)){
            return true;
        }

        BungeeUtilities.sendMessage(player, (deniedMessage == null
                ? "&cSorry! But you do not have permission to perform that command." : deniedMessage));
        return false;
    }

    public static boolean hasPermissionWithMessage(ProxiedPlayer player, List<String> commandPermissions, String deniedMessage){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        for(String string : commandPermissions){
            if(player.hasPermission(string)){
                return true;
            }
        }

        BungeeUtilities.sendMessage(player, (deniedMessage == null
                ? "&cSorry! But you do not have permission to perform that command." : deniedMessage));
        return false;
    }

}
