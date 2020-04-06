package net.skaerf.blecraft.mutecmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class MuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED+"Usage: /mute <name>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player could not be found!");
				}
				else {
					List<String> mutes = Main.getPlugin(Main.class).getConfig().getStringList("mutes");
					for (String s : mutes) {
						String[] array = s.split(";");
						if (array[0].contentEquals(target.getUniqueId().toString())) {
							sender.sendMessage(ChatColor.RED+"That player is already muted!");
							break;
						}
					}
					mutes.add(target.getUniqueId().toString());
					Main.getPlugin(Main.class).getConfig().set("mutes", mutes);
					Main.getPlugin(Main.class).saveConfig();
					Main.getPlugin(Main.class).reloadConfig();
					sender.sendMessage(ChatColor.RED+target.getName()+" was permanently muted!");
					System.out.println("[BlecraftBans] "+target.getName()+" was permanently muted!");
				}
			}
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.mute")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED+"Usage: /mute <name>");
				}
				else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED+"That player could not be found!");
					}
					else {
						List<String> mutes = Main.getPlugin(Main.class).getConfig().getStringList("mutes");
						for (String s : mutes) {
							String[] array = s.split(";");
							if (array[0].contentEquals(target.getUniqueId().toString())) {
								sender.sendMessage(ChatColor.RED+"That player is already muted!");
								break;
							}
						}
						mutes.add(target.getUniqueId().toString());
						Main.getPlugin(Main.class).getConfig().set("mutes", mutes);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						sender.sendMessage(ChatColor.RED+target.getName()+" was permanently muted!");
						System.out.println("[BlecraftBans] "+target.getName()+" was permanently muted by " + ((Player)sender).getDisplayName()+"!");
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
