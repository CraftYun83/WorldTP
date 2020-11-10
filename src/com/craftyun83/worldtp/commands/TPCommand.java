package com.craftyun83.worldtp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.craftyun83.worldtp.Main;

public class TPCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public TPCommand (Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("worldtp").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be activated by a player!");
			return true;
		}
		Player p = (Player) sender;
		
		if (p.hasPermission("worldtp.tp")) {
			try {
				if (args[0].equalsIgnoreCase("tp")) {
					World dest = Bukkit.getWorld(args[1]);
					try {
						p.teleport(new Location(dest, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), 0, 0));
					}
					catch (Exception e) {
						p.sendMessage("Didn't set specific coords, sent you to the world's default location!");
						p.teleport(new Location(dest,0,0,0,0,0));
						p.teleport(dest.getSpawnLocation());
					}
				}
			}
			catch (Exception e){
				p.sendMessage("This command takes 1 subcommand!");
			}
		} else {
			p.sendMessage("You do not have sufficient permission!");
		}
		if (p.hasPermission("worldtp.createworld")) {
			try {
				if (args[0].equalsIgnoreCase("world")) {
					if (args[1].equalsIgnoreCase("create")) {
						WorldCreator wc = new WorldCreator(args[2]);
						if (args[3].equalsIgnoreCase("FLAT")) {
							wc.type(WorldType.FLAT);
						}
						if (args[3].equalsIgnoreCase("NORMAL")) {
							wc.type(WorldType.NORMAL);
						}
						if (args[3].equalsIgnoreCase("LARGE_BIOMES")) {
							wc.type(WorldType.LARGE_BIOMES);
						}
						if (args[3].equalsIgnoreCase("VOID")) {
							wc.type(WorldType.FLAT);
							wc.generatorSettings("2;0;1;"); //This is what makes the world empty (void)
						}
				        wc.createWorld();
					}
				}
			}
			catch (Exception e){
				p.sendMessage("Command Arguments: /worldtp world create <name> <generator>");
			}
		} else {
			p.sendMessage("You do not have sufficient permission!");
		}
		return false;
	}
}
