package net.skaerf.blecraft.ipbans;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnIPBanCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.unipban")) {
				
			}
		}
		return true;
	}

}
