package es.xdec0de.blp.utils.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.xdec0de.blp.BLP;
import es.xdec0de.blp.utils.files.enums.BLPMessage;
import es.xdec0de.blp.utils.files.enums.BLPSetting;

public class BLPCfg {

	private static FileConfiguration cfg;
	private static File file;
	
	public static void setup() {
		if (!BLP.getInstance().getDataFolder().exists())
			BLP.getInstance().getDataFolder().mkdir(); 
		if (!(file = new File(BLP.getInstance().getDataFolder(), "config.yml")).exists())
			BLP.getInstance().saveResource("config.yml", false); 
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		if(FileUtils.updateFile(file, "config.yml"))
			reload();
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

	public static String getString(BLPSetting setting) {
		return cfg.getString(setting.getPath());
	}

	public static boolean getBoolean(BLPSetting setting) {
		return cfg.getBoolean(setting.getPath());
	}

	public static boolean hasPermission(BLPSetting permission, CommandSender sender, boolean message) {
		if(sender.hasPermission(cfg.getString(permission.getPath())))
			return true;
		if(message)
			MessageUtils.sendMessage(sender, BLPMessage.NO_PERM);
		return false;
	}
}
