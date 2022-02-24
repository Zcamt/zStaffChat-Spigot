package me.Zcamt.zstaffchat.bungee.listeners;

import me.Zcamt.zstaffchat.ChatTypes;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeChatManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeLeaveListener implements Listener {

    private final BungeeChatManager bungeeChatManager;

    public BungeeLeaveListener(BungeeChatManager bungeeChatManager) {
        this.bungeeChatManager = bungeeChatManager;
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent event){
        ProxiedPlayer player = event.getPlayer();
        if (bungeeChatManager.getDirectChatManager().contains(player.getUniqueId())){
            bungeeChatManager.getDirectChatManager().remove(player.getUniqueId());
        }
        for(ChatTypes chatType : ChatTypes.values()){
            if(bungeeChatManager.getDisabledChatsManager().contains(player.getUniqueId(), chatType)){
                bungeeChatManager.getDisabledChatsManager().remove(player.getUniqueId(), chatType);
            }
        }

    }

}
