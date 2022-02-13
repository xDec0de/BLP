package es.xdec0de.blp.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import es.xdec0de.blp.utils.files.BLPSetting;

public class BLPTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> tab = new ArrayList<String>();
		if(args.length == 1) {
			if(BLPSetting.RELOAD_PERMISSION.asPermission(sender, false))
				tab.add("reload");
			if(BLPSetting.UPDATE_CHECK_PERMISSION.asPermission(sender, false))
				tab.add("checkupdates");
		}
		return tab;
	}
}
