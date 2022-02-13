package es.xdec0de.blp.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.enums.BLPSetting;

public class BLPTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> tab = new ArrayList<String>();
		if(args.length == 1) {
			if(BLPCfg.hasPermission(BLPSetting.RELOAD_PERMISSION, sender, false))
				tab.add("reload");
			if(BLPCfg.hasPermission(BLPSetting.UPDATE_CHECK_PERMISSION, sender, false))
				tab.add("checkupdates");
		}
		return tab;
	}
}
