package co.redeker.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.redeker.Market;

public class Exit {
    
    public static void execute(Market plugin, Player player, CommandSender sender) {
        String uuid = player.getUniqueId().toString();
        Location location = player.getLocation();
        Boolean isVisitingMarket = plugin.getConfig().getBoolean(uuid + ".isVisitingMarket");

        if (isVisitingMarket) {
            sender.sendMessage(ChatColor.AQUA + "Teleporting you to your last location.");

            plugin.getConfig().set(uuid + ".isVisitingMarket", false);
            
            String worldName = plugin.getConfig().getString(uuid + ".locationBeforeMarket.world");
            double x = plugin.getConfig().getDouble(uuid + ".locationBeforeMarket.x");
            double y = plugin.getConfig().getDouble(uuid + ".locationBeforeMarket.y");
            double z = plugin.getConfig().getDouble(uuid + ".locationBeforeMarket.z");
            float yaw = (float)plugin.getConfig().getDouble(uuid + ".locationBeforeMarket.yaw");
            float pitch = (float)plugin.getConfig().getDouble(uuid + ".locationBeforeMarket.pitch");
            
            World world = Bukkit.getServer().getWorld(worldName);
            Location newLocation = new Location(world, x, y, z, yaw, pitch);
            player.teleport(newLocation);

            world.spawnParticle(Particle.REDSTONE, player.getLocation(), 300, 1.0, 1.0, 1.0, 2, new Particle.DustOptions(Color.fromRGB(0, 255, 255), 0.5f));
            
        } else {
            sender.sendMessage("You're not at the market!");
        }
    }

}
