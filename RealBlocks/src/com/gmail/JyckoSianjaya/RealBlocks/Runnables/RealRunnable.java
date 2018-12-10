package com.gmail.JyckoSianjaya.RealBlocks.Runnables;

import java.util.ArrayList;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.JyckoSianjaya.RealBlocks.RealBlocks;

public class RealRunnable {
	private static RealRunnable instance;
	private ArrayList<RealTask> tasks = new ArrayList<RealTask>();
	private ArrayList<RealTask> synctasks = new ArrayList<RealTask>();
	private RealRunnable() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (RealTask task : new ArrayList<RealTask>(tasks)) {
					if (task.getHealth() <= 0) {
						tasks.remove(task);
						continue;
					}
					task.runTask();
					task.reduceHealth();
				}
			}
		}.runTaskTimerAsynchronously(RealBlocks.getInstance(), 20L, 20L);
		new BukkitRunnable() {
			@Override
			public void run() {
				for (RealTask task : new ArrayList<RealTask>(synctasks)) {
					if (task.getHealth() <= 0) {
						synctasks.remove(task);
						continue;
					}
					task.reduceHealth();
					task.runTask();
				}
			}
		}.runTaskTimer(RealBlocks.getInstance(), 1L, 1L);
	}
	public static RealRunnable getInstance() { if (instance == null) instance = new RealRunnable(); return instance; }
	public void addTask(RealTask task) { 
		this.tasks.add(task);
	}
	public void addSyncTask(RealTask task) {
		this.synctasks.add(task);
	}
}
