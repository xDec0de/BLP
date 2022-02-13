package es.xdec0de.blp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.xdec0de.blp.utils.UpdateChecker;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.BLPMessage;
import es.xdec0de.blp.utils.files.BLPMessages;
import es.xdec0de.blp.utils.files.BLPSetting;

public class BLPCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			switch(args[0].toLowerCase()) { // I don't like java 8 switches :(
			case "reload": case "rl":
				if(BLPSetting.RELOAD_PERMISSION.asPermission(sender, true)) {
					BLPCfg.setup(true);
					BLPMessages.setup(true); // setup is called instead of reload as setup already uses reload but also creates the file if it doesn't exist.
					BLPMessage.RELOADED.send(sender);
				}
				break;
			case "checkupdate": case "checkupdates":
				if(BLPSetting.UPDATE_CHECK_PERMISSION.asPermission(sender, true))
					UpdateChecker.sendUpdate(sender);
				break;
			default:
				BLPMessage.BLP_INVALID_ARG.send(sender);
				break;
			}
		} else
			BLPMessage.BLP_USAGE.send(sender);
		return true;
	}
}
