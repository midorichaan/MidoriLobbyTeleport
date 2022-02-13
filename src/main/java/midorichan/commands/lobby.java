package midorichan.commands;

import midorichan.LobbyTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lobby implements CommandExecutor {

    private LobbyTeleport plugin = LobbyTeleport.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lobby")) {
            if (sender.hasPermission("midorilobby.use")) {
                World w = Bukkit.getWorld(plugin.config.getString("worldname", "world"));
                if (w == null) {
                    sender.sendMessage(plugin.prefix + "ワールドが設定されていないか存在しません");
                    return true;
                }

                double x = plugin.config.getDouble("pos-x", 0);
                double y = plugin.config.getDouble("pos-y", 0);
                double z = plugin.config.getDouble("pos-z", 0);
                float yaw = plugin.config.getInt("pos-facing", 0);
                float pitch = plugin.config.getInt("pos-rotation", 0);

                if (args.length == 0) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(plugin.prefix + "コンソールからは使用できません");
                        return true;
                    }

                    Player p = (Player) sender;
                    Location loc = new Location(w, x, y, z, yaw, pitch);
                    p.teleport(loc);
                    p.sendMessage(plugin.prefix + "Lobbyにテレポートしました");
                } else if (args.length == 1) {
                    if (sender.hasPermission("midorilobby.use.admin")) {
                        String tar = args[0];
                        Player p = Bukkit.getPlayer(tar);
                        if (p == null) {
                            sender.sendMessage(plugin.prefix + "プレイヤー " + tar + " は存在しません");
                            return true;
                        }

                        Location loc = new Location(w, x, y, z, yaw, pitch);
                        p.teleport(loc);
                        sender.sendMessage(plugin.prefix + tar + "をLobbyにテレポートしました");
                    } else {
                        sender.sendMessage(plugin.prefix + "権限がありません");
                    }
                } else {
                    sender.sendMessage(plugin.prefix + "引数が不正です");
                }
                return true;
            } else {
                sender.sendMessage(plugin.prefix + "権限がありません");
                return true;
            }
        }
        return true;
    }

}
