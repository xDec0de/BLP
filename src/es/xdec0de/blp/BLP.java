package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.xdec0de.blp.cmd.BLPCMD;
import es.xdec0de.blp.utils.UpdateChecker;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.BLPMessages;
import es.xdec0de.blp.utils.files.MessageUtils;
import es.xdec0de.blp.utils.files.enums.BLPSetting;

public class BLP extends JavaPlugin {

	private static BLP instance;

	public void onEnable() {
		executeEnable();
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
		MessageUtils.logCol("       &e&lBattleLevelsPAPI &8- &aEnabled");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Author&8: &bxDec0de_");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Version: &b"+instance.getDescription().getVersion());
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
		if(checkDependencies())
			checkUpdates();
	}

	public void onDisable() {
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
		MessageUtils.logCol("       &e&lBattleLevelsPAPI &8- &cDisabled");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Author&8: &bxDec0de_");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Version: &b"+instance.getDescription().getVersion());
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
	}

	private void executeEnable() {
		instance = this;
		BLPCfg.setup();
		BLPMessages.setup();
		getCommand("blp").setExecutor(new BLPCMD());
		getServer().getPluginManager().registerEvents(new UpdateChecker(), instance);
	}
	
	private boolean checkDependencies() {
		Plugin bl = Bukkit.getPluginManager().getPlugin("BattleLevels");
		if(bl != null) {
			MessageUtils.logCol("&e- &bBattleLevels &7detected (&av" + bl.getDescription().getVersion() + "&7)");
			MessageUtils.log(" ");
		} else {
			MessageUtils.logCol("&4- &eBattleLevels &cNOT detected, disabling plugin.");
			MessageUtils.log(" ");
			Bukkit.getPluginManager().disablePlugin(instance);
			return false;
		}
		Plugin papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
		if(papi != null)
			new PAPI(papi);
		else {
			MessageUtils.logCol("&4- &ePlaceholderAPI &cNOT detected, disabling plugin.");
			MessageUtils.log(" ");
			Bukkit.getPluginManager().disablePlugin(instance);
			return false;
		}
		return true;
	}

	private void checkUpdates() {
		if(BLPCfg.getBoolean(BLPSetting.CHECK_UPDATES) && BLPCfg.getBoolean(BLPSetting.UPDATER_MESSAGE_CONSOLE))
			UpdateChecker.sendUpdate(Bukkit.getConsoleSender());
	}

	public static BLP getInstance() {
		return instance;
	}
}
