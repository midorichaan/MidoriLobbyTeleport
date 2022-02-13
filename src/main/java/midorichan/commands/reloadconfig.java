package midorichan.commands;

import midorichan.LobbyTeleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reloadconfig implements CommandExecutor {

    private LobbyTeleport plugin = LobbyTeleport.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mrl")) {
            if (sender.hasPermission("midorilobby.reload")) {
                plugin.reloadConfig();
                plugin.config = plugin.getConfig();
                sender.sendMessage(plugin.prefix + "Configを再読み込みしました");
            } else {
                sender.sendMessage(plugin.prefix + "権限がありません");
            }
        }
        return true;
    }

}
