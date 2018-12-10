package com.gmail.JyckoSianjaya.RealBlocks.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.gmail.JyckoSianjaya.RealBlocks.RealBlocks;
import com.gmail.JyckoSianjaya.RealBlocks.Storage.ToggleStorage;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.Utility;

public class RealCommand implements TabExecutor {
	private static RealCommand instance;
	private RealCommand() {}
	public static RealCommand getInstance() { if (instance == null) instance = new RealCommand(); return instance;}
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean onCommand(CommandSender snd, Command cmd, String arg2, String[] args) {
		// TODO Auto-generated method stub
		this.remanage(snd, cmd, args);
		return true;
	}
	public boolean isPlayer(CommandSender snd) { return snd instanceof Player; }
	private void remanage(CommandSender snd, Command cmd, String[] args) {
		int length = args.length;
		if (length < 1) {
			if (!snd.hasPermission("realblockphysics.reload")) {
				if (!snd.hasPermission("realblockphysics.toggle")) {
					Utility.sendMsg(snd, "&c&lOopsie! &7You can't do that.");
					return;
				}
				Player p = (Player) snd;
				Boolean bool = ToggleStorage.getInstance().getToggle(p.getUniqueId());
				if (bool == false || bool == null) {
					ToggleStorage.getInstance().setToggle(p.getUniqueId(), true);
					Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &aenabled&7 Block Throwing!");
					return;
				}
				ToggleStorage.getInstance().setToggle(p.getUniqueId(), false);
				Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &cdisabled &7Block Throwing!");
				return;
			}
			Utility.sendMsg(snd, "&a    &lReal&2&lBlock&8&lPhysics &7by &fGober");
			Utility.sendMsg(snd, "&7 Use &f\"/throws help\" &7for more!");
			return;
		}
		if (!snd.hasPermission("realblockphysics.reload")) {
		if (!snd.hasPermission("realblockphysics.toggle")) {
			Utility.sendMsg(snd, "&c&lOopsie! &7You can't do that.");
			return;
		}
		if (!isPlayer(snd)) { return; }
		Player p = (Player) snd;
		Boolean bool = ToggleStorage.getInstance().getToggle(p.getUniqueId());
		if (bool == false || bool == null) {
			ToggleStorage.getInstance().setToggle(p.getUniqueId(), true);
			Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &aenabled&7 Block Throwing!");
			return;
		}
		ToggleStorage.getInstance().setToggle(p.getUniqueId(), false);
		Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &cdisabled &7Block Throwing!");
		return;
		}
		switch (args[0].toLowerCase()) {
		case "help":
			default:
			{
				Utility.sendMsg(snd, "&a&lReal&2&lBlock&8&lPhysics ");
				Utility.sendMsg(snd, "&7 > &f/rbp &etoggle");
				Utility.sendMsg(snd, "&7 > &f/rbp &creload");
				return;
			}
			case "reload":
				RealBlocks.getInstance().reloadConfig();
				Utility.sendMsg(snd, "&7Config has been reloaded!");
				return;
			case "toggle":
				Player p = (Player) snd;
				Boolean bool = ToggleStorage.getInstance().getToggle(p.getUniqueId());
				if (bool == null || bool == false) {
					ToggleStorage.getInstance().setToggle(p.getUniqueId(), true);
					Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &aenabled&7 Block Throwing!");
					return;
				}
				ToggleStorage.getInstance().setToggle(p.getUniqueId(), false);
				Utility.sendMsg(p, "&8[ &a&l! &8] &7Successfully &cdisabled &7Block Throwing!");
				return;
		}
		}
	}

