package es.xdec0de.blp.utils.files.enums;

public enum BLPMessage {

	PREFIX("BLP.Prefix"),
	ERROR("BLP.Error"),
	NO_PERM("BLP.NoPerm"),
	
	RELOADED("Commands.Reload.Success"),
	RELOAD_USAGE("Commands.Reload.Usage"),
	
	TRUE("Placeholders.IsTrue"),
	FALSE("Placeholders.IsFalse"),
	
	UPDATE_AVAILABLE_PLAYER("Updater.Available.Player"),
	UPDATE_AVAILABLE_CONSOLE("Updater.Available.Console"),

	UPDATE_LATEST_PLAYER("Updater.Latest.Player"),
	UPDATE_LATEST_CONSOLE("Updater.Latest.Console");

	private String path;

	BLPMessage(String string) {
		this.path = string;
	}

	public String getPath() {
		return path;
	}
}
