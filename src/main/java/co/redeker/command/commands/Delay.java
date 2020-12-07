package co.redeker.command.commands;

import org.bukkit.command.CommandSender;

import co.redeker.Market;
import net.md_5.bungee.api.ChatColor;

public class Delay {
    
    public static void execute(Market plugin, CommandSender sender, String[] args) {
        if (args.length > 0) {
            try {
                int teleportDelay = Integer.parseInt(args[0]);
                if (teleportDelay < 0) {
                    sender.sendMessage(ChatColor.RED + "Invalid input: please use an integer greater than or equal to 0.");
                } else if (teleportDelay > 60) {
                    sender.sendMessage(ChatColor.RED + "Invalid input: please use an integer less than or equal to 60.");
                } else {
                    plugin.getConfig().set("marketTeleportDelay", teleportDelay);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "Successfully updated the market teleport delay time.");
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid input: please input an integer.");
            }
        } else {
            sender.sendMessage("Input a time in seconds or 0 for none.");
            sender.sendMessage("i.e. " + ChatColor.GOLD + "/mkdelay 3" + ChatColor.RESET + " for a 3 second delay.");
            sender.sendMessage("\nThe current teleport delay is " + ChatColor.GOLD + plugin.getConfig().getString("marketTeleportDelay") + ChatColor.RESET + ".");
        }
    }

}
