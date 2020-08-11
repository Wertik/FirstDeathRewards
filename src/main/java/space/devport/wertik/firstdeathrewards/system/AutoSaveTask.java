package space.devport.wertik.firstdeathrewards.system;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;

@RequiredArgsConstructor
public class AutoSaveTask implements Runnable {

    private final FirstDeathPlugin plugin;

    // in ticks
    private int interval;

    private boolean running = false;

    private BukkitTask task;

    public void load() {
        this.interval = plugin.getConfig().getInt("auto-save.interval", 300) * 20;
    }

    public void start() {
        if (running) stop();

        running = true;
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, interval, interval);
    }

    public void stop() {
        if (!running) return;

        running = false;
        task.cancel();
        task = null;
    }

    @Override
    public void run() {
        plugin.getDataManager().save();
    }
}
