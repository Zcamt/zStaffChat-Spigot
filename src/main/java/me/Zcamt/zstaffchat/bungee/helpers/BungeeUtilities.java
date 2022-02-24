package me.Zcamt.zstaffchat.bungee.helpers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeUtilities {

    public static void sendMessage(ProxiedPlayer player, String string){
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', string)));
    }

}
