package com.gordonfreemanq.civlobby.util;

import java.util.logging.Level;

public interface IPluginLogger {
	public void log(Object msg);
	public void log(String str, Object... args);
	public void log(Level level, String str, Object... args);
	public void log(Level level, Object msg);
}
