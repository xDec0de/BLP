package es.xdec0de.blp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.xdec0de.blp.utils.UpdateChecker;
import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.MessageUtils;
import es.xdec0de.blp.utils.files.enums.BLPMessage;
import es.xdec0de.blp.utils.files.enums.BLPSetting;

public class BLPCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			switch(args[0].toLowerCase()) { // I don't like java 8 switches :(
			case "reload": case "rl":
				if(sndr.hasPermission(BLPCfg.getString(BLPSetting.RELOAD_PERM))) {
					BLPCfg.reload();
					MessageUtils.sendMessage(sndr, BLPMessage.RELOADED);
				} else
					MessageUtils.sendMessage(sndr, BLPMessage.NO_PERM);
				break;
			case "checkupdate": case "checkupdates":
				if(sndr.hasPermission(BLPCfg.getString(BLPSetting.UPDATE_CHECK_PERMISSION)))
					UpdateChecker.sendUpdate(sndr);
				else
					MessageUtils.sendMessage(sndr, BLPMessage.NO_PERM);
				break;
			default:
				MessageUtils.sendMessage(sndr, BLPMessage.BLP_INVALID_ARG);
				break;
			}
		} else
			MessageUtils.sendMessage(sndr, BLPMessage.BLP_USAGE);
		return true;
	}
}
