package com.gmail.JyckoSianjaya.RealBlocks.Events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.JyckoSianjaya.RealBlocks.Storage.ToggleStorage;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.Utility;

public class RealEventListener implements Listener {
	private RealEventHandler handler = RealEventHandler.getInstance();
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		switch (e.getAction()) {
		default:
			return;
		case RIGHT_CLICK_BLOCK:
		case RIGHT_CLICK_AIR:
			manageBlockPickUp(e);
			return;
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof ArmorStand)) return;
		ArmorStand arm = (ArmorStand) e.getRightClicked();
		Player p = e.getPlayer();
		if (p.getPassenger() == null) return;
		if (p.getPassenger() != arm) return;
		if (arm.getCustomName() == null) return;
		if (!arm.getCustomName().equals("throwableblock")) return;
		handler.manageThrow(e);
	}
	private void manageBlockThrow(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Entity pass = p.getPassenger();
		if (!(pass instanceof ArmorStand)) return;
		ArmorStand cow = (ArmorStand) pass;
		if (cow.getCustomName() == null) return;
		if (!cow.getCustomName().equals("throwableblock")) return;
		handler.manageThrow(e);
	}
	private void manageBlockPickUp(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getPassenger() != null) {
			manageBlockThrow(e);
			return;
		}
		Boolean toggled = ToggleStorage.getInstance().getToggle(e.getPlayer().getUniqueId());
		if (toggled == null) toggled = false;
		if (!toggled) {
			return;
		}
		handler.managePickup(e);
		
	}
}
