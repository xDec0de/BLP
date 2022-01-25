package es.xdec0de.blp.utils.files;

public enum BLPSetting {

	CHECK_UPDATES("Updater.Check"),
	UPDATER_MESSAGE_PLAYER("Updater.MessagePlayer"),
	UPDATER_PERMISSION("Updater.Permission"),

	RELOAD_PERM("Reload.Permission");

	private String path;

	BLPSetting(String string) {
		this.path = string;
	}

	public String getPath() {
		return path;
	}
}
