package space.devport.wertik.firstdeathrewards;

import space.devport.utils.text.language.LanguageDefaults;

public class FirstDeathLanguage extends LanguageDefaults {

    @Override
    public void setDefaults() {
        addDefault("Commands.Invalid-Player", "&cPlayer &f%param% &cdoes not exist.");

        addDefault("Commands.Reset.Done", "&7Reset for &f%player% &7successful.");
        addDefault("Commands.Reset.Done-All", "&7Reset for all players successful.");

        addDefault("Commands.Info.Message", "&8&m    &7 First Death of &f%player%", "&7Available: %available%");
        addDefault("Commands.Info.You", "&6You");
        addDefault("Commands.Info.Available", "&ayes &7(means he has not died yet)");
        addDefault("Commands.Info.Not-Available", "&cno &7(means he's lame and died already)");
    }
}