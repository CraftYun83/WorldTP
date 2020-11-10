package com.craftyun83.worldtp;

import org.bukkit.plugin.java.JavaPlugin;

import com.craftyun83.worldtp.commands.TPCommand;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new TPCommand(this);
	}
}
