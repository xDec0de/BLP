package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.xdec0de.blp.cmd.BLPCMD;
import es.xdec0de.blp.utils.files.BLPCfg;
import net.md_5.bungee.api.ChatColor;

public class BLP extends JavaPlugin {

	private static BLP instance;

	public void onEnable() {
		executeEnable();
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|------------------------------------------|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|             &e&lBattleLevelsPAPI             &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &aPlugin enabled                        &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &7Author:                               &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7     - &bxDec0de_                           &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &7Version: &b1.1                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|------------------------------------------|"));
		Bukkit.getConsoleSender().sendMessage(" ");
		checkDependencies();
		Bukkit.getConsoleSender().sendMessage(" ");
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8                                    "));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|------------------------------------------|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|             &e&lBattleLevelsPAPI             &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &cPlugin disabled                       &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &7Author:                               &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7     - &bxDec0de_                           &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&7  - &7Version: &b1.1                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|&8                                          &8|"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|------------------------------------------|"));
		Bukkit.getConsoleSender().sendMessage(" ");
	}

	private void executeEnable() {
		instance = this;
		BLPCfg.setup();
		BLPCfg.save();
		getCommand("blp").setExecutor((CommandExecutor)new BLPCMD());
	}
	
	private void checkDependencies() {
		Plugin bl = Bukkit.getPluginManager().getPlugin("BattleLevels");
		if(bl != null)
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e- &bBattleLevels &7detected (&av" + bl.getDescription().getVersion() + "&7)"));
		else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4- &eBattleLevels &cNOT detected, disabling plugin."));
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		Plugin papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
		if(papi != null)
			new PAPI(papi);
		else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4- &ePlaceholderAPI &cNOT detected, disabling plugin."));
			Bukkit.getPluginManager().disablePlugin(instance);
		}
	}
	
	public static BLP getInstance() {
		return instance;
	}
}
