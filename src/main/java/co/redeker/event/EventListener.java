package co.redeker.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import co.redeker.Market;

public class EventListener implements Listener {

    private Market plugin;

    public EventListener(Market plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Checks to see if the Player exists in the config upon onPlayerJoin event. If they don't, then assign default data.
     * @param e the PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();
        Location location = e.getPlayer().getLocation();
        
        if (plugin.getConfig().get(uuid) == null) {    
            plugin.getConfig().set(uuid + ".locationBeforeMarket.world", location.getWorld().getName());
            plugin.getConfig().set(uuid + ".locationBeforeMarket.x", location.getX());
            plugin.getConfig().set(uuid + ".locationBeforeMarket.y", location.getY());
            plugin.getConfig().set(uuid + ".locationBeforeMarket.z", location.getZ());
            plugin.getConfig().set(uuid + ".isVisitingMarket", false);
            plugin.getConfig().set(uuid + ".teleportDelay", false);
            plugin.saveConfig();
        }
        
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        String uuid = player.getUniqueId().toString();
        plugin.getConfig().set(uuid + ".isVisitingMarket", false);
        plugin.getConfig().set(uuid + ".teleportDelay", false);
    }

}
