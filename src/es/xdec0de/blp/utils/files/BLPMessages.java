package es.xdec0de.blp.utils.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.xdec0de.blp.BLP;
import es.xdec0de.blp.utils.files.enums.BLPMessage;

public class BLPMessages {

	private static FileConfiguration cfg;
	private static File file;
	
	public static void setup(boolean isByReload) {
		if (!BLP.getInstance().getDataFolder().exists())
			BLP.getInstance().getDataFolder().mkdir(); 
		if (!(file = new File(BLP.getInstance().getDataFolder(), "messages.yml")).exists())
			BLP.getInstance().saveResource("messages.yml", false); 
		reload(true, isByReload);
	}

	private static void reload(boolean update, boolean isByReload) {
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		if(update && FileUtils.updateFile(file, "messages.yml", isByReload)) // If update is false, the update won't be called because the and operator returns false.
			reload(false, isByReload);
		MessageUtils.prefix = cfg.getString(BLPMessage.PREFIX.getPath());
		MessageUtils.errorPrefix = cfg.getString(BLPMessage.ERROR.getPath());
	}

	static FileConfiguration get() {
		return cfg;
	}
}
