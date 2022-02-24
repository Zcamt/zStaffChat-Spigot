package me.Zcamt.zstaffchat.utils;

import me.Zcamt.zstaffchat.Permissions;
import org.bukkit.entity.Player;

import java.util.List;

public class PermissionChecker {

    public static boolean hasPermission(Player player, String permission){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;
        return player.hasPermission(permission);
    }

    public static boolean hasPermission(Player player, List<String> permissions){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        for(String string : permissions){
            if(player.hasPermission(string)){
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermissionWithMessage(Player player, String commandPermission, String deniedMessage){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        if(player.hasPermission(commandPermission)){
            return true;
        }

        Utils.sendMessage(player, (deniedMessage == null
                ? "&cSorry! But you do not have permission to perform that command." : deniedMessage));
        return false;
    }

    public static boolean hasPermissionWithMessage(Player player, List<String> commandPermissions, String deniedMessage){
        if(player.hasPermission(Permissions.OVERRIDE.getPermission())) return true;

        for(String string : commandPermissions){
            if(player.hasPermission(string)){
                return true;
            }
        }

        Utils.sendMessage(player, (deniedMessage == null
                ? "&cSorry! But you do not have permission to perform that command." : deniedMessage));
        return false;
    }

}
