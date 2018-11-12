package fr.eloxfire.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.eloxfire.utils.Scoreboards;
import fr.eloxfire.game.main;
import fr.eloxfire.game.states;

public class JoinQuitEvent implements Listener{
	

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		
		if(main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER)){
			if(player.getGameMode() != GameMode.SPECTATOR){
				e.setJoinMessage("§6[§2+§6]§2 " + player.getName() + "§2 veut encore plus de BONBONS !");
				main.getInstance().title.sendTitle(player, "§6[T.D.K]", "§6BONNE CONTINUATION !", 35);
			}
		}
		if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.FINISH)){
				Location spawn = player.getWorld().getSpawnLocation();
				e.setJoinMessage("§6[§2+§6]§2 " + player.getName() + "§2 veut participer à la chasse aux bonbons !");
				player.teleport(spawn);
				main.getInstance().title.sendTitle(player, "§6[T.D.K] - SAISON 4", "§6Edition spécialle HALLOWEEN", 35);
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 20));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 20));
				for(Player players : Bukkit.getOnlinePlayers()){
					players.getWorld().playSound(players.getLocation(), Sound.AMBIENCE_THUNDER, 8, 8);
				}
		}
		if(main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER)){
			if(player.getGameMode() == GameMode.SPECTATOR){
				e.setJoinMessage("§6[§2+§6]§2 " + player.getName() + "§2 a rejoins la partie en §6SPECTATEUR!");
				main.getInstance().title.sendTitle(player, "§6[T.D.K]", "§6Tu rejoins en SPECTATEUR", 35);
			}
		}

		
		player.setScoreboard(Scoreboards.s);
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		
		e.setQuitMessage("§6[§2-§6]§2 " + player.getName() + "§2 a assez de bonbons pour la soirée !");
	}
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		
		if(!p.isOp()){
			if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.FINISH)){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockPlaceEvent e){
		Player p = e.getPlayer();
		
		if(!p.isOp()){
			if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.FINISH)){
				e.setCancelled(true);
			}
		}
	}
	
}
