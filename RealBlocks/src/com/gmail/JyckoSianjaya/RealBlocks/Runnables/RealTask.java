package com.gmail.JyckoSianjaya.RealBlocks.Runnables;

public interface RealTask {
	int health = 0;
	public void runTask();
	public void reduceHealth();
	public int getHealth();
}
