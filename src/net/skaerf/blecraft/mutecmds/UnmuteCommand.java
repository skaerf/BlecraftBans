package net.skaerf.blecraft.mutecmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class UnmuteCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED+"Usage: /unmute <name>");
			}
			else {
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempmutes")) {
					String[] array = str.split(":");
					if (array[0].equals(target.getUniqueId().toString())) {
						List<String> tempmutes = Main.getPlugin(Main.class).getConfig().getStringList("tempmutes");
						tempmutes.remove(str);
						Main.getPlugin(Main.class).getConfig().set("tempmutes", tempmutes);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was unmuted!");
						sender.sendMessage(ChatColor.GREEN+target.getName()+" was unmuted!");
						return true;
					}
					
				}
				for (String str : Main.getPlugin(Main.class).getConfig().getStringList("mutes")) {
					String[] array = str.split(":");
					if (array[0].equals(target.getUniqueId().toString())) {
						List<String> mutes = Main.getPlugin(Main.class).getConfig().getStringList("mutes");
						mutes.remove(str);
						Main.getPlugin(Main.class).getConfig().set("mutes", mutes);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was unmuted!");
						sender.sendMessage(ChatColor.GREEN+target.getName()+" was unmuted!");
						return true;
					}
					
				}
				sender.sendMessage(ChatColor.RED+"That player is not muted!");
			}
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.unmute")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED+"Usage: /unmute <name>");
				}
				else {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempmutes")) {
						String[] array = str.split(":");
						if (array[0].equals(target.getUniqueId().toString())) {
							List<String> tempmutes = Main.getPlugin(Main.class).getConfig().getStringList("tempmutes");
							tempmutes.remove(str);
							Main.getPlugin(Main.class).getConfig().set("tempmutes", tempmutes);
							Main.getPlugin(Main.class).saveConfig();
							Main.getPlugin(Main.class).reloadConfig();
							System.out.println("[BlecraftBans] "+target.getName()+" was unmuted by " + ((Player)sender).getDisplayName()+"!");
							sender.sendMessage(ChatColor.GREEN+target.getName()+" was unmuted!");
							return true;
						}
						
					}
					for (String str : Main.getPlugin(Main.class).getConfig().getStringList("mutes")) {
						String[] array = str.split(":");
						if (array[0].equals(target.getUniqueId().toString())) {
							List<String> mutes = Main.getPlugin(Main.class).getConfig().getStringList("mutes");
							mutes.remove(str);
							Main.getPlugin(Main.class).getConfig().set("mutes", mutes);
							Main.getPlugin(Main.class).saveConfig();
							Main.getPlugin(Main.class).reloadConfig();
							System.out.println("[BlecraftBans] "+target.getName()+" was unmuted by " + ((Player)sender).getDisplayName()+"!");
							sender.sendMessage(ChatColor.GREEN+target.getName()+" was unmuted!");
							return true;
						}
						
					}
					sender.sendMessage(ChatColor.RED+"That player is not muted!");
				}
		}
			else {
				((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
			}
	}
		return true;
	}
}
