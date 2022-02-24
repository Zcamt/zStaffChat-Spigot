package me.Zcamt.zstaffchat.spigot.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotUtilities {

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
