package es.xdec0de.blp.utils.files;

public enum BLPMessage {

	PREFIX("BLP.Prefix"),
	ERROR("BLP.Error"),
	NO_PERM("BLP.NoPerm"),
	
	RELOADED("Commands.Reload.Success"),
	RELOAD_USAGE("Commands.Reload.Usage"),
	
	TRUE("Placeholders.IsTrue"),
	FALSE("Placeholders.IsFalse"),
	
	UPDATE_AVAILABLE_PLAYER("Updater.Available.Player");

	private String path;

	BLPMessage(String string) {
		this.path = string;
	}

	public String getPath() {
		return path;
	}
}
