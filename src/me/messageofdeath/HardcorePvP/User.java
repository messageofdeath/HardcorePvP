package me.messageofdeath.HardcorePvP;

import me.messageofdeath.HardcorePvP.File.Backend;

public class User {
	
	public static int getLevel(String name) {
		return Backend.getLevel(name);
	}
	
	public static int getKills(String name) {
		return Backend.getKills(name);
	}
	
	public static void setLevel(String name, int level) {
		Backend.setLevel(name, level);
	}
	
	public static void setKills(String name, int kills) {
		Backend.setLevel(name, kills);
	}
}
