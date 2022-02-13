package es.xdec0de.blp.utils.files;

import org.bukkit.command.CommandSender;

import es.xdec0de.blp.utils.Replacer;

public enum BLPMessage {

	PREFIX("BLP.Prefix"),
	ERROR("BLP.Error"),
	NO_PERM("BLP.NoPerm"),
	
	RELOADED("Commands.BLP.Reload.Success"),

	BLP_INVALID_ARG("Commands.BLP.InvalidArg"),
	BLP_USAGE("Commands.BLP.Usage"),
	
	TRUE("Placeholders.IsTrue"),
	FALSE("Placeholders.IsFalse"),
	INVALID_TOP("Placeholders.InvalidTop"),
	
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

	// Getters //

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}.
	 * 
	 * @return The message as a string, with colors and the default replacer applied to it.
	 */
	public String asString() {
		return MessageUtils.applyColor(new Replacer("%prefix%", MessageUtils.prefix, "%error%", MessageUtils.errorPrefix).replaceAt(BLPMessages.get().getString(path)));
	}

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}, also, the specified replacer is added to the default replacer.
	 * 
	 * @param replacer The replacer to apply.
	 * 
	 * @return The message as a string, with colors and replacer applied to it.
	 */
	public String asString(Replacer replacer) {
		return MessageUtils.applyColor(new Replacer("%prefix%", MessageUtils.prefix, "%error%", MessageUtils.errorPrefix).add(replacer).replaceAt(BLPMessages.get().getString(path)));
	}

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}, also, a new replacer made with the 
	 * specified strings is added to the default replacer.
	 * 
	 * @param replacements The replacements to apply.
	 * 
	 * @return The message as a string, with colors and replacer applied to it.
	 */
	public String asString(String... replacements) {
		return MessageUtils.applyColor(new Replacer("%prefix%", MessageUtils.prefix, "%error%", MessageUtils.errorPrefix).add(replacements).replaceAt(BLPMessages.get().getString(path)));
	}

	// Senders //

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, empty messages will be ignored and the message wont be sent.
	 * 
	 * @param sender The sender that will receive the message.
	 * 
	 * @see #getMessage(BLPMessage)
	 */
	public void send(CommandSender sender) {
		String send = asString();
		if(!send.isEmpty())
			sender.sendMessage(send);
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, also, 
	 * the specified replacer is added to the default replacer, empty messages will be ignored and the message wont be sent.
	 * 
	 * @param sender The sender that will receive the message.
	 * @param replacer The replacer to apply.
	 * 
	 * @see #getMessage(BLPMessage, Replacer)
	 */
	public void send(CommandSender sender, Replacer replacer) {
		String send = asString(replacer);
		if(!send.isEmpty())
			sender.sendMessage(send);
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, also, a new replacer made with 
	 * the specified strings is added to the default replacer, empty messages will be ignored and the message wont be sent.
	 * 
	 * @param sender The sender that will receive the message.
	 * @param replacements The replacements to apply.
	 * 
	 * @see #getMessage(BLPMessage, String...)
	 */
	public void send(CommandSender sender, String... replacements) {
		String send = asString(replacements);
		if(!send.isEmpty())
			sender.sendMessage(send);
	}
}
