package net.skaerf.blecraft.bancmds;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class TempbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length != 3) {
				sender.sendMessage(ChatColor.RED+"Usage: /tempban <name> <time> <timeunit>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player could not be found!");
					return true;
				}
				try {
					Long.parseLong(args[1]);
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED+args[2]+" is not a valid time!");
					return true;
				}
				try {
					TimeUnit.valueOf(args[2].toUpperCase());
				}
				catch (Exception e) {
					sender.sendMessage(ChatColor.RED+args[2]+" is not a valid unit of time!");
					return true;
				}
				for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempbans")) {
					String[] array = str.split(":");
					if (!target.getUniqueId().toString().equalsIgnoreCase(array[0])) {
						continue;
					}
					List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
					tempbans.remove(str);
					TimeUnit timeunit = TimeUnit.valueOf(args[2].toUpperCase());
					long currentTime = System.currentTimeMillis();
					long toMillis = timeunit.toMillis(Long.parseLong(args[1]));
					long time = currentTime + toMillis;
					tempbans.add(target.getUniqueId().toString()+":"+time);
					Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
					Main.getPlugin(Main.class).saveConfig();
					Main.getPlugin(Main.class).reloadConfig();
					if (target.isOnline()) target.kickPlayer("backup");
					sender.sendMessage(ChatColor.RED+"That player was already temporarily banned, so it was updated to the new length.");
					return true;
				}
				List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
				TimeUnit timeunit = TimeUnit.valueOf(args[2].toUpperCase());
				long currentTime = System.currentTimeMillis();
				long toMillis = timeunit.toMillis(Long.parseLong(args[1]));
				long time = currentTime + toMillis;
				tempbans.add(target.getUniqueId().toString()+":"+time);
				Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
				Main.getPlugin(Main.class).saveConfig();
				Main.getPlugin(Main.class).reloadConfig();
				System.out.println("[BlecraftBans] "+target.getName()+" was temporarily banned by " + ((Player)sender).getDisplayName()+"!");
				target.kickPlayer(ChatColor.RED+"You have been temporarily banned from Blecraft!");
				sender.sendMessage(ChatColor.RED+target.getName()+" was temporarily banned!");
				return true;
		}
	}
	else {
		if (((Player)sender).hasPermission("blecraft.admin.tempban")) {
			if (args.length != 3) {
				sender.sendMessage(ChatColor.RED+"Usage: /tempban <name> <time> <timeunit>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player could not be found!");
					return true;
				}
				try {
					Long.parseLong(args[1]);
				}
				catch (Exception ex) {
					sender.sendMessage(ChatColor.RED+args[2]+" is not a valid time!");
					return true;
				}
				try {
					TimeUnit.valueOf(args[2].toUpperCase());
				}
				catch (Exception e) {
					sender.sendMessage(ChatColor.RED+args[2]+" is not a valid unit of time!");
					return true;
				}
					for (String str : Main.getPlugin(Main.class).getConfig().getStringList("tempbans")) {
						String[] array = str.split(":");
						if (!target.getUniqueId().toString().equalsIgnoreCase(array[0])) {
							continue;
						}
						List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
						tempbans.remove(str);
						TimeUnit timeunit = TimeUnit.valueOf(args[2].toUpperCase());
						long currentTime = System.currentTimeMillis();
						long toMillis = timeunit.toMillis(Long.parseLong(args[1]));
						long time = currentTime + toMillis;
						tempbans.add(target.getUniqueId().toString()+":"+time);
						Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						sender.sendMessage(ChatColor.RED+"That player was already temporarily banned, so it was updated to the new length.");
						return true;
					}
					List<String> tempbans = Main.getPlugin(Main.class).getConfig().getStringList("tempbans");
					TimeUnit timeunit = TimeUnit.valueOf(args[2].toUpperCase());
					long currentTime = System.currentTimeMillis();
					long toMillis = timeunit.toMillis(Long.parseLong(args[1]));
					long time = currentTime + toMillis;
					tempbans.add(target.getUniqueId().toString()+":"+time);
					Main.getPlugin(Main.class).getConfig().set("tempbans", tempbans);
					Main.getPlugin(Main.class).saveConfig();
					Main.getPlugin(Main.class).reloadConfig();
					System.out.println("[BlecraftBans] "+target.getName()+" was temporarily banned by " + ((Player)sender).getDisplayName()+"!");
					target.kickPlayer(ChatColor.RED+"You have been temporarily banned from Blecraft!");
					sender.sendMessage(ChatColor.RED+target.getName()+" was temporarily banned!");
					return true;
			}
		}
		else {
			((Player)sender).sendMessage(ChatColor.RED+"You don't have permission!");
		}
	}
		return true;
	}
}
