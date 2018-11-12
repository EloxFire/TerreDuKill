package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

/*
 * 
 *  @Author : EloxFire
 *  Twitter : @EloxFire
 *  Youtube : EloxFire
 *  
 *  /!\ THIS PLUGIN CANNOT BE USED WITHOUT THE PERMISSIOJN OF THE AUTHOR /!\
 *  
 */

public class main extends JavaPlugin implements Listener{

	//STATU DE JEU
	private states current;
	//INSANCIATION CLASSE PRINCIPALE
	public static main instance;
	public static main getInstance(){
		return instance;
	}
	
	public void onEnable(){
		instance = this;
		current = states.PREGAME;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[T.D.K] Enabled !");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GameListener(), this);
		pm.registerEvents(new Team(), this);
	}
	
		public void onDisable(){
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[T.D.K] Enabled !");
		}
	
		//ON GERE LES STATUS DE JEU ACTUELS
		public void setState(states state){
			current = state;
		}
		
		public boolean isState(states state){
			return current == state;
		}

		@EventHandler
		public void onJoin(PlayerJoinEvent e){
			Player p = e.getPlayer();

		}
		
		//MODIFICATION MESSAGE DE DECONEXION
		@EventHandler
		public void onQuit(PlayerQuitEvent e){
			Player p = e.getPlayer();
		}
		
		

}
