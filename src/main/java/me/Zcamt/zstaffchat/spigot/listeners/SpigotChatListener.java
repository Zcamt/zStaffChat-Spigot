package me.Zcamt.zstaffchat.spigot.listeners;

import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SpigotChatListener implements Listener {

    private final SpigotChatManager spigotChatManager;

    public SpigotChatListener(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(spigotChatManager.getDirectChatManager().contains(player.getUniqueId())){
            spigotChatManager.sendMessageToChat(player, event.getMessage(), spigotChatManager.getDirectChatManager().get(player.getUniqueId()));
            event.setCancelled(true);
        }
    }
}
