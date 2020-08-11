package space.devport.wertik.firstdeathrewards;

import lombok.Getter;
import space.devport.utils.DevportPlugin;
import space.devport.wertik.firstdeathrewards.commands.FirstDeathCommand;
import space.devport.wertik.firstdeathrewards.commands.subcommands.InfoSubCommand;
import space.devport.wertik.firstdeathrewards.commands.subcommands.ReloadSubCommand;
import space.devport.wertik.firstdeathrewards.commands.subcommands.ResetSubCommand;
import space.devport.wertik.firstdeathrewards.listeners.PlayerListener;
import space.devport.wertik.firstdeathrewards.system.DataManager;

public class FirstDeathPlugin extends DevportPlugin {

    @Getter
    private static FirstDeathPlugin instance;

    @Getter
    private DataManager dataManager;

    @Override
    public void onPluginEnable() {
        instance = this;

        consoleOutput.setColors(true);

        new FirstDeathLanguage();

        dataManager = new DataManager();
        dataManager.loadData();

        dataManager.load();

        dataManager.createSaveTask();
        if (getConfig().getBoolean("auto-save.enabled", false))
            dataManager.getAutoSave().start();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        addMainCommand(new FirstDeathCommand()
                .addSubCommand(new InfoSubCommand())
                .addSubCommand(new ResetSubCommand())
                .addSubCommand(new ReloadSubCommand()));
    }

    @Override
    public void onPluginDisable() {
        dataManager.save();
    }

    @Override
    public void onReload() {
        dataManager.loadData();
        dataManager.reloadAutoSave();
    }

    @Override
    public boolean useLanguage() {
        return true;
    }

    @Override
    public boolean useHolograms() {
        return false;
    }

    @Override
    public boolean useMenus() {
        return false;
    }
}
