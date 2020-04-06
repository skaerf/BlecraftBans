package net.skaerf.blecraft.bancmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class BanCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED+"Usage: /ban <player>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player is not online!");
				}
				else {
					List<String> bans = Main.getPlugin(Main.class).getConfig().getStringList("bans");
					bans.add(target.getUniqueId().toString());
					Main.getPlugin(Main.class).getConfig().set("bans", bans);
					Main.getPlugin(Main.class).saveConfig();
					Main.getPlugin(Main.class).reloadConfig();
					System.out.println("[BlecraftBans] "+target.getName()+" was permanently banned!");
					target.kickPlayer(ChatColor.RED+"You have been permanently banned from Blecraft!\n"
							+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
					sender.sendMessage(ChatColor.RED+target.getName()+" was permanently banned from Blecraft!");
				}
			}
		}
		else {
			if (((Player)sender).hasPermission("blecrat.admin.ban")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED+"Usage: /ban <player>");
				}
				else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED+"That player is not online!");
					}
					else {
						List<String> bans = Main.getPlugin(Main.class).getConfig().getStringList("bans");
						bans.add(target.getUniqueId().toString());
						Main.getPlugin(Main.class).getConfig().set("bans", bans);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was permanently banned by " + ((Player)sender).getDisplayName()+"!");
						target.kickPlayer(ChatColor.RED+"You have been permanently banned from Blecraft!\n"
								+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
						sender.sendMessage(ChatColor.RED+target.getName()+" was permanently banned from Blecraft!");
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
