package space.devport.wertik.firstdeathrewards.commands.subcommands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.devport.utils.commands.SubCommand;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.commands.struct.Preconditions;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;
import space.devport.wertik.firstdeathrewards.commands.CommandUtils;

public class InfoSubCommand extends SubCommand {

    public InfoSubCommand() {
        super("info");
        this.preconditions = new Preconditions()
                .permissions("firstdeathrewards.info");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {

        boolean me = false;

        OfflinePlayer target;
        if (args.length > 0) {
            target = CommandUtils.getOfflineTarget(sender, args[0]);

            if (target == null) return CommandResult.FAILURE;
        } else {
            if (!(sender instanceof Player)) return CommandResult.NO_CONSOLE;

            target = (Player) sender;
            me = true;
        }

        boolean dead = FirstDeathPlugin.getInstance().getDataManager().hasFirstDeath(target);
        language.get("Commands.Info.Message")
                .replace("%player%", me ? language.get("Commands.Info.You").color().toString() : target.getName())
                .replace("%available%", dead ? language.get("Commands.Info.Not-Available").color().toString() : language.get("Commands.Info.Available").color().toString())
                .send(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @NotNull String getDefaultUsage() {
        return "/%label% info (player)";
    }

    @Override
    public @NotNull String getDefaultDescription() {
        return "Get info about you or target.";
    }

    @Override
    public @NotNull ArgumentRange getRange() {
        return new ArgumentRange(0, 1);
    }
}