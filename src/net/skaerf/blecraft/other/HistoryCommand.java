package net.skaerf.blecraft.other;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.history")) {
				
			}
			else {
				((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
			}
		}
		return true;
	}

}
