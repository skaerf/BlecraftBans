package net.skaerf.blecraft.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED+"Usage: /kick <name>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player could not be found!");
				}
				else {
					target.kickPlayer(ChatColor.RED+"You were kicked from Blecraft!");
					System.out.println(ChatColor.RED+target.getName()+" was kicked from Blecraft!");
				}
			}
			
		}
		else {
			if (((Player)sender).hasPermission("blecraft.bans.kick")) {
				if (args.length != 1) {
					sender.sendMessage(ChatColor.RED+"Usage: /kick <name>");
				}
				else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED+"That player could not be found!");
					}
					else {
						target.kickPlayer(ChatColor.RED+"You were kicked from Blecraft!");
						System.out.println(ChatColor.RED+target.getName()+" was kicked from Blecraft by "+sender.getName()+"!");
					}
				}
			}
			else {
				((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
			}
		}
		return true;
	}

}
