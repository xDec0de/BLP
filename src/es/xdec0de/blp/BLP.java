package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.xdec0de.blp.cmd.BLPCMD;
import es.xdec0de.blp.cmd.BLPTabCompleter;
import es.xdec0de.blp.utils.UpdateChecker;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.BLPMessages;
import es.xdec0de.blp.utils.files.BLPSetting;
import es.xdec0de.blp.utils.files.MessageUtils;

public class BLP extends JavaPlugin {

	public void onEnable() {
		executeEnable();
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
		MessageUtils.logCol("       &e&lBattleLevelsPAPI &8- &aEnabled");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Author&8: &bxDec0de_");
		MessageUtils.log(" ");
		MessageUtils.logCol("  &b- &7Version: &b"+getDescription().getVersion());
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
		MessageUtils.logCol("  &b- &7Version: &b"+getDescription().getVersion());
		MessageUtils.log(" ");
		MessageUtils.logCol("&8|------------------------------------------>");
		MessageUtils.log(" ");
	}

	private void executeEnable() {
		BLPCfg.setup(false);
		BLPMessages.setup(false);
		getCommand("blp").setExecutor(new BLPCMD());
		getCommand("blp").setTabCompleter(new BLPTabCompleter());
		getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
	}
	
	private boolean checkDependencies() {
		Plugin bl = Bukkit.getPluginManager().getPlugin("BattleLevels");
		if(bl != null) {
			MessageUtils.logCol("&e- &bBattleLevels &7detected (&av" + bl.getDescription().getVersion() + "&7)");
			MessageUtils.log(" ");
		} else {
			MessageUtils.logCol("&4- &eBattleLevels &cNOT detected, disabling plugin.");
			MessageUtils.log(" ");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}
		Plugin papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
		if(papi != null)
			new PAPI(papi);
		else {
			MessageUtils.logCol("&4- &ePlaceholderAPI &cNOT detected, disabling plugin.");
			MessageUtils.log(" ");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}
		return true;
	}

	private void checkUpdates() {
		if(BLPSetting.CHECK_UPDATES.asBoolean() && BLPSetting.UPDATER_MESSAGE_CONSOLE.asBoolean())
			UpdateChecker.sendUpdate(Bukkit.getConsoleSender());
	}
}
