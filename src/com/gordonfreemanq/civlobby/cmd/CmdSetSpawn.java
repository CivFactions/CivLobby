package com.gordonfreemanq.civlobby.cmd;

import org.bukkit.Location;
import org.bukkit.World;

import com.gordonfreemanq.civlobby.Lang;
import com.gordonfreemanq.civlobby.util.*;


public class CmdSetSpawn extends LobbyCommand {

	public CmdSetSpawn()
	{
		super();
		this.aliases.add("setspawn");

		this.setHelpShort("Sets the spawn location");
		
		this.errorOnToManyArgs = false;
		this.senderMustBePlayer = true;
		
		this.permission = Permission.ADMIN.node;
		this.visibility = CommandVisibility.SECRET;
	}

	@Override
	public void perform() 
	{
		Location l = me.getPlayer().getLocation();
		World w = me.getPlayer().getWorld();
		w.setSpawnLocation(l.getBlockX(), l.getBlockY(), l.getBlockZ());
		this.plugin.getLobbyConfig().setSpawnLocation(l);
		msg(Lang.adminSetSpawn);
	}
}
