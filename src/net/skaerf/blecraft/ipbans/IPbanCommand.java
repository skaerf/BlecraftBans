package net.skaerf.blecraft.ipbans;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.blecraft.Main;

public class IPbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED+"Usage: /ipban <name>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player could not be found!");
				}
				else {
					List<String> ipbans = Main.getPlugin(Main.class).getConfig().getStringList("ipbans");
					String port = target.getAddress().toString();
					port.replace("/", "");
					String[] ip = port.split(":");
					String ipname = ip[0]+":"+target.getName();
					ipbans.add(ipname);
					Main.getPlugin(Main.class).getConfig().set("ipbans", ipbans);
					Main.getPlugin(Main.class).saveConfig();
					Main.getPlugin(Main.class).reloadConfig();
					System.out.println("[BlecraftBans] "+target.getName()+" was permanently IP-banned!");
					target.kickPlayer(ChatColor.RED+"You have been permanently IP-banned from Blecraft!\n"
							+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
					sender.sendMessage(ChatColor.RED+target.getName()+" was permenently IP-banned from Blecraft!");
				}
			}
		}
		else {
			if (((Player)sender).hasPermission("blecraft.admin.ipban")) {
				if (args.length != 1) {
					sender.sendMessage(ChatColor.RED+"Usage: /ipban <name>");
				}
				else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED+"That player could not be found!");
					}
					else {
						List<String> ipbans = Main.getPlugin(Main.class).getConfig().getStringList("ipbans");
						String port = target.getAddress().toString();
						port.replace("/", "");
						String[] ip = port.split(":");
						ipbans.add(ip[0]);
						Main.getPlugin(Main.class).getConfig().set("ipbans", ipbans);
						Main.getPlugin(Main.class).saveConfig();
						Main.getPlugin(Main.class).reloadConfig();
						System.out.println("[BlecraftBans] "+target.getName()+" was permanently IP-banned!");
						target.kickPlayer(ChatColor.RED+"You have been permanently IP-banned from Blecraft!\n"
								+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
						sender.sendMessage(ChatColor.RED+target.getName()+" was permenently IP-banned from Blecraft!");
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
