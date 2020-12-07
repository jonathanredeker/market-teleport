package co.redeker.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable; 

import co.redeker.Market;

public class Enter {
    
    public static void execute(Market plugin, Player player, CommandSender sender) {
        String uuid = player.getUniqueId().toString();
        Location location = player.getLocation();
        Boolean isVisitingMarket = plugin.getConfig().getBoolean(uuid + ".isVisitingMarket");
        Boolean teleportDelay = plugin.getConfig().getBoolean(uuid + ".teleportDelay");
        
        if (!isVisitingMarket) {
            
            if (!teleportDelay) {
                plugin.getConfig().set(uuid + ".teleportDelay", true);

                new BukkitRunnable() {

                    int seconds = plugin.getConfig().getInt("marketTeleportDelay");

                    @Override
                    public void run() {

                        if (this.seconds == 0) {

                            sender.sendMessage(ChatColor.AQUA + "Teleporting you to the market.");
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.world", location.getWorld().getName());
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.x", location.getX());
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.y", location.getY());
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.z", location.getZ());
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.yaw", location.getYaw());
                            plugin.getConfig().set(uuid + ".locationBeforeMarket.pitch", location.getPitch());
                            plugin.getConfig().set(uuid + ".isVisitingMarket", true);
                            plugin.getConfig().set(uuid + ".teleportDelay", false);
                            
                            String worldName = plugin.getConfig().getString("marketSpawn.world");
                            double x = plugin.getConfig().getDouble("marketSpawn.x");
                            double y = plugin.getConfig().getDouble("marketSpawn.y");
                            double z = plugin.getConfig().getDouble("marketSpawn.z");
                            float yaw = (float)plugin.getConfig().getDouble("marketSpawn.yaw");
                            float pitch = (float)plugin.getConfig().getDouble("marketSpawn.pitch");
                            
                            World world = Bukkit.getServer().getWorld(worldName);
                            Location newLocation = new Location(world, x, y, z, yaw, pitch);
                            player.teleport(newLocation);

                            world.spawnParticle(Particle.REDSTONE, player.getLocation(), 300, 1.0, 1.0, 1.0, 2, new Particle.DustOptions(Color.fromRGB(0, 255, 255), 0.5f));
                            
                            this.cancel();

                        } else {

                            sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Teleporting you in " + Integer.toString(this.seconds--) + ".");
                        }

                    }

                }.runTaskTimer(plugin, 0, 20);

            } else {

                sender.sendMessage(ChatColor.ITALIC + "Please wait...");

            }

            
        } else {

            sender.sendMessage("You're already visiting the market!");

        }
    }

}
