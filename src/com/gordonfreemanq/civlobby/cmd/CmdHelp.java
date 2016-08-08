package com.gordonfreemanq.civlobby.cmd;

import java.util.ArrayList;


public class CmdHelp extends LobbyCommand {

	public ArrayList<ArrayList<String>> helpPages;
	
	public CmdHelp()
	{
		super();
		this.aliases.add("help");
		this.aliases.add("h");
		this.aliases.add("?");

		this.optionalArgs.put("page", "1");

		senderMustBePlayer = true;
	}

	@Override
	public void perform() 
	{
		if (helpPages == null) updateHelp();

		int page = this.argAsInt(0, 1);

		msg(plugin.txt.titleize("CivLobby Help ("+page+"/"+helpPages.size()+")"));

		page -= 1;
		getUseageTemplate();
		if (page < 0 || page >= helpPages.size())
		{
			msg("<b>This page does not exist");
			return;
		}
		msg(helpPages.get(page));
	}
	
	
	public void updateHelp()
	{
		helpPages = new ArrayList<ArrayList<String>>();
		ArrayList<String> pageLines;

		pageLines = new ArrayList<String>();
		pageLines.add( plugin.txt.parse("<i>CivFactions is a server that allows players"));
		pageLines.add( plugin.txt.parse("<i>to build cities, nations, and civilizations."));
		pageLines.add("");
		pageLines.add( plugin.txt.parse("<i>Use <c>/spawn <i>to return to the lobby spawn location."));
		helpPages.add(pageLines);
	}
}
