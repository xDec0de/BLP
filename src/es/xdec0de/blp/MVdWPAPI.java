package es.xdec0de.blp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.robin.battlelevels.api.BattleLevelsAPI;
import net.md_5.bungee.api.ChatColor;

class MVdWPAPI {

	protected MVdWPAPI(Plugin mvdwpapi) {
		PlaceholderAPI.registerPlaceholder(BLP.getInstance(), "neededfornext", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player p = e.getPlayer();
				if(p == null)
					return "";
				return String.valueOf(BattleLevelsAPI.getNeededForNext(p.getUniqueId()));
			}
		});
		PlaceholderAPI.registerPlaceholder(BLP.getInstance(), "globalboosterenabled", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player p = e.getPlayer();
				if(p == null)
					return "";
				if (BattleLevelsAPI.isGlobalBoosterEnabled())
					return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsTrue")); 
				return ChatColor.translateAlternateColorCodes('&', BLPCfg.get().getString("PlaceHolders.IsFalse"));
			}
		});
	}
}
