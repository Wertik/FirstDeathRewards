package space.devport.wertik.firstdeathrewards.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import space.devport.utils.commands.SubCommand;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.commands.struct.Preconditions;
import space.devport.wertik.firstdeathrewards.FirstDeathPlugin;

public class ReloadSubCommand extends SubCommand {

    public ReloadSubCommand() {
        super("reload");
        this.preconditions = new Preconditions()
                .permissions("firstdeathrewards.reload");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {
        FirstDeathPlugin.getInstance().reload(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @NotNull String getDefaultUsage() {
        return "/%label% reload";
    }

    @Override
    public @NotNull String getDefaultDescription() {
        return "Reloads the configuration.";
    }

    @Override
    public @NotNull ArgumentRange getRange() {
        return new ArgumentRange(0);
    }
}