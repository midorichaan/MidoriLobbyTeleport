package midorichan;

import midorichan.commands.lobby;
import midorichan.commands.reloadconfig;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class LobbyTeleport extends JavaPlugin {

    public static FileConfiguration config = null;
    public static String prefix = " §2>§a>§r ";
    private static LobbyTeleport instance;

    public static LobbyTeleport getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        config = this.getConfig();

        //register commands
        PluginCommand cmd_lobby = Bukkit.getPluginCommand("lobby");
        cmd_lobby.setExecutor(new lobby());
        try {
            cmd_lobby.setAliases(config.getStringList("command-aliases"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getPluginCommand("mrl").setExecutor(new reloadconfig());
        this.getLogger().info(prefix + "enabled MidoriLobbyTeleport v1.1");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(prefix + "disabled MidoriLobbyTeleport v1.1");
    }

}
