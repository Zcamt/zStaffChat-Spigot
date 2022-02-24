package me.Zcamt.zstaffchat.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
