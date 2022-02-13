package es.xdec0de.blp.utils.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.xdec0de.blp.BLP;

public class BLPCfg {

	static FileConfiguration cfg;
	private static File file;

	public static void setup(boolean isByReload) {
		BLP blp = BLP.getPlugin(BLP.class);
		if (!blp.getDataFolder().exists())
			blp.getDataFolder().mkdir(); 
		if (!(file = new File(blp.getDataFolder(), "config.yml")).exists())
			blp.saveResource("config.yml", false); 
		reload(true, isByReload);
	}

	private static void reload(boolean update, boolean isByReload) {
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		if(update && FileUtils.updateFile(file, "config.yml", isByReload))
			reload(false, isByReload);
	}
}
