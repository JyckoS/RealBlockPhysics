package com.gmail.JyckoSianjaya.RealBlocks;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.JyckoSianjaya.RealBlocks.Commands.RealCommand;
import com.gmail.JyckoSianjaya.RealBlocks.Events.RealEventHandler;
import com.gmail.JyckoSianjaya.RealBlocks.Events.RealEventListener;
import com.gmail.JyckoSianjaya.RealBlocks.Runnables.RealRunnable;
import com.gmail.JyckoSianjaya.RealBlocks.Storage.ToggleStorage;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.ActionBarAPI;

public class RealBlocks extends JavaPlugin {
	private static RealBlocks instance;
	private RealCommand rcmd;
	private RealEventHandler rhnd;
	private RealRunnable runnable;
	private ToggleStorage strg;
	private ActionBarAPI aba = ActionBarAPI.getInstance();
	@Override
	public void onEnable() {
		instance = this;
		new Metrics(this);
		loadObjects();
		loadCommand();
		loadEvents();
	}
	private void loadCommand() {
		this.getCommand("throws").setExecutor(RealCommand.getInstance());
	}
	private void loadEvents() {
		this.getServer().getPluginManager().registerEvents(new RealEventListener(), this);
	}
	@Override
	public void onDisable() {
		ToggleStorage.getInstance().saveToggle();
	}
	public static RealBlocks getInstance() { return instance; }
	private void loadObjects() {
		rcmd = RealCommand.getInstance();
		rhnd = RealEventHandler.getInstance();
		runnable = RealRunnable.getInstance();
		strg = ToggleStorage.getInstance();
	}
}
