package com.gmail.JyckoSianjaya.RealBlocks.Storage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.JyckoSianjaya.RealBlocks.RealBlocks;

public class ToggleStorage {
	private static ToggleStorage instance;
	private HashMap<UUID, Boolean> toggles = new HashMap<UUID, Boolean>();
	private ToggleStorage() {
		
		loadToggles();}
	public static ToggleStorage getInstance() { if (instance == null) instance = new ToggleStorage(); return instance; }
	public void saveToggle() {
		File f = new File(RealBlocks.getInstance().getDataFolder(), "datafile.yml");
		if (!f.exists()) {
			RealBlocks.getInstance().saveResource("datafile.yml", false);

		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.createSection("toggles");
		ConfigurationSection saves = yml.getConfigurationSection("toggles");
		for (UUID u : this.toggles.keySet()) {
			saves.set(u.toString(), toggles.get(u));
		}
		try {
			yml.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setToggle(UUID uu, Boolean bool) {
		this.toggles.put(uu, bool);
	}
	public Boolean getToggle(UUID uu) {
		return this.toggles.get(uu);
	}
	public void loadToggles() {
		File f = new File(RealBlocks.getInstance().getDataFolder(), "datafile.yml");
		if (!f.exists()) {
			RealBlocks.getInstance().saveResource("datafile.yml", false);
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		ConfigurationSection toggles = yml.getConfigurationSection("toggles");
		Set<String> keys = toggles.getKeys(false);
		for (String str : keys) {
			UUID uu = UUID.fromString(str);
			this.toggles.put(uu, toggles.getBoolean(str));
		}
	}
}
