package me.Zcamt.zstaffchat.bungee;

import co.aikar.commands.BungeeCommandManager;
import me.Zcamt.zstaffchat.bungee.commands.adminchat.BungeeAdminChatCommand;
import me.Zcamt.zstaffchat.bungee.commands.adminchat.BungeeAdminChatOffCommand;
import me.Zcamt.zstaffchat.bungee.commands.adminchat.BungeeAdminChatOnCommand;
import me.Zcamt.zstaffchat.bungee.commands.adminchat.BungeeToggleAdminChatCommand;
import me.Zcamt.zstaffchat.bungee.commands.devchat.BungeeDevChatCommand;
import me.Zcamt.zstaffchat.bungee.commands.devchat.BungeeDevChatOffCommand;
import me.Zcamt.zstaffchat.bungee.commands.devchat.BungeeDevChatOnCommand;
import me.Zcamt.zstaffchat.bungee.commands.devchat.BungeeToggleDevChatCommand;
import me.Zcamt.zstaffchat.bungee.commands.misc.BungeeCoreCommand;
import me.Zcamt.zstaffchat.bungee.commands.staffchat.BungeeStaffChatCommand;
import me.Zcamt.zstaffchat.bungee.commands.staffchat.BungeeStaffChatOffCommand;
import me.Zcamt.zstaffchat.bungee.commands.staffchat.BungeeStaffChatOnCommand;
import me.Zcamt.zstaffchat.bungee.commands.staffchat.BungeeToggleStaffChatCommand;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeChatManager;
import me.Zcamt.zstaffchat.bungee.helpers.managers.BungeeConfigManager;
import me.Zcamt.zstaffchat.bungee.listeners.BungeeChatListener;
import me.Zcamt.zstaffchat.bungee.listeners.BungeeLeaveListener;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

/*
Ideas:
Add bstats
https://bstats.org/plugin/bungeecord/zStaffChat%20Bungee/13079
 */

public class BungeeZStaffChat extends Plugin {

    private static Plugin instance;
    private final BungeeChatManager bungeeChatManager = new BungeeChatManager();

    @Override
    public void onEnable() {
        instance = this;
        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }


        try {
            loadConfig();
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration file", e);
        }
        getLogger().info("zStaffChat");
        getLogger().info("Made by Zcamt");

        BungeeCommandManager commandManager = new BungeeCommandManager(this);
        commandManager.registerCommand(new BungeeCoreCommand(this));

        if(BungeeConfigManager.staffchatEnabled) {
            commandManager.registerCommand(new BungeeStaffChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeToggleStaffChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeStaffChatOnCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeStaffChatOffCommand(bungeeChatManager));
        }

        if(BungeeConfigManager.adminchatEnabled) {
            commandManager.registerCommand(new BungeeAdminChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeToggleAdminChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeAdminChatOnCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeAdminChatOffCommand(bungeeChatManager));
        }

        if(BungeeConfigManager.devchatEnabled) {
            commandManager.registerCommand(new BungeeDevChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeToggleDevChatCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeDevChatOnCommand(bungeeChatManager));
            commandManager.registerCommand(new BungeeDevChatOffCommand(bungeeChatManager));
        }

        getProxy().getPluginManager().registerListener(this, new BungeeChatListener(bungeeChatManager));
        getProxy().getPluginManager().registerListener(this, new BungeeLeaveListener(bungeeChatManager));

    }

    public void loadConfig() throws IOException {
        BungeeConfigManager.createConfigFile(this);
        BungeeConfigManager.reload();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
