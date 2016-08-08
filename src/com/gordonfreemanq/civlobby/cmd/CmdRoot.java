package com.gordonfreemanq.civlobby.cmd;

import java.util.Collections;

public class CmdRoot extends LobbyCommand {
	
	public CmdRoot()
	{
		super();
		
		this.aliases.add("l");
		this.aliases.removeAll(Collections.singletonList(null));  // remove any nulls from extra commas
		
		this.addSubCommand(new CmdSetSpawn());
		this.addSubCommand(new CmdWarpSpawn());
		this.addSubCommand(new CmdUnknown());
		this.addSubCommand(new CmdHelp());

	}
	
	@Override
	public void perform()
	{
		this.commandChain.add(this);
	}
}
