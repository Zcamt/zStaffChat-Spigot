package me.Zcamt.zstaffchat.bungee.listeners;

import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeChatManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeChatListener implements Listener {

    private final BungeeChatManager bungeeChatManager;

    public BungeeChatListener(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
    }

    @EventHandler
    public void onChat(ChatEvent event){
        if(!(event.getSender() instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        if(bungeeChatManager.getDirectChatManager().contains(player.getUniqueId())){
            if(event.isCommand()) return;
            bungeeChatManager.sendMessageToChat(player, event.getMessage(), bungeeChatManager.getDirectChatManager().get(player.getUniqueId()));
            event.setCancelled(true);
        }
    }
}
