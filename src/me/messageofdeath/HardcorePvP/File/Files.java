package me.messageofdeath.HardcorePvP.File;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import me.messageofdeath.HardcorePvP.HardcorePvP;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	
	private HardcorePvP pvp;
	public File dbFile;
	public FileConfiguration database;
	
	public Files(HardcorePvP instance) {
		pvp = instance;
	}
	
	public void init() {
		dbFile = new File(pvp.getDataFolder(), "database.yml");
		if(!dbFile.exists()) {
			dbFile.getParentFile().mkdirs();
			copy(pvp.getResource("database.yml"), dbFile);
		}
		database = new YamlConfiguration();
		try {
			database.load(dbFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
