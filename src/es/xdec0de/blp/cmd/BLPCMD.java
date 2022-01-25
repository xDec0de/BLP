package es.xdec0de.blp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BLPCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
		if(sndr.hasPermission(BLPCfg.get().getString("Permissions.Reload"))) {
			if(args.length == 1) {
				BLPCfg.reload();
				sendMsg(sndr, "Commands.Reload.Success");
			} else {
				sendMsg(sndr, "Commands.Reload.Usage");
			} 
		} else {
			sendMsg(sndr, "BLPAPI.NoPerm");
		} 
		return true;
	}

	private void sendMsg(CommandSender sndr, String path) {
		sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString(path).replaceAll("%prefix%", BLPCfg.get().getString("BLPAPI.Prefix"))));
	}
}
