package fr.eloxfire.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.eloxfire.game.StartGame;

public class DeathEvent implements Listener{
	
	public static int TimerDEATH = 0;
	public static int taskDEATH;
	public static List<Player> MORT = new ArrayList<>();
	
	@EventHandler
	public void onMortDeMerde(PlayerDeathEvent e){
		String message = e.getDeathMessage();
		Player player = e.getEntity().getPlayer();

		if(StartGame.TITLE < 60){
			Bukkit.broadcastMessage(player.getName() + "§2 est surement mort à cause d'un bug !");
			player.teleport(player.getWorld().getSpawnLocation());
		}else{
			player.teleport(player.getWorld().getSpawnLocation());
			player.setGameMode(GameMode.SPECTATOR);
			StartGame.Ready.remove(player);
			MORT.add(player);
		}
		
		if(player instanceof Player){
			ItemStack crane = new ItemStack(Material.SKULL_ITEM, 1/*, (byte)3*/);
			SkullMeta craneM = (SkullMeta) crane.getItemMeta();
			craneM.setDisplayName("Ame de " + player.getName());
			crane.setItemMeta(craneM);
			player.getWorld().dropItem(player.getLocation(), crane);
			
		}
		
		Bukkit.broadcastMessage("§6[T.D.K] §2 " + player.getName() + " §2est passé en spectateur !");
		
		if(player.getKiller() instanceof Player){
			player.getKiller().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 1));
		}
		
		for(Player players : Bukkit.getOnlinePlayers()){
			players.getWorld().playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 8, 8);
		}
		
		if(message.contains("drowned")){
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(noyade)");
		}
		if(message.contains("ground") || message.contains("fall") || message.contains("fell")){
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(chute)");
		}
		if(message.contains("lava")){
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(lave)");
		}
		if(message.contains("was slain by")){
			e.setDeathMessage(player.getName() + " §7s'est fait tuer par §r(tué par : " + e.getEntity().getKiller().getName() + ")");
		}
		if(message.contains("blown up")){
			e.setDeathMessage(player.getName() + " §7 a été frappé par la mort ! §r(tué par" + e.getEntity().getKiller().getName() + "§r)");
		}
	}
	
}
