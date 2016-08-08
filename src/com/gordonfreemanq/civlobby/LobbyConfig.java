package com.gordonfreemanq.civlobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class LobbyConfig {
	
	public static int CONFIG_VERSION = 1;
	
	private final FileConfiguration fc;
	
	/**
	 * Creates a new SabreConfig instance
	 */
	public LobbyConfig(FileConfiguration fc) {
		this.fc = fc;
	}
	
	
	private Location spawnLocation;
	
	
	/**
	 * Factory method for creating a new config class
	 * @param fc The file configuration instance
	 * @return The new configuration class instance
	 */
	public static LobbyConfig load(FileConfiguration fc) {
		LobbyConfig config = new LobbyConfig(fc);
		config.read();
		config.save();
		return config;
	}
	
	
	/**
	 * Reads the config values into memory
	 */
	@SuppressWarnings("unused")
	public void read() {		
		int version = fc.getInt("general.version");
		
		String spawnWorld = fc.getString("spawn.world", "world");
		double spawnX = fc.getDouble("spawn.x", 0);
		double spawnY = fc.getDouble("spawn.y", 20);
		double spawnZ = fc.getDouble("spawn.z", 0);
		float spawnYaw = (float)fc.getDouble("spawn.yaw", 0);
		float spawnPitch = (float)fc.getDouble("spawn.pitch", 0);
		World world = Bukkit.getWorld(spawnWorld);
		
		this.spawnLocation = new Location(world, spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);
	}
	
	
	/**
	 * Saves the config value to disk
	 */
	public void save() {
		fc.set("general.config_version", CONFIG_VERSION);
		fc.set("spawn.world", this.spawnLocation.getWorld().getName());
		fc.set("spawn.x", this.spawnLocation.getX());
		fc.set("spawn.y", this.spawnLocation.getY());
		fc.set("spawn.z", this.spawnLocation.getZ());
		fc.set("spawn.yaw", this.spawnLocation.getYaw());
		fc.set("spawn.pitch", this.spawnLocation.getPitch());
	}
	
	
	/**
	 * Gets the spawn location
	 * @return The spawn location
	 */
	public Location getSpawnLocation() {
		return this.spawnLocation;
	}
	
	/**
	 * Sets the spawn location
	 * @param spawnLocation The spawn location
	 */
	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
		CivLobby.getPlugin().saveConfig();
	}
}
