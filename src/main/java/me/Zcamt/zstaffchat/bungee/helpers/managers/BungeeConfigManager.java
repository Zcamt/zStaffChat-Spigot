package me.Zcamt.zstaffchat.bungee.helpers.managers;


import com.google.common.io.ByteStreams;
import me.Zcamt.zstaffchat.bungee.BungeeZStaffChat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class BungeeConfigManager {

    private static File configFile = new File(BungeeZStaffChat.getInstance().getDataFolder(), "config.yml");
    public static Configuration config;


    public static boolean staffchatEnabled, adminchatEnabled, devchatEnabled;
    public static String staffchatPrefix,adminchatPrefix,devchatPrefix, chatFormat;


    public static void reload() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            throw new RuntimeException("Unable to able to load config!", e);
        }

        chatFormat = config.getString("chat-format");

        staffchatEnabled = config.getBoolean("staffchat.enabled");
        adminchatEnabled = config.getBoolean("adminchat.enabled");
        devchatEnabled = config.getBoolean("devchat.enabled");

        staffchatPrefix = translateCC(config.getString("staffchat.staffchat-prefix"));
        adminchatPrefix = translateCC(config.getString("adminchat.adminchat-prefix"));
        devchatPrefix = translateCC(config.getString("devchat.devchat-prefix"));
    }

    public static void createConfigFile(Plugin plugin) throws IOException {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()){
            configFile.createNewFile();
            try (InputStream is = plugin.getResourceAsStream("config.yml");
                 OutputStream os = new FileOutputStream(configFile)) {
                ByteStreams.copy(is, os);
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }
    }

    public static String translateCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
