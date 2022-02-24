package me.Zcamt.zstaffchat;

import co.aikar.commands.PaperCommandManager;
import me.Zcamt.zstaffchat.commands.adminchat.AdminChatCommand;
import me.Zcamt.zstaffchat.commands.adminchat.AdminChatOffCommand;
import me.Zcamt.zstaffchat.commands.adminchat.AdminChatOnCommand;
import me.Zcamt.zstaffchat.commands.adminchat.ToggleAdminChatCommand;
import me.Zcamt.zstaffchat.commands.devchat.DevChatCommand;
import me.Zcamt.zstaffchat.commands.devchat.DevChatOffCommand;
import me.Zcamt.zstaffchat.commands.devchat.DevChatOnCommand;
import me.Zcamt.zstaffchat.commands.devchat.ToggleDevChatCommand;
import me.Zcamt.zstaffchat.commands.misc.CoreCommand;
import me.Zcamt.zstaffchat.commands.staffchat.StaffChatCommand;
import me.Zcamt.zstaffchat.commands.staffchat.StaffChatOffCommand;
import me.Zcamt.zstaffchat.commands.staffchat.StaffChatOnCommand;
import me.Zcamt.zstaffchat.commands.staffchat.ToggleStaffChatCommand;
import me.Zcamt.zstaffchat.managers.ChatManager;
import me.Zcamt.zstaffchat.managers.ConfigManager;
import me.Zcamt.zstaffchat.listeners.ChatListener;
import me.Zcamt.zstaffchat.listeners.LeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/*
Ideas:
Add bstats
https://bstats.org/plugin/bukkit/zStaffChat%20Spigot/13078
 */

public class ZStaffChat extends JavaPlugin {

    private static JavaPlugin instance;
    private final ChatManager chatManager = new ChatManager();

    @Override
    public void onEnable() {
        instance = this;
        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }

        try {
            loadConfig();
        } catch (IOException e) {
            getServer().getPluginManager().disablePlugin(this);
            throw new RuntimeException("Can't load configuration file, disabling plugin", e);
        }


        getLogger().info("zStaffChat");
        getLogger().info("Made by Zcamt");


        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new CoreCommand(this));

        if(ConfigManager.staffchatEnabled) {
            commandManager.registerCommand(new StaffChatCommand(chatManager));
            commandManager.registerCommand(new ToggleStaffChatCommand(chatManager));
            commandManager.registerCommand(new StaffChatOnCommand(chatManager));
            commandManager.registerCommand(new StaffChatOffCommand(chatManager));
        }

        if(ConfigManager.adminchatEnabled) {
            commandManager.registerCommand(new AdminChatCommand(chatManager));
            commandManager.registerCommand(new ToggleAdminChatCommand(chatManager));
            commandManager.registerCommand(new AdminChatOnCommand(chatManager));
            commandManager.registerCommand(new AdminChatOffCommand(chatManager));
        }

        if(ConfigManager.devchatEnabled) {
            commandManager.registerCommand(new DevChatCommand(chatManager));
            commandManager.registerCommand(new ToggleDevChatCommand(chatManager));
            commandManager.registerCommand(new DevChatOnCommand(chatManager));
            commandManager.registerCommand(new DevChatOffCommand(chatManager));
        }

        getServer().getPluginManager().registerEvents(new ChatListener(chatManager), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(chatManager), this);
    }

    public void loadConfig() throws IOException {
        ConfigManager.createConfigFile(this);
        ConfigManager.reload();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
