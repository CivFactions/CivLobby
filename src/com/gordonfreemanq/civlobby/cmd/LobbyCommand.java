package com.gordonfreemanq.civlobby.cmd;

import com.gordonfreemanq.civlobby.CivLobby;
import com.gordonfreemanq.civlobby.util.BaseCommand;

/**
 * Abstract class for a Groups command
 * @author GFQ
 *
 */
public abstract class LobbyCommand extends BaseCommand<CivLobby>
{

	/**
	 * @brief Constructor
	 */
	public LobbyCommand()
	{
		super(CivLobby.getPlugin());
	}
}
