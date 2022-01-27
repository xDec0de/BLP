package es.xdec0de.blp.utils.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.xdec0de.blp.BLP;
import es.xdec0de.blp.utils.files.enums.BLPMessage;

public class BLPMessages {

	private static FileConfiguration cfg;
	private static File file;
	
	public static void setup() {
		if (!BLP.getInstance().getDataFolder().exists())
			BLP.getInstance().getDataFolder().mkdir(); 
		if (!(file = new File(BLP.getInstance().getDataFolder(), "messages.yml")).exists())
			BLP.getInstance().saveResource("messages.yml", false); 
		reload(true);
	}

	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			MessageUtils.logCol("&e&lBattleLevelsPAPI: &cCould not save &6messages.yml&c file.");
		}
	}

	public static FileConfiguration get() {
		return cfg;
	}

	private static void reload(boolean update) {
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		MessageUtils.prefix = cfg.getString(BLPMessage.PREFIX.getPath());
		MessageUtils.errorPrefix = cfg.getString(BLPMessage.ERROR.getPath());
		if(update && FileUtils.updateFile(file, "messages.yml")) // If update is false, the update won't be called because the and operator returns false.
			reload(false);
	}
}
