package net.skaerf.blecraft.other;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class BanlistCommand implements CommandExecutor {

	@SuppressWarnings({ "null", "unused"})
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			List<String> bans = null;
			List<String> tempbans = null;
			for (String s : Main.getPlugin(Main.class).getConfig().getStringList("bans")) {
				bans.add(s);
			}
			for (String s : Main.getPlugin(Main.class).getConfig().getStringList("tempbans")) {
				String[] str = s.split(":");
				tempbans.add(s);
			}
			sender.sendMessage(ChatColor.RED+"Bans: "+bans+"\nTempbans: "+tempbans);
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.banlist")) {
				
			}
			else {
				((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
			}
		}
		return true;
	}

}
