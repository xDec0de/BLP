package es.xdec0de.blp.utils.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import es.xdec0de.blp.BLP;

class FileUtils {

	private static File copyInputStreamToFile(String path, InputStream inputStream) {
		File file = new File(path);
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			int read;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1)
				outputStream.write(bytes, 0, read);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	static void updateFile(File file, String path) {
		try {
			int changes = 0;
			FileConfiguration old = Utf8YamlConfiguration.loadConfiguration(file);
			Utf8YamlConfiguration updated = new Utf8YamlConfiguration();
			JavaPlugin plugin = BLP.getInstance();
			if(plugin.getResource(path) != null)
				updated.load(copyInputStreamToFile(plugin.getDataFolder()+ "/"+path, plugin.getResource(path)));
			else {
				MessageUtils.logColRep("%prefix% Could not update &6"+path);
				return;
			}
			Set<String> oldKeys = old.getKeys(true);
			Set<String> updKeys = updated.getKeys(true);
			for(String str : oldKeys)
				if(!updKeys.contains(str)) {
					old.set(str, null);
					changes++;
				}
			for(String str : updKeys)
				if(!oldKeys.contains(str)) {
					old.set(str, updated.get(str));
					changes++;
				}
			old.save(plugin.getDataFolder() + "/"+path);
			if(changes != 0)
				MessageUtils.logColRep("%prefix% &6"+path+" &7has been updated to &ev"+BLP.getInstance().getDescription().getVersion()+"&7 with &b"+changes+" &7changes.");
		} catch(InvalidConfigurationException | IOException ex) {
			MessageUtils.logColRep("%prefix% Could not update &6"+path);
		}
	}
}
