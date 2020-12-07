package co.redeker.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import co.redeker.Market;

public class Set {
    
    public static void execute(Market plugin, Location location, CommandSender sender) {

        String world = location.getWorld().getName();

        plugin.getConfig().set("marketSpawn.world", world);
        plugin.getConfig().set("marketSpawn.x", location.getX());
        plugin.getConfig().set("marketSpawn.y", location.getY());
        plugin.getConfig().set("marketSpawn.z", location.getZ());
        plugin.getConfig().set("marketSpawn.yaw", location.getYaw());
        plugin.getConfig().set("marketSpawn.pitch", location.getPitch());
        plugin.saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Successfully updated the market spawn location.");

    }

}
