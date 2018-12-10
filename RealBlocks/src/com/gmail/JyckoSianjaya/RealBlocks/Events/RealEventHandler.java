package com.gmail.JyckoSianjaya.RealBlocks.Events;

import org.bukkit.Location;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.gmail.JyckoSianjaya.RealBlocks.Runnables.RealRunnable;
import com.gmail.JyckoSianjaya.RealBlocks.Runnables.RealTask;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.Utility;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.XMaterial;
import com.gmail.JyckoSianjaya.RealBlocks.Utility.XSound;

public class RealEventHandler {
	private static RealEventHandler instance;
	private RealEventHandler() {
	}
	Material air = XMaterial.AIR.parseMaterial();
	public static RealEventHandler getInstance() { if (instance == null) instance = new RealEventHandler(); return instance;
	}
	public void manageThrow(PlayerInteractEvent e) {
		ArmorStand block = (ArmorStand) e.getPlayer().getPassenger();
		Block target = null;
		if (e.getClickedBlock() == null) {
		BlockIterator iter = new BlockIterator(e.getPlayer(), 30);
		target = iter.next();
		while (iter.hasNext()) {
			target = iter.next();
			if (target.getType() == this.air) continue;
			break;
		}
		}
		else {
			target = e.getClickedBlock();
		}
		if (target.getType() == this.air) return;
		block.eject();
		e.getPlayer().removePassenger(block);
		Location targetlocation = target.getLocation().clone();
		Location origin = e.getPlayer().getEyeLocation();
		Vector direction = targetlocation.toVector().subtract(origin.toVector());
		block.setVelocity(direction.normalize().multiply(2));
		Material item = block.getHelmet().getType();
		World world = targetlocation.getWorld();
		RealRunnable.getInstance().addSyncTask(new RealTask() {
			int health = 1;
			@Override
			public void runTask() {
				// TODO Auto-generated method stub
				if (!block.isOnGround()) return;
				health = 0;
				Block below = world.getBlockAt(block.getLocation().add(0.0, -1, 0.0));
				if (below.getType() != air) {
					world.getBlockAt(block.getLocation()).setType(item);
				}
				else {
					below.setType(item);
				}
				Utility.PlaySoundAt(world, block.getLocation(), XSound.STEP_WOOD.bukkitSound(), 2.0F, 1.4F);
				block.remove();
			}

			@Override
			public void reduceHealth() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getHealth() {
				// TODO Auto-generated method stub
				return health;
			}
		});
	}
	public void manageThrow(PlayerInteractAtEntityEvent e) {
		ArmorStand block = (ArmorStand) e.getPlayer().getPassenger();
		Block target = null;
		BlockIterator iter = new BlockIterator(e.getPlayer(), 10);
		target = iter.next();
		while (iter.hasNext()) {
			target = iter.next();
			if (target.getType() == this.air) continue;
			break;
		}
		if (target.getType() == this.air) return;
		block.eject();
		e.getPlayer().removePassenger(block);
		Location targetlocation = target.getLocation().clone();
		Location origin = e.getPlayer().getEyeLocation();
		Vector direction = targetlocation.toVector().subtract(origin.toVector());
		block.setVelocity(direction.normalize().multiply(2));
		Material item = block.getHelmet().getType();
		World world = targetlocation.getWorld();
		RealRunnable.getInstance().addSyncTask(new RealTask() {
			int health = 1;
			@Override
			public void runTask() {
				// TODO Auto-generated method stub
				if (!block.isOnGround()) return;
				health = 0;
				Block below = world.getBlockAt(block.getLocation().add(0.0, -1, 0.0));
				if (below.getType() != air) {
					world.getBlockAt(block.getLocation()).setType(item);
				}
				else {
					below.setType(item);
				}
				Utility.PlaySoundAt(world, block.getLocation(), XSound.STEP_WOOD.bukkitSound(), 2.0F, 1.4F);
				block.remove();
			}

			@Override
			public void reduceHealth() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getHealth() {
				// TODO Auto-generated method stub
				return health;
			}
		});
	}
	public void manageEntityDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	@SuppressWarnings("deprecation")
	public void managePickup(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		ArmorStand armor = (ArmorStand) p.getWorld().spawnEntity(b.getLocation(), EntityType.ARMOR_STAND);
		p.setPassenger(armor);
		armor.setCustomNameVisible(false);
		armor.setInvulnerable(true);
		// EulerAngle(Pitch, yaw, Roll)
		EulerAngle bodyangle = new EulerAngle(270, 0, 0);
		armor.setBodyPose(bodyangle);
		EulerAngle legangle = new EulerAngle(180, 0, 0);
		armor.setHeadPose(new EulerAngle(180, 0, 0));
		armor.setLeftLegPose(legangle);
		armor.setRightLegPose(legangle);
		armor.setCollidable(false);
		armor.setVisible(false);
		armor.setBasePlate(false);
		armor.setGravity(true);
		armor.setHelmet(new ItemStack(b.getType()));
		armor.setCustomName("throwableblock");
		b.setType(XMaterial.AIR.parseMaterial());
		Utility.PlaySound(p, XSound.HORSE_ARMOR.bukkitSound(), 1.0F, 1.2F);
	}
	
}
