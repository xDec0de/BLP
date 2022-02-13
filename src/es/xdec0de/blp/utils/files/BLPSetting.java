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

	/**
	 * Gets the path on config.yml to the setting.
	 * 
	 * @return The path of the setting.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the setting as a string.
	 * 
	 * @return The setting as a string.
	 */
	public String asString() {
		return BLPCfg.cfg.getString(path);
	}

	/**
	 * Gets the setting as a boolean.
	 * 
	 * @return The setting as a boolean.
	 */
	public boolean asBoolean() {
		return BLPCfg.cfg.getBoolean(path);
	}

	/**
	 * Checks if <b>sender</b> has permission to the setting.
	 * 
	 * @param sender The sender to check.
	 * @param message Whether to send {@link BLPMessage#NO_PERM} to the sender if it doesn't have permission or not.
	 * 
	 * @return true if the sender has permission, false otherwise.
	 */
	public boolean asPermission(CommandSender sender, boolean message) {
		if(sender.hasPermission(BLPCfg.cfg.getString(path)))
			return true;
		if(message)
			BLPMessage.NO_PERM.send(sender);
		return false;
	}
}
