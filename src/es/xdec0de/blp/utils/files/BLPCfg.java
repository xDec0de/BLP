package es.xdec0de.blp.utils.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.xdec0de.blp.BLP;

public class BLPCfg {

	private static FileConfiguration cfg;
	private static File file;
	
	public static void setup() {
		if (!BLP.getInstance().getDataFolder().exists())
			BLP.getInstance().getDataFolder().mkdir(); 
		if (!(file = new File(BLP.getInstance().getDataFolder(), "config.yml")).exists())
			BLP.getInstance().saveResource("config.yml", false); 
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			MessageUtils.logCol("&e&lBattleLevelsPAPI: &cCould not save &6config.yml&c file.");
		} 
	}

	public static FileConfiguration get() {
		return cfg;
	}

	public static void reload() {
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
	}
}
