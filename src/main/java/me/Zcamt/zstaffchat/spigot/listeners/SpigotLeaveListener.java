package me.Zcamt.zstaffchat.spigot.listeners;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpigotLeaveListener implements Listener {

    private final SpigotChatManager spigotChatManager;

    public SpigotLeaveListener(SpigotChatManager spigotChatManager) {
        this.spigotChatManager = spigotChatManager;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (spigotChatManager.getDirectChatManager().contains(player.getUniqueId())){
            spigotChatManager.getDirectChatManager().remove(player.getUniqueId());
        }
        for(ChatTypes chatType : ChatTypes.values()){
            if(spigotChatManager.getSpigotDisabledChatsManager().contains(player.getUniqueId(), chatType)){
                spigotChatManager.getSpigotDisabledChatsManager().remove(player.getUniqueId(), chatType);
            }
        }

    }

}
