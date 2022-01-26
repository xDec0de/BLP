package es.xdec0de.blp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.xdec0de.blp.BLP;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.MessageUtils;
import es.xdec0de.blp.utils.files.enums.BLPMessage;
import es.xdec0de.blp.utils.files.enums.BLPSetting;
import net.md_5.bungee.api.ChatColor;

public class UpdateChecker implements Listener {

	private static final int resourceId = 74141;

	private static void getLatestVersion(final Consumer<String> consumer) {
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
		if(BLPCfg.getBoolean(BLPSetting.CHECK_UPDATES) && BLPCfg.getBoolean(BLPSetting.UPDATER_MESSAGE_PLAYER))
			if(BLPCfg.hasPermission(BLPSetting.UPDATE_NOTIFY_PERMISSION, e.getPlayer(), false))
				sendUpdate(e.getPlayer());
	}

	public static void sendUpdate(CommandSender sender) {
		getLatestVersion(version -> {
			String current = BLP.getInstance().getDescription().getVersion();
			if(!current.equalsIgnoreCase(version))
				if(sender instanceof Player)
					MessageUtils.sendMessage(sender, BLPMessage.UPDATE_AVAILABLE_PLAYER, "%current%", current, "%new%", version);
				else
					MessageUtils.sendMessage(sender, BLPMessage.UPDATE_AVAILABLE_CONSOLE, "%current%", current, "%new%", version);
			else
				if(sender instanceof Player)
					MessageUtils.sendMessage(sender, BLPMessage.UPDATE_LATEST_PLAYER, "%current%", current);
				else
					MessageUtils.sendMessage(sender, BLPMessage.UPDATE_LATEST_CONSOLE, "%current%", current);
		});
	}
}
