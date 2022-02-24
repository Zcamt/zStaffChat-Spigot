package me.Zcamt.zstaffchat.spigot;

import co.aikar.commands.PaperCommandManager;
import me.Zcamt.zstaffchat.spigot.commands.adminchat.SpigotAdminChatCommand;
import me.Zcamt.zstaffchat.spigot.commands.adminchat.SpigotAdminChatOffCommand;
import me.Zcamt.zstaffchat.spigot.commands.adminchat.SpigotAdminChatOnCommand;
import me.Zcamt.zstaffchat.spigot.commands.adminchat.SpigotToggleAdminChatCommand;
import me.Zcamt.zstaffchat.spigot.commands.devchat.SpigotDevChatCommand;
import me.Zcamt.zstaffchat.spigot.commands.devchat.SpigotDevChatOffCommand;
import me.Zcamt.zstaffchat.spigot.commands.devchat.SpigotDevChatOnCommand;
import me.Zcamt.zstaffchat.spigot.commands.devchat.SpigotToggleDevChatCommand;
import me.Zcamt.zstaffchat.spigot.commands.misc.SpigotCoreCommand;
import me.Zcamt.zstaffchat.spigot.commands.staffchat.SpigotStaffChatCommand;
import me.Zcamt.zstaffchat.spigot.commands.staffchat.SpigotStaffChatOffCommand;
import me.Zcamt.zstaffchat.spigot.commands.staffchat.SpigotStaffChatOnCommand;
import me.Zcamt.zstaffchat.spigot.commands.staffchat.SpigotToggleStaffChatCommand;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotChatManager;
import me.Zcamt.zstaffchat.spigot.helpers.managers.SpigotConfigManager;
import me.Zcamt.zstaffchat.spigot.listeners.SpigotChatListener;
import me.Zcamt.zstaffchat.spigot.listeners.SpigotLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/*
Ideas:
Add bstats
https://bstats.org/plugin/bukkit/zStaffChat%20Spigot/13078
 */

public class SpigotZStaffChat extends JavaPlugin {

    private static JavaPlugin instance;
    private final SpigotChatManager spigotChatManager = new SpigotChatManager();

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
        commandManager.registerCommand(new SpigotCoreCommand(this));

        if(SpigotConfigManager.staffchatEnabled) {
            commandManager.registerCommand(new SpigotStaffChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotToggleStaffChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotStaffChatOnCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotStaffChatOffCommand(spigotChatManager));
        }

        if(SpigotConfigManager.adminchatEnabled) {
            commandManager.registerCommand(new SpigotAdminChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotToggleAdminChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotAdminChatOnCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotAdminChatOffCommand(spigotChatManager));
        }

        if(SpigotConfigManager.devchatEnabled) {
            commandManager.registerCommand(new SpigotDevChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotToggleDevChatCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotDevChatOnCommand(spigotChatManager));
            commandManager.registerCommand(new SpigotDevChatOffCommand(spigotChatManager));
        }

        getServer().getPluginManager().registerEvents(new SpigotChatListener(spigotChatManager), this);
        getServer().getPluginManager().registerEvents(new SpigotLeaveListener(spigotChatManager), this);
    }

    public void loadConfig() throws IOException {
        SpigotConfigManager.createConfigFile(this);
        SpigotConfigManager.reload();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
