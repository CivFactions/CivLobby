package com.gordonfreemanq.civlobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Class to generate effects for the players
 * 
 * @author GFQ
 *
 */
public class PlayerWorker implements Runnable {
	
	
	private final CivLobby plugin;
	
	/**
	 * Creates a new PlayerEffects instance
	 * @param plugin The plugin instance
	 */
	public PlayerWorker(CivLobby plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Starts the worker
	 */
	public void start() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, 200);
	}
	

	@Override
	public void run() {
		
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			plugin.givePlayerEffect(p);
		}
	}
}
