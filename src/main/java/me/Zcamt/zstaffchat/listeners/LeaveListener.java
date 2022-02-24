package me.Zcamt.zstaffchat.listeners;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.managers.ChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    private final ChatManager chatManager;

    public LeaveListener(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (chatManager.getDirectChatManager().contains(player.getUniqueId())){
            chatManager.getDirectChatManager().remove(player.getUniqueId());
        }
        for(ChatTypes chatType : ChatTypes.values()){
            if(chatManager.getSpigotDisabledChatsManager().contains(player.getUniqueId(), chatType)){
                chatManager.getSpigotDisabledChatsManager().remove(player.getUniqueId(), chatType);
            }
        }

    }

}
