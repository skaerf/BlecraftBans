package net.skaerf.blecraft.bancmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class UnbanCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED+"Usage: /unban <name>");
			}
			else {
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempbans")) {
					String[] array = str.split(":");
					if (array[0].equals(target.getUniqueId().toString())) {
						List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
						tempbans.remove(str);
						Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was unbanned!");
						sender.sendMessage(ChatColor.GREEN+target.getName()+" was unbanned!");
						return true;
					}
					
				}
				for (String str : Main.getPlugin(Main.class).getConfig().getStringList("bans")) {
					String[] array = str.split(":");
					if (array[0].equals(target.getUniqueId().toString())) {
						List<String> bans = Main.getPlugin(Main.class).getConfig().getStringList("bans");
						bans.remove(str);
						Main.getPlugin(Main.class).getConfig().set("bans", bans);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was unbanned!");
						sender.sendMessage(ChatColor.GREEN+target.getName()+" was unbanned!");
						return true;
					}
					
				}
				sender.sendMessage(ChatColor.RED+"That player is not banned!");
			}
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.unban")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED+"Usage: /unban <name>");
				}
				else {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempbans")) {
						String[] array = str.split(":");
						if (array[0].equals(target.getUniqueId().toString())) {
							List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
							tempbans.remove(str);
							Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
							Main.getPlugin(Main.class).saveConfig();
							Main.getPlugin(Main.class).reloadConfig();
							System.out.println("[BlecraftBans] "+target.getName()+" was unbanned by " + ((Player)sender).getDisplayName()+"!");
							sender.sendMessage(ChatColor.GREEN+target.getName()+" was unbanned!");
							return true;
						}
						
					}
					for (String str : Main.getPlugin(Main.class).getConfig().getStringList("bans")) {
						String[] array = str.split(":");
						if (array[0].equals(target.getUniqueId().toString())) {
							List<String> bans = Main.getPlugin(Main.class).getConfig().getStringList("bans");
							bans.remove(str);
							Main.getPlugin(Main.class).getConfig().set("bans", bans);
							Main.getPlugin(Main.class).saveConfig();
							Main.getPlugin(Main.class).reloadConfig();
							System.out.println("[BlecraftBans] "+target.getName()+" was unbanned by " + ((Player)sender).getDisplayName()+"!");
							sender.sendMessage(ChatColor.GREEN+target.getName()+" was unbanned!");
							return true;
						}
						
					}
					sender.sendMessage(ChatColor.RED+"That player is not banned!");
				}
		}
			else {
				((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
			}
	}
		return true;
	}
}
