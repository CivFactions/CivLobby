package com.gordonfreemanq.civlobby;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PlayerListener implements Listener {

	private final CivLobby plugin;
	private final RegionManager regionManager;
	private final ProtectedRegion spawnRegion;

	public PlayerListener(CivLobby plugin) {
		this.plugin = plugin;
		regionManager = WGBukkit.getRegionManager(plugin.getLobbyConfig().getSpawnLocation().getWorld());
		spawnRegion = regionManager.getRegion("spawn");
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		// Force survival
		if (!e.getPlayer().hasPermission("lobby.creative")) {
			p.setGameMode(GameMode.SURVIVAL);
			p.setFlying(false);
		}

		e.setJoinMessage(null);
		
		Location spawnLocation = plugin.getLobbyConfig().getSpawnLocation();
		p.teleport(spawnLocation);
		plugin.givePlayerEffect(p);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerKicked(PlayerKickEvent e) {
		e.setLeaveMessage(null);
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
	    if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	    	Player victim = (Player)e.getEntity();
	    	Player hitter = (Player)e.getDamager();
	    	hitter.hidePlayer(victim);
	    	
	    	String msg = CivLobby.getPlugin().txt.parse(Lang.playerPopped, victim.getName());
	    	hitter.sendMessage(msg);
	    	hitter.giveExp(10);
	    }
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		Location l = p.getLocation();
		
		// Only apply to survival
		if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
			return;
		}

		// Falling below bedrock
		if (l.getBlockY() < -2) {
			plugin.teleportToSpawn(p);
			return;
		}
		
		// TP the player back to spawn if they left the region
		if (spawnRegion != null) {
			if (!spawnRegion.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
				plugin.teleportToSpawn(p);
			}
		}
	}
}
