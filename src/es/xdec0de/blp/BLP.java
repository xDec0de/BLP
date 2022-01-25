package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.xdec0de.blp.cmd.BLPCMD;
import es.xdec0de.blp.utils.MessageUtils;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.BLPMessages;

public class BLP extends JavaPlugin {

	private static BLP instance;

	public void onEnable() {
		executeEnable();
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------|");
		MessageUtils.logCol("&8|                                          &8|");
		MessageUtils.logCol("&8|             &e&lBattleLevelsPAPI             &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|&7  - &aPlugin disabled                       &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|&7  - &7Author:                               &8|");
		MessageUtils.logCol("&8|&7                                          &8|");
		MessageUtils.logCol("&8|&7     - &bxDec0de_                           &8|");
		MessageUtils.logCol("&8|&7                                          &8|");
		MessageUtils.logCol("&8|&7  - &7Version: &b1.1                          &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|------------------------------------------|");
		MessageUtils.log(" ");
		checkDependencies();
		Bukkit.getConsoleSender().sendMessage(" ");
	}

	public void onDisable() {
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------|");
		MessageUtils.logCol("&8|                                          &8|");
		MessageUtils.logCol("&8|             &e&lBattleLevelsPAPI             &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|&7  - &cPlugin disabled                       &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|&7  - &7Author:                               &8|");
		MessageUtils.logCol("&8|&7                                          &8|");
		MessageUtils.logCol("&8|&7     - &bxDec0de_                           &8|");
		MessageUtils.logCol("&8|&7                                          &8|");
		MessageUtils.logCol("&8|&7  - &7Version: &b1.1                          &8|");
		MessageUtils.logCol("&8|&8                                          &8|");
		MessageUtils.logCol("&8|------------------------------------------|");
		MessageUtils.log(" ");
	}

	private void executeEnable() {
		instance = this;
		BLPCfg.setup();
		BLPMessages.setup();
		getCommand("blp").setExecutor(new BLPCMD());
	}
	
	private void checkDependencies() {
		Plugin bl = Bukkit.getPluginManager().getPlugin("BattleLevels");
		if(bl != null)
			MessageUtils.logCol("&e- &bBattleLevels &7detected (&av" + bl.getDescription().getVersion() + "&7)");
		else {
			MessageUtils.logCol("&4- &eBattleLevels &cNOT detected, disabling plugin.");
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		Plugin papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
		if(papi != null)
			new PAPI(papi);
		else {
			MessageUtils.logCol("&4- &ePlaceholderAPI &cNOT detected, disabling plugin.");
			Bukkit.getPluginManager().disablePlugin(instance);
		}
	}

	public static BLP getInstance() {
		return instance;
	}
}
