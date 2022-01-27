package es.xdec0de.blp.utils.files;

import java.io.File;

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
		reload(true);
	}

	private static void reload(boolean update) {
		cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		if(update && FileUtils.updateFile(file, "config.yml"))
			reload(false);
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
