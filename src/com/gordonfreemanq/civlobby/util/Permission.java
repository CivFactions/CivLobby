package com.gordonfreemanq.civlobby.util;

import org.bukkit.command.CommandSender;

import com.gordonfreemanq.civlobby.CivLobby;

public enum Permission
{
	ADMIN("admin"),
	MOD("mod");
	
	/**
	 * The node string that is referenced for permissions
	 */
	public final String node;
	
	Permission(final String node)
	{
		this.node = "civlobby." + node;
	}
	
	public boolean has(CommandSender sender, boolean informSenderIfNot)
	{
		return CivLobby.getPlugin().perm.has(sender, this.node, informSenderIfNot);
	}
	
	public boolean has(CommandSender sender)
	{
		return has(sender, false);
	}
}
