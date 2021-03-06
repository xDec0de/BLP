package es.xdec0de.blp.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a replacer to replace parts of a string with other string, if you want to use the same replacements for multiple strings, you should 
 * create a replacer variable and apply it to as many strings as you want to <b>avoid creating multiple instances of the same replacements</b>, also, 
 * make sure that the amount of strings added to the replacer are <b>even</b>, otherwise, an exception will be thrown!<br><br>
 * 
 * The default replacer stands for a replacer with the %prefix% and %error% replaces added to it.
 * 
 * @see Replacer#Replacer(String...)
 */
public class Replacer {

	private final LinkedList<String> replaceList = new LinkedList<String>();

	/**
	 * Creates a replacer to replace parts of a string with other string, if you want to use the same replacements for multiple strings, you should 
	 * create a replacer variable and apply it to as many strings as you want to <b>avoid creating multiple instances of the same replacements</b>, also, 
	 * make sure that the amount of strings added to the replacer are <b>even</b>, otherwise, an exception will be thrown!
	 * 
	 * @param replacements The strings to be replaced, the format is "%placeholder1%", "replace1", "%placeholder2%", "replace2"...
	 * 
	 * @see #replaceAt(String)
	 * @see #replaceAt(List)
	 */
	public Replacer(String... replacements) {
		replaceList.addAll(Arrays.asList(replacements));
		if(replaceList.size() % 2 != 0)
			throw new IllegalArgumentException(replaceList.get(replaceList.size() - 1) + "does not have a replacer! Add one more element to the replacer.");
	}

	/**
	 * Adds new strings to an existing replacer, the amount of strings must also be even, note that existing replacements won't be ignored 
	 * and the new replacer won't overwrite them.
	 * 
	 * @param replacements The new strings to be replaced, the format is "%placeholder1%", "replace1", "%placeholder2%", "replace2"...
	 * 
	 * @return The old replacer with the new replacements added to it.
	 * 
	 * @throws IllegalArgumentException if the amount of strings is not even, more technically, if replaces size % 2 is not equal to 0.
	 */
	public Replacer add(String... replacements) {
		if(replacements.length % 2 != 0)
			throw new IllegalArgumentException(replacements[replacements.length -1] + "does not have a replacer! Add one more element to the replacer.");
		replaceList.addAll(Arrays.asList(replacements));
		return this;
	}

	/**
	 * Applies the replacements to the specified string, it the string is null, "null" will be returned.
	 * 
	 * @param str The string to apply the replacements to.
	 * 
	 * @return A new string with the replacements applied to it.
	 */
	public String replaceAt(String str) {
		if(str == null)
			return "null";
		String res = str;
		for(int i = 0; i <= replaceList.size() - 1; i += 2)
			res = res.replace(replaceList.get(i), replaceList.get(i + 1));
		return res;
	}
}
