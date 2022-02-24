package me.Zcamt.zstaffchat.listeners;

import me.Zcamt.zstaffchat.managers.ChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final ChatManager chatManager;

    public ChatListener(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(chatManager.getDirectChatManager().contains(player.getUniqueId())){
            chatManager.sendMessageToChat(player, event.getMessage(), chatManager.getDirectChatManager().get(player.getUniqueId()));
            event.setCancelled(true);
        }
    }
}
