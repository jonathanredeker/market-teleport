package co.redeker;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;

import co.redeker.command.MarketCommandExecutor;
import co.redeker.event.EventListener;

public class Market extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("market").setExecutor(new MarketCommandExecutor(this));
        getCommand("marketspawn").setExecutor(new MarketCommandExecutor(this));
        getCommand("marketdelay").setExecutor(new MarketCommandExecutor(this));

        /*
         * Setup default market spawn location and 
         */
        if (getConfig().get("marketSpawn") == null) {
            World overworld = null;
            for (World world : getServer().getWorlds()) {
                if (world.getEnvironment() == Environment.NORMAL) {
                    overworld = world;
                    break;
                }
            }
            Location overworldSpawn = overworld.getSpawnLocation();

            getConfig().set("marketTeleportDelay", 3); // 3 seconds
            getConfig().set("marketSpawn.world", overworld.getName());
            getConfig().set("marketSpawn.x", overworldSpawn.getX());
            getConfig().set("marketSpawn.y", overworldSpawn.getY());
            getConfig().set("marketSpawn.z", overworldSpawn.getZ());
            getConfig().set("marketSpawn.yaw", overworldSpawn.getYaw());
            getConfig().set("marketSpawn.pitch", overworldSpawn.getPitch());
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

}