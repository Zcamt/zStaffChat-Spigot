package me.Zcamt.zstaffchat.managers;

import me.Zcamt.zstaffchat.ZStaffChat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File configFile = new File(ZStaffChat.getInstance().getDataFolder(), "config.yml");
    public static YamlConfiguration config;


    public static boolean staffchatEnabled, adminchatEnabled, devchatEnabled;
    public static String staffchatPrefix,adminchatPrefix,devchatPrefix, chatFormat;


    public static void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);

        chatFormat = config.getString("chat-format");

        staffchatEnabled = config.getBoolean("staffchat.enabled");
        adminchatEnabled = config.getBoolean("adminchat.enabled");
        devchatEnabled = config.getBoolean("devchat.enabled");

        staffchatPrefix = translateCC(config.getString("staffchat.staffchat-prefix"));
        adminchatPrefix = translateCC(config.getString("adminchat.adminchat-prefix"));
        devchatPrefix = translateCC(config.getString("devchat.devchat-prefix"));
    }

    public static void createConfigFile(JavaPlugin plugin) throws IOException {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()){
            plugin.saveResource("config.yml", false);
        }
    }

    public static String translateCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
