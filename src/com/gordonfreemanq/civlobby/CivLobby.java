package com.gordonfreemanq.civlobby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gordonfreemanq.civlobby.cmd.CmdAutoHelp;
import com.gordonfreemanq.civlobby.cmd.CmdRoot;
import com.gordonfreemanq.civlobby.util.AbstractPlugin;

public class CivLobby extends AbstractPlugin {

	private static CivLobby instance;
	private LobbyConfig lobbyConfig;
	private PlayerListener playerListener;
	private PlayerWorker playerWorker;
	
	private Set<PotionEffect> potionEffects;
	
	private CmdRoot cmdBase;
	private CmdAutoHelp cmdAutoHelp;

	/**
	 * Gets the plugin instance
	 * @return The plugin instance
	 */
	public static CivLobby getPlugin() { 
		return instance;
	}
	
	
	/**
	 * Gets the lobby config
	 * @return The lobby config
	 */
	public LobbyConfig getLobbyConfig() {
		return this.lobbyConfig;
	}
	
	/**
	 * Gets the auto-help command
	 * @return The auto-help command
	 */
	public CmdAutoHelp getCmdAutoHelp() { 
		return cmdAutoHelp;
	}
	
	
	public CivLobby() {
		instance = this;
	}
	
	
	/**
	 * Bukkit plugin disable function
	 */
	@Override
	public void onEnable() {
		// Base plugin
		if (!super.preEnable()) {
			return;
		}
		
		this.lobbyConfig = LobbyConfig.load(this.getConfig());
		super.saveConfig();
		
		playerListener = new PlayerListener(this);
		getServer().getPluginManager().registerEvents(playerListener, this);
		
		playerWorker = new PlayerWorker(this);
		playerWorker.start();
		
		this.cmdBase = new CmdRoot();
		this.cmdAutoHelp = new CmdAutoHelp();
		
		potionEffects = new HashSet<PotionEffect>();
		potionEffects.add(new PotionEffect(PotionEffectType.SPEED, 400, 0, true, false));
		potionEffects.add(new PotionEffect(PotionEffectType.JUMP, 400, 1, true, false));
		//potionEffects.add(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 1, true, false));
	}
	
	
	/**
	 * Bukkit plugin disable function
	 */
	@Override
	public void onDisable()
	{
	}
	
	
	/**
	 * Saves the config
	 */
	public void saveConfig() {
		lobbyConfig.save();
		super.saveConfig();
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
	{
		// Roll any other accepted raw commands into subcommands
		if (!cmd.getLabel().equalsIgnoreCase("lobby")) {
			
			String[] args2 = new String[args.length + 1];
			args2[0] = cmd.getLabel();
			for (int i = 0; i < args.length; i++) {
				args2[i + 1] = args[i];
			}
			
			args = args2;
			cmd.setLabel("l");
		}
		
		this.cmdBase.execute(sender, new ArrayList<String>(Arrays.asList(args)));
		return true;
	}
	
	
	/**
	 * Generates effects for the player
	 * @param p The player instance
	 */
	public void givePlayerEffect(Player p) {
		
		for(PotionEffect pot : this.potionEffects) {
			p.addPotionEffect(pot, true);
		}
	}
	
	
	/**
	 * Teleports a player to spawn
	 * @param p The player
	 */
	public void teleportToSpawn(Player p) {
		p.teleport(lobbyConfig.getSpawnLocation());
	}
}
