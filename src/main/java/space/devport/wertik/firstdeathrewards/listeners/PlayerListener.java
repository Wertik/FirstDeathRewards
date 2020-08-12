package space.devport.wertik.firstdeathrewards.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final FirstDeathPlugin plugin;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (plugin.getConfig().getStringList("disabled-worlds").contains(player.getWorld().getName()) ||
                plugin.getDataManager().hasFirstDeath(player))
            return;

        plugin.getDataManager().setFirstDeath(player);

        event.setKeepInventory(plugin.getConfig().getBoolean("keep-inventory", false));
        event.setKeepLevel(plugin.getConfig().getBoolean("keep-exp", false));

        if (plugin.getConfig().getBoolean("deny-drops", true)) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        }

        plugin.getDataManager().getReward().give(player);
    }
}