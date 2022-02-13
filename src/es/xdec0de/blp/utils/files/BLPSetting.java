package es.xdec0de.blp.utils.files;

import org.bukkit.command.CommandSender;

public enum BLPSetting {

	CHECK_UPDATES("Updater.Check"),
	UPDATER_MESSAGE_PLAYER("Updater.Message.Player"),
	UPDATER_MESSAGE_CONSOLE("Updater.Message.Console"),
	UPDATE_NOTIFY_PERMISSION("Updater.Permission.Notify"),
	UPDATE_CHECK_PERMISSION("Updater.Permission.Check"),

	RELOAD_PERMISSION("Reload.Permission");

	private String path;

	BLPSetting(String string) {
		this.path = string;
	}

	public String getPath() {
		return path;
	}

	public String asString() {
		return BLPCfg.cfg.getString(path);
	}

	public boolean asBoolean() {
		return BLPCfg.cfg.getBoolean(path);
	}

	public boolean asPermission(CommandSender sender, boolean message) {
		if(sender.hasPermission(BLPCfg.cfg.getString(path)))
			return true;
		if(message)
			MessageUtils.sendMessage(sender, BLPMessage.NO_PERM);
		return false;
	}
}
