package com.gordonfreemanq.civlobby.cmd;

import com.gordonfreemanq.civlobby.util.*;

public class CmdUnknown extends LobbyCommand {

	public CmdUnknown()
	{
		super();
		this.aliases.add("unknown");
		
		this.errorOnToManyArgs = false;
		this.visibility = CommandVisibility.INVISIBLE;

		senderMustBePlayer = false;
	}

	@Override
	public void perform() 
	{
		msg("<w>Unknown command. Type \"/help\" for help.");
	}
}
