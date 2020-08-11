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

public class ResetSubCommand extends SubCommand {

    public ResetSubCommand() {
        super("reset");
        this.preconditions = new Preconditions()
                .permissions("firstdeathrewards.reset");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {

        OfflinePlayer target = null;
        if (args.length > 0) {
            if (!args[0].equalsIgnoreCase("all")) {
                target = CommandUtils.getOfflineTarget(sender, args[0]);

                if (target == null) return CommandResult.FAILURE;
            }
        } else {
            if (!(sender instanceof Player)) return CommandResult.NO_CONSOLE;

            target = (Player) sender;
        }

        FirstDeathPlugin.getInstance().getDataManager().reset(target);
        language.getPrefixed(target == null ? "Commands.Reset.Done-All" : "Commands.Reset.Done")
                .replace("%player%", target == null ? "all" : target.getName())
                .send(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @NotNull String getDefaultUsage() {
        return "/%label% reset (player)";
    }

    @Override
    public @NotNull String getDefaultDescription() {
        return "Reset all or players first death.";
    }

    @Override
    public @NotNull ArgumentRange getRange() {
        return new ArgumentRange(0, 1);
    }
}