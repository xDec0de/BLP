package es.xdec0de.blp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.xdec0de.blp.BLP;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.BLPMessage;
import es.xdec0de.blp.utils.files.BLPSetting;
import es.xdec0de.blp.utils.files.MessageUtils;
import net.md_5.bungee.api.ChatColor;

public class UpdateChecker implements Listener {

	private static final int resourceId = 74141;

	public static void getLatestVersion(final Consumer<String> consumer) {
		Bukkit.getScheduler().runTaskAsynchronously(BLP.getInstance(), () -> {
			try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
				if (scanner.hasNext())
					consumer.accept(scanner.next());
			} catch (IOException ex) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lLangAPI&8: &8[&cWarning&8] &cAn error occurred while checking for updates&8:&6 " + ex.getMessage()));
			}
		});
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(BLPCfg.getBoolean(BLPSetting.CHECK_UPDATES) && BLPCfg.getBoolean(BLPSetting.UPDATER_MESSAGE_PLAYER)) {
			if(e.getPlayer().hasPermission(BLPCfg.getString(BLPSetting.UPDATER_PERMISSION))) {
				getLatestVersion(version -> {
					String current = BLP.getInstance().getDescription().getVersion();
					if(!current.equalsIgnoreCase(version)) {
						MessageUtils.sendMessage(e.getPlayer(), BLPMessage.UPDATE_AVAILABLE_PLAYER, "%current%", current, "%mew%", version);
					}
				});
			}
		}
	}
}
