package es.xdec0de.blp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.xdec0de.blp.utils.files.BLPCfg;
import es.xdec0de.blp.utils.files.MessageUtils;
import es.xdec0de.blp.utils.files.enums.BLPMessage;
import es.xdec0de.blp.utils.files.enums.BLPSetting;

public class BLPCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sndr, Command cmd, String label, String[] args) {
		if(sndr.hasPermission(BLPCfg.getString(BLPSetting.RELOAD_PERM))) {
			if(args.length == 1) {
				BLPCfg.reload();
				MessageUtils.sendMessage(sndr, BLPMessage.RELOADED);
			} else
				MessageUtils.sendMessage(sndr, BLPMessage.RELOAD_USAGE);
		} else
			MessageUtils.sendMessage(sndr, BLPMessage.NO_PERM);
		return true;
	}
}
