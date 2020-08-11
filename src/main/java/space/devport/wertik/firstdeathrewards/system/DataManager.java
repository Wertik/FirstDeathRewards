package space.devport.wertik.firstdeathrewards.system;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import space.devport.utils.struct.Rewards;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DataManager {

    private final FirstDeathPlugin plugin;

    private final Set<UUID> cache = new HashSet<>();

    private final Gson gson = new GsonBuilder()
            // .setPrettyPrinting()
            .create();

    @Getter
    private Rewards reward;

    @Getter
    private AutoSaveTask autoSave;

    public DataManager() {
        this.plugin = FirstDeathPlugin.getInstance();
    }

    public void setFirstDeath(OfflinePlayer player) {
        cache.add(player.getUniqueId());
    }

    public void reset(OfflinePlayer... player) {
        if (player.length > 0)
            cache.remove(player[0].getUniqueId());
        else cache.clear();
    }

    public boolean hasFirstDeath(OfflinePlayer player) {
        return cache.contains(player.getUniqueId());
    }

    public Set<UUID> getCache() {
        return Collections.unmodifiableSet(cache);
    }

    public void loadData() {
        reward = plugin.getConfiguration().getRewards("rewards");
    }

    public void createSaveTask() {
        autoSave = new AutoSaveTask(plugin);
        autoSave.load();
    }

    public void reloadAutoSave() {
        autoSave.stop();

        if (plugin.getConfig().getBoolean("auto-save.enabled", false)) {
            autoSave.load();
            autoSave.start();
        }
    }

    public void save() {

        final Set<UUID> finalCache = new HashSet<>(cache);

        plugin.getConsoleOutput().debug("Saving " + cache.size() + " first death records..");

        String output = gson.toJson(finalCache, new TypeToken<Set<UUID>>() {
        }.getType());

        plugin.getConsoleOutput().debug("JSON: " + output);

        Path path = Paths.get(plugin.getDataFolder().getPath() + "/Data.json");

        try {
            Files.write(path, output.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        cache.clear();

        Path path = Paths.get(plugin.getDataFolder().getPath() + "/Data.json");

        if (!Files.exists(path)) return;

        String input;
        try {
            input = String.join("", Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (Strings.isNullOrEmpty(input)) return;

        cache.addAll(gson.fromJson(input, new TypeToken<Set<UUID>>() {
        }.getType()));
    }
}