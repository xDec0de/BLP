package es.xdec0de.blp.utils.files;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.xdec0de.blp.utils.Replacer;

public class MessageUtils {

	private final static Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");
	static String prefix = "";
	static String errorPrefix = "";

	// String utility //

	/**
	 * Applies color to the specified string, with hexadecimal color codes support.
	 * 
	 * @param string The string to color
	 * 
	 * @return The string, colored
	 */
	public static String applyColor(String string) {
		if(string == null)
			return "null";
		char COLOR_CHAR = '\u00A7';
		Matcher matcher = HEX_PATTERN.matcher(string);
		StringBuffer buffer = new StringBuffer(string.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(1);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x"
					+ COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
					+ COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
					+ COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));
		}
		return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
	}

	/**
	 * Applies color to the specified list of strings, with hexadecimal color codes support.
	 * 
	 * @param list The string list to color
	 * 
	 * @return The string list, colored
	 */
	public static List<String> applyColor(List<String> list) {
		if(list == null)
			return new LinkedList<String>();
		List<String> res = new LinkedList<String>();
		for(String str : list)
			res.add(applyColor(str));
		return res;
	}

	// Loggers //

	/**
	 * Sends the specified string to the console, if the string is null, "null" will be sent, if the string is empty, 
	 * nothing will be done.
	 * 
	 * @param str The string to send
	 */
	public static void log(String str) {
		if(str == null)
			str = "null";
		if(!str.isEmpty())
			Bukkit.getConsoleSender().sendMessage(str);
	}

	/**
	 * Applies color ({@link #applyColor(String)}) to the specified string and then sends it to the console, if the string is null, "null" will be sent, 
	 * if the string is empty, nothing will be done.
	 * 
	 * @param str The string to send
	 */
	public static void logCol(String str) {
		if(!str.isEmpty())
			Bukkit.getConsoleSender().sendMessage(applyColor(new Replacer("%prefix%", prefix, "%error%", errorPrefix).replaceAt(str)));
		// It's not necessary to null check as applyColor does it.
	}

	/**
	 * Applies color ({@link #applyColor(String)}) and replacements ({@link Replacer}) to the specified string and then sends it to the console, 
	 * if the string is null, "null" will be sent, if the string is empty, nothing will be done.
	 * 
	 * @param str The string to send ({@link #applyColor(String)})
	 * @replacements The replacements to apply to the string ({@link Replacer})
	 */
	public static void logCol(String str, String... replacements) {
		if(!str.isEmpty())
			Bukkit.getConsoleSender().sendMessage(applyColor(new Replacer("%prefix%", prefix, "%error%", errorPrefix).add(replacements).replaceAt(str)));
		// It's not necessary to null check as applyColor does it.
	}

	// Message getters //

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it
	 * 
	 * @param msg The message to get.
	 * 
	 * @return The message as a string, with colors and the default replacer applied to it.
	 */
	public static String getMessage(BLPMessage msg) {
		return applyColor(new Replacer("%prefix%", prefix, "%error%", errorPrefix).replaceAt(BLPMessages.get().getString(msg.getPath())));
	}

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, the specified replacer is added to the default replacer.
	 * 
	 * @param msg The message to get.
	 * @param replacer The replacer to apply.
	 * 
	 * @return The message as a string, with colors and replacer applied to it.
	 */
	public static String getMessage(BLPMessage msg, Replacer replacer) {
		return applyColor(new Replacer("%prefix%", prefix, "%error%", errorPrefix).add(replacer).replaceAt(BLPMessages.get().getString(msg.getPath())));
	}

	/**
	 * Gets a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, a replacer new replacer made with the specified strings is added to the default replacer.
	 * 
	 * @param msg The message to get.
	 * @param replacements The replacements to apply.
	 * 
	 * @return The message as a string, with colors and replacer applied to it.
	 */
	public static String getMessage(BLPMessage msg, String... replacements) {
		return applyColor(new Replacer("%prefix%", prefix, "%error%", errorPrefix).add(replacements).replaceAt(BLPMessages.get().getString(msg.getPath())));
	}

	// Message senders //

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it.
	 * 
	 * @param sender The sender that will receive the message.
	 * @param msg The message to get.
	 * 
	 * @see #getMessage(BLPMessage)
	 */
	public static void sendMessage(CommandSender sender, BLPMessage msg) {
		sender.sendMessage(getMessage(msg));
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, the specified replacer is added to the default replacer.
	 * 
	 * @param sender The sender that will receive the message.
	 * @param msg The message to get.
	 * @param replacer The replacer to apply.
	 * 
	 * @see #getMessage(BLPMessage, Replacer)
	 */
	public static void sendMessage(CommandSender sender, BLPMessage msg, Replacer replacer) {
		sender.sendMessage(getMessage(msg, replacer));
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, a replacer new replacer made with the specified strings is added to the default replacer.
	 * 
	 * @param sender The sender that will receive the message.
	 * @param msg The message to get.
	 * @param replacements The replacements to apply.
	 * 
	 * @see #getMessage(BLPMessage, String...)
	 */
	public static void sendMessage(CommandSender sender, BLPMessage msg, String... replacements) {
		sender.sendMessage(getMessage(msg, replacements));
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it.
	 * 
	 * @param player The player that will receive the message.
	 * @param msg The message to get.
	 * 
	 * @see #getMessage(BLPMessage)
	 */
	public static void sendMessage(Player player, BLPMessage msg) {
		player.sendMessage(getMessage(msg));
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, the specified replacer is added to the default replacer.
	 * 
	 * @param player The player that will receive the message.
	 * @param msg The message to get.
	 * @param replacer The replacer to apply.
	 * 
	 * @see #getMessage(BLPMessage, Replacer)
	 */
	public static void sendMessage(Player player, BLPMessage msg, Replacer replacer) {
		player.sendMessage(getMessage(msg, replacer));
	}

	/**
	 * Sends a message with colors {@link #applyColor(String)} and the default {@link Replacer}, which contains the %prefix% and %error% placeholder, applied to it, 
	 * also, a replacer new replacer made with the specified strings is added to the default replacer.
	 * 
	 * @param player The player that will receive the message.
	 * @param msg The message to get.
	 * @param replacements The replacements to apply.
	 * 
	 * @see #getMessage(BLPMessage, String...)
	 */
	public static void sendMessage(Player player, BLPMessage msg, String... replacements) {
		player.sendMessage(getMessage(msg, replacements));
	}
}