package me.messageofdeath.HardcorePvP;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.messageofdeath.HardcorePvP.File.Backend;
import me.messageofdeath.HardcorePvP.File.Files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcorePvP extends JavaPlugin implements Listener {
	
	public static File dbFile;
	public static FileConfiguration database;
	public static HardcorePvP plugin;
	
	@Override
	public void onEnable() {
		Files file = new Files(this);
		file.init();
		dbFile = file.dbFile;
		database = file.database;
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		log(Level.INFO, "is now enabled!");
	}
	
	@Override
	public void onDisable() {
	}
	
	public static void save() {
		try {
			database.save(dbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void log(Level level, String log) {
		Bukkit.getServer().getLogger().log(level, "[PermissionsOP] " + log);
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent event) {
		Player died = event.getEntity();
		Player killer = died.getKiller();
		int kills = User.getKills(killer.getName()) + 1;
		if(kills >= 2) {
			User.setKills(killer.getName(), 0);
			int level = User.getLevel(killer.getName()) + 1;
			if(level < Backend.getMaxLevel()) {
				User.setLevel(killer.getName(), level);
				killer.sendMessage("You ranked up to level " + level);
			}
		}else{User.setKills(killer.getName(), kills);}
		event.setDeathMessage(ChatColor.BLACK + "[" + ChatColor.DARK_BLUE + "HardCore" + ChatColor.DARK_RED + "P" 
				+ ChatColor.WHITE + "v" + ChatColor.DARK_RED + "P" + ChatColor.BLACK + "] " + ChatColor.DARK_GREEN + died.getName() 
				+ "(" + User.getLevel(died.getName()) + ") was killed by " + killer.getName() + "(" + User.getLevel(killer.getName()) + ")!");
	}
}
