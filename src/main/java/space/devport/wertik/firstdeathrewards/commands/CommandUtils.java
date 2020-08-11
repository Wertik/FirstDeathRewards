package space.devport.wertik.firstdeathrewards.commands;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;

@UtilityClass
public class CommandUtils {

    public OfflinePlayer getOfflineTarget(CommandSender sender, String name) {
        OfflinePlayer offlinePlayer = Bukkit.getPlayer(name);

        if (offlinePlayer == null) {
            FirstDeathPlugin.getInstance().getLanguageManager().getPrefixed("Commands.Invalid-Player")
                    .replace("%param%", name)
                    .send(sender);
            return null;
        }
        return offlinePlayer;
    }
}