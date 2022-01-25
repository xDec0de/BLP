package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.robin.battlelevels.api.BattleLevelsAPI;
import net.md_5.bungee.api.ChatColor;

class PAPI extends PlaceholderExpansion {
	
	protected PAPI(Plugin papi) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e- &bPlaceHolderAPI &7detected (&av" + papi.getDescription().getVersion() + "&7)"));
		register();
	}
	
	public boolean persist() {
		return true;
	}

	public boolean canRegister() {
		return true;
	}

	public String getAuthor() {
		return BLP.getInstance().getDescription().getAuthors().toString();
	}

	public String getIdentifier() {
		return BLP.getInstance().getDescription().getName();
	}

	public String getVersion() {
		return BLP.getInstance().getDescription().getVersion();
	}

	public String onPlaceholderRequest(Player player, String identifier) {
		if (player == null)
			return ""; 
		String str = identifier.toLowerCase();
		int pos = Integer.parseInt(str.substring(str.lastIndexOf("#") + 1));
		if(pos == -1) {
			switch (str) {
			case "neededfornext":
				return String.valueOf(BattleLevelsAPI.getNeededForNext(player.getUniqueId()));
			case "globalboosterenabled":
				if (BattleLevelsAPI.isGlobalBoosterEnabled())
					return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsTrue")); 
				return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsFalse"));
			case "deaths":
				return String.valueOf(BattleLevelsAPI.getDeaths(player.getUniqueId()));
			case "killstreak":
				return String.valueOf(BattleLevelsAPI.getKillstreak(player.getUniqueId()));
			case "bar":
				return BattleLevelsAPI.getProgressBar(player.getUniqueId());
			case "kdr":
				return String.valueOf(BattleLevelsAPI.getKdr(player.getUniqueId()));
			case "booster":
				return String.valueOf(BattleLevelsAPI.getBoosterInMinutes(player.getUniqueId()));
			case "kills":
				return String.valueOf(BattleLevelsAPI.getKills(player.getUniqueId()));
			case "level":
				return String.valueOf(BattleLevelsAPI.getLevel(player.getUniqueId()));
			case "score":
				return String.valueOf(BattleLevelsAPI.getScore(player.getUniqueId()));
			case "topstreak":
				return String.valueOf(BattleLevelsAPI.getTopKillstreak(player.getUniqueId()));
			case "boosterenabled":
				if(BattleLevelsAPI.hasBooster(player.getUniqueId()))
					return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsTrue")); 
				return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsFalse"));
			case "neededfornextremaining":
				return String.valueOf(BattleLevelsAPI.getNeededForNextRemaining(player.getUniqueId()));
			case "globalbooster":
				return String.valueOf(BattleLevelsAPI.getGlobalBoosterInMinutes());
			}
		} else {
			str = str.substring(0, str.lastIndexOf("#") - 1);
			switch (str) {
			case "toplevels_name":
				return BattleLevelsAPI.getTopLevels().get(pos).getKey();
			}
		}
		return null;
	}
	
	private int getLastInt(String str) {
		int lastPos;
		for(lastPos = str.length()-1; lastPos >= 0; --lastPos)
			if(!Character.isDigit(str.charAt(lastPos))) break;
		lastPos++;
		if(lastPos == str.length()) return -1;
		return Integer.parseInt(str.substring(lastPos));
	}
}
