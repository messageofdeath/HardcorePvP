package me.messageofdeath.HardcorePvP.File;

import me.messageofdeath.HardcorePvP.HardcorePvP;

public class Backend {
	
	public static int getLevel(String name) {
		return HardcorePvP.database.getInt(name + ".level");
	}
	
	public static int getKills(String name) {
		return HardcorePvP.database.getInt(name + ".kills");
	}
	
	public static void setLevel(String name, int level) {
		HardcorePvP.database.set(name + ".level", level);
		HardcorePvP.save();
	}
	
	public static void setKills(String name, int kills) {
		HardcorePvP.database.set(name + ".kills", kills);
		HardcorePvP.save();
	}
	
	public static int getMaxLevel() {
		return HardcorePvP.database.getInt("maxlevel");
	}
}
