package net.skaerf.blecraft;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.skaerf.blecraft.bancmds.BanCommand;
import net.skaerf.blecraft.bancmds.TempbanCommand;
import net.skaerf.blecraft.bancmds.UnbanCommand;
import net.skaerf.blecraft.ipbans.IPbanCommand;
import net.skaerf.blecraft.mutecmds.MuteCommand;
import net.skaerf.blecraft.mutecmds.TempmuteCommand;
import net.skaerf.blecraft.mutecmds.UnmuteCommand;
import net.skaerf.blecraft.other.BanlistCommand;
import net.skaerf.blecraft.other.HistoryCommand;
import net.skaerf.blecraft.other.KickCommand;


public class Main extends JavaPlugin implements Listener {
	
	
	@Override
	public void onEnable() {
		System.out.println("[BlecraftBans] Booting");
		System.out.println("[BlecraftBans] Made by skaerf#6400");
		System.out.println("[BlecraftBans] My only contribution to Blecraft :D");
		this.getCommand("ban").setExecutor(new BanCommand());
		this.getCommand("tempban").setExecutor(new TempbanCommand());
		this.getCommand("unban").setExecutor(new UnbanCommand());
		this.getCommand("kick").setExecutor(new KickCommand());
		this.getCommand("mute").setExecutor(new MuteCommand());
		this.getCommand("tempmute").setExecutor(new TempmuteCommand());
		this.getCommand("unmute").setExecutor(new UnmuteCommand());
		this.getCommand("history").setExecutor(new HistoryCommand());
		this.getCommand("banlist").setExecutor(new BanlistCommand());
		this.getCommand("ipban").setExecutor(new IPbanCommand());
		getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		this.reloadConfig();
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		// add ip scanner - check for alts
		String port = event.getPlayer().getAddress().toString();
		String[] ip = port.split(":");
		if (this.getConfig().getStringList("ipbans").contains(ip[0])) {
			event.getPlayer().kickPlayer(ChatColor.RED+"You have been permanently IP-banned from Blecraft!\n"
					+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
			return;
		}
		else if (this.getConfig().getStringList("bans").contains(event.getPlayer().getUniqueId().toString())) {
			event.getPlayer().kickPlayer(ChatColor.RED+"You have been permanently banned from Blecraft!\n"
					+ "To appeal, please join our Discord server at https://discord.gg/FbTdnnn and contact a developer.");
			return;
		}
		else {
			String x = "";
			for (String s : this.getConfig().getStringList("tempbans")) {
				String[] array = s.split(":");
				if (array[0].contentEquals(event.getPlayer().getUniqueId().toString())) {
					long time = Long.parseLong(array[1]) - System.currentTimeMillis();
					double hours = TimeUnit.MILLISECONDS.toHours(time);
					if (System.currentTimeMillis() < Long.parseLong(array[1])) {
						if (hours > 1) {
							event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes(
									'&', "&c&lYou are temporarily banned from Blecraft!"
											+ "\nYou will be unbanned in: " 
											+ hours + " hours."));
						}
						else {
							event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes(
									'&', "&c&lYou are temporarily banned from Blecraft!"
											+ "\nYou will be unbanned within the next hour."));
						}
						x = s;
					break;
					}
				}
				else {
					List<String> playerbanned = this.getConfig().getStringList("tempbans");
					playerbanned.remove(x);
					this.getConfig().set("tempbans", playerbanned);
					this.saveConfig();
					this.reloadConfig();
				}
			}
		}
		}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if (this.getConfig().getStringList("mutes").contains(event.getPlayer().getUniqueId().toString())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED+"You are permanently muted!");
		}
		for (String s : this.getConfig().getStringList("tempmutes")) {
			String[] array = s.split(":");
			if (array[0].contentEquals(event.getPlayer().getUniqueId().toString())) {
				if (System.currentTimeMillis() < Long.parseLong(array[1])) {
					event.getPlayer().sendMessage(ChatColor.RED+"You are temporarily muted!");
					event.setCancelled(true);
				}
				else {
					List<String> tempmutes = this.getConfig().getStringList("tempmutes");
					tempmutes.remove(s);
					this.getConfig().set("tempmutes", tempmutes);
					this.saveConfig();
					this.reloadConfig();
				}
			}
		}
	}
	@EventHandler
	public void cmdProcess(PlayerCommandPreprocessEvent event) {
		if (this.getConfig().getStringList("mutes").contains(event.getPlayer().getUniqueId().toString())) {
			//for (String allowed : Main.getPlugin(Main.class).getConfig().getStringList("allowedcmds")) {
				
			//}
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED+"You are permanently muted!");
		}
		for (String s : this.getConfig().getStringList("tempmutes")) {
			String[] array = s.split(":");
			if (array[0].contentEquals(event.getPlayer().getUniqueId().toString())) {
				if (System.currentTimeMillis() < Long.parseLong(array[1])) {
					event.getPlayer().sendMessage(ChatColor.RED+"You are temporarily muted!");
					event.setCancelled(true);
				}
				else {
					List<String> tempmutes = this.getConfig().getStringList("tempmutes");
					tempmutes.remove(s);
					this.getConfig().set("tempmutes", tempmutes);
					this.saveConfig();
					this.reloadConfig();
				}
			}
		}
	}
}


