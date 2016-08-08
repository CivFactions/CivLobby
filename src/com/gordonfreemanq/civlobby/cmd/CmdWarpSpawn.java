package com.gordonfreemanq.civlobby.cmd;
import com.gordonfreemanq.civlobby.util.*;


public class CmdWarpSpawn extends LobbyCommand {

	public CmdWarpSpawn()
	{
		super();
		this.aliases.add("spawn");

		this.setHelpShort("Warp to the spawn location");
		
		this.errorOnToManyArgs = false;
		this.senderMustBePlayer = true;
		
		this.visibility = CommandVisibility.SECRET;
	}

	@Override
	public void perform() 
	{
		me.teleport(plugin.getLobbyConfig().getSpawnLocation());
	}
}
