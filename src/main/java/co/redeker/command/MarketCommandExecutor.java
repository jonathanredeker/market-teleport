package co.redeker.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.redeker.Market;
import co.redeker.command.commands.Delay;
import co.redeker.command.commands.Enter;
import co.redeker.command.commands.Exit;
import co.redeker.command.commands.Set;

public class MarketCommandExecutor implements CommandExecutor {
    
    private Market plugin;

    public MarketCommandExecutor(Market plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        switch (label) {
            case "market":
            case "mk":
                if (args.length > 0) {

                    switch (args[0]) {
                        case "enter":
                            Enter.execute(plugin, player, sender);
                            break;
                        case "exit":
                        case "leave":
                            Exit.execute(plugin, player, sender);
                            break;
                    }
                    return true;

                } else {

                    Boolean isVisitingMarket = plugin.getConfig().getBoolean(uuid + ".isVisitingMarket");

                    if (isVisitingMarket) {
                        Exit.execute(plugin, player, sender);
                    } else {
                        Enter.execute(plugin, player, sender);
                    }
                    return true;      

                }
            case "marketspawn":
            case "mkspawn":
                Set.execute(plugin, player.getLocation(), sender);
                return true;
            case "marketdelay":
            case "mkdelay":
                Delay.execute(plugin, sender, args);
                return true;

        }

        return false;

    }

}
